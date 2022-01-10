package ec.bp.inventario.service.business;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.Cliente;
import ec.bp.inventario.repository.entity.Producto;
import ec.bp.inventario.repository.entity.Tienda;
import ec.bp.inventario.repository.entity.TiendaProducto;
import ec.bp.inventario.repository.entity.TiendaProductoId;
import ec.bp.inventario.repository.ifc.IClienteDAO;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.repository.ifc.ITiendaDAO;
import ec.bp.inventario.repository.ifc.ITiendaProductoDAO;
import ec.bp.inventario.service.command.consumer.ConsultaAsincStockMockCommand;
import ec.bp.inventario.service.command.consumer.ConsultaStockMockCommand;
import ec.bp.inventario.service.command.model.Stock;
import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.Orden;
import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.ProductoOrden;
import ec.bp.inventario.util.exception.ApplicationException;
import ec.bp.inventario.util.exception.Error.TipoErrorEnum;

/**
 * @author Santiago
 * 
 * */

public class InventarioValidacion {
	
	public static void validarStock(int stock) throws ApplicationException {
		if(stock<=0) {
			throw new ApplicationException(InventarioConstant.STOCK_0,TipoErrorEnum.LOGICA_NEGOCIO);
		}
	}
	
	public static void validarProductos(IProductoDAO repoProd,List<Long> idProductos) throws ApplicationException{
		for (Long idProducto : idProductos) {
			validarProducto(repoProd, idProducto);
		}
		
	}
	
	public static Producto validarProducto(IProductoDAO repoProd,Long idProducto)throws ApplicationException{
		Optional<Producto> optional= repoProd.findById(idProducto);
		if(optional.isEmpty()) {
			throw new ApplicationException(InventarioConstant.NO_PRODUCTO+idProducto,TipoErrorEnum.SOLICITUD_INVALIDA);
		}
		return optional.get();
	}
	
	public static void validarCamposCliente(ClienteDTO peticion ) throws ApplicationException{
		if(peticion.getIdentificacion()==null || "".equals(peticion.getIdentificacion())) {
			throw new ApplicationException(InventarioConstant.IDENTIFICACION,TipoErrorEnum.SOLICITUD_INVALIDA);
		}
		if(peticion.getNombre()==null || "".equals(peticion.getNombre())) {
			throw new ApplicationException(InventarioConstant.NOMBRE_CLIENTE,TipoErrorEnum.SOLICITUD_INVALIDA);
		}
	}
	
	public static Cliente validarCliente(IClienteDAO repoCliente,Long idCliente)throws ApplicationException{
		Optional<Cliente> optional= repoCliente.findById(idCliente);
		if(optional.isEmpty()) {
			throw new ApplicationException(InventarioConstant.NO_CLIENTE+idCliente,TipoErrorEnum.SOLICITUD_INVALIDA);
		}
		return optional.get();
	}
	
	public static Tienda validarTienda(ITiendaDAO repoTienda,Long idTienda)throws ApplicationException{
		Optional<Tienda> optional= repoTienda.findById(idTienda);
		if(optional.isEmpty()) {
			throw new ApplicationException(InventarioConstant.NO_TIENDA+idTienda,TipoErrorEnum.SOLICITUD_INVALIDA);
		}
		return optional.get();
	}
	
	public static void validarOrden(PedidoDTO dto,IClienteDAO repoCliente,ITiendaDAO repoTienda, IProductoDAO repoProd,ITiendaProductoDAO repoTiendaProd) throws ApplicationException{
		validarCliente(repoCliente, dto.getIdCliente());
		for (Orden orden : dto.getPedidos()) {
			validarTienda(repoTienda, orden.getIdTienda());
			for (ProductoOrden prod : orden.getProductos()) {
				validarProducto(repoProd, prod.getIdProducto());
				
				Optional<TiendaProducto> opTiendaProd= repoTiendaProd.findById(new TiendaProductoId(new Tienda(orden.getIdTienda()), new Producto(prod.getIdProducto())));
				if(opTiendaProd.isEmpty()) {
					throw new ApplicationException(InventarioConstant.NO_TIENDA_PRODUCTO.replace(InventarioConstant.R_PROD, prod.getIdProducto()+"") +orden.getIdTienda(),TipoErrorEnum.SOLICITUD_INVALIDA);
				}
				
				if(prod.getCantidad()<=0) {
					throw new ApplicationException(InventarioConstant.CANTIDAD_0,TipoErrorEnum.SOLICITUD_INVALIDA);
				}
			}
		}
	}
	
	@Transactional(rollbackFor = ApplicationException.class)
	public static void actualizarStock(IProductoDAO repoProd,Producto producto,int newStock) {
		producto.setStock(newStock);
		repoProd.save(producto);
	}
	
	@Transactional(rollbackFor = ApplicationException.class)
	public static void validarStockEnBodega(PedidoDTO dto,IProductoDAO repoProd,ConsultaStockMockCommand command,ConsultaAsincStockMockCommand asinc) throws ApplicationException{
		for (Orden orden : dto.getPedidos()) {
			for (ProductoOrden prod : orden.getProductos()) {
				Producto producto=validarProducto(repoProd, prod.getIdProducto());
				int cantidad=prod.getCantidad();
				int stock=producto.getStock();
				int newStock=stock-cantidad;
				int stockFaltante=Math.abs(newStock);
				if(newStock>=0) {
					actualizarStock(repoProd, producto, newStock);
				}else if(stockFaltante> 10){
					throw new ApplicationException(InventarioConstant.NO_UNIDAD+prod.getIdProducto(),TipoErrorEnum.SOLICITUD_INVALIDA);
				}else if(stockFaltante >5 && stockFaltante<=10) {
					try {
						Stock stockExtra= (Stock) command.execute(null);
						stock=stock+stockExtra.getStock();
						newStock=stock-cantidad;
						actualizarStock(repoProd, producto, newStock);
					} catch (Exception e) {
						throw new ApplicationException(InventarioConstant.NO_DISPONIBLE+prod.getIdProducto(),TipoErrorEnum.SOLICITUD_INVALIDA);
					}
				}else if(stockFaltante>=0 && stockFaltante<=5) {
						final int stockF=stock;
						ListenableFuture<ResponseEntity<Stock>> future = (ListenableFuture<ResponseEntity<Stock>>) asinc.execute(null);
						future.addCallback(new ListenableFutureCallback<ResponseEntity<Stock>>() {

							@Override
							public void onSuccess(ResponseEntity<Stock> result) {
								Stock stockExtra=result.getBody();
								int newStockF=stockF+stockExtra.getStock()-cantidad;
								actualizarStock(repoProd, producto, newStockF);
								//System.out.println("stock actualizado");
							}

							@Override
							public void onFailure(Throwable ex) {
								// TODO Guardar log del error
								
							}
						});
						//System.out.println("esperando respuesta, para actualizar stock");
				}
			}
		}
	}

}
