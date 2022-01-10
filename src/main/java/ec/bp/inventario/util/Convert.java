package ec.bp.inventario.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IteratorUtils;
import org.modelmapper.ModelMapper;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.Cliente;
import ec.bp.inventario.repository.entity.Pedido;
import ec.bp.inventario.repository.entity.Producto;
import ec.bp.inventario.repository.entity.ReporteMontoVenta;
import ec.bp.inventario.repository.entity.ReporteNumeroTransaccion;
import ec.bp.inventario.repository.entity.Tienda;
import ec.bp.inventario.repository.entity.TiendaProducto;
import ec.bp.inventario.repository.entity.TiendaProductoId;
import ec.bp.inventario.service.command.model.Prod;
import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.ConsultaAsignacionDTO;
import ec.bp.inventario.service.dto.Orden;
import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.ProductoDTO;
import ec.bp.inventario.service.dto.ProductoOrden;
import ec.bp.inventario.service.dto.ReporteClienteDTO;
import ec.bp.inventario.service.dto.ReporteListaProductoVentaDTO;
import ec.bp.inventario.service.dto.ReporteListaTransaccionDTO;
import ec.bp.inventario.service.dto.ReporteMontoVentaDTO;
import ec.bp.inventario.service.dto.ReporteNumeroTransaccionDTO;
import ec.bp.inventario.service.dto.TiendaProductoDTO;

/**
 * @author Santiago
 * 
 * */

public class Convert {
	
	public static List<Producto> mockToEntity(List<Prod> list) {
		List<Producto> productos=new ArrayList<Producto>();
		for (Prod prod : list) {
			Producto producto=new Producto();
			producto.setIdProducto(Long.valueOf(prod.getId()));
			producto.setCodigo(prod.getCod());
			producto.setNombre(prod.getName());
			producto.setPrecio(prod.getPrice());
			producto.setStock(prod.getStock());
			productos.add(producto);
		}
		return productos;
	}
	
	public static List<ProductoDTO> entityToProductoDTO(Iterable<Producto> list) {
		List<ProductoDTO> productos=new ArrayList<ProductoDTO>();
		for (Producto prod : list) {
			ProductoDTO producto=new ProductoDTO();
			producto.setCodigo(prod.getCodigo());
			producto.setNombre(prod.getNombre());
			productos.add(producto);
		}
		return productos;
	}
	
	public static List<TiendaProducto> dtoToTiendaProducto(TiendaProductoDTO dto){
		List<TiendaProducto> productos=new ArrayList<TiendaProducto>();
		for (Long idProducto : dto.getIdProductos()) {
			TiendaProducto producto=new TiendaProducto();
			producto.setId(new TiendaProductoId(new Tienda(dto.getIdTienda()), new Producto(idProducto)));
			productos.add(producto);
		}
		return productos;
		
	}
	
	public static ConsultaAsignacionDTO tiendaProductoTodto(List<TiendaProducto> list) {
		ConsultaAsignacionDTO asignacionDTO=new ConsultaAsignacionDTO();
		List<ProductoDTO> productos=new ArrayList<ProductoDTO>();
		for (TiendaProducto prod : list) {
			asignacionDTO.setIdTienda(prod.getId().getTienda().getIdTienda());
			asignacionDTO.setNombre(prod.getId().getTienda().getNombre());
			
			ProductoDTO producto=new ProductoDTO();
			producto.setCodigo(prod.getId().getProducto().getCodigo());
			producto.setNombre(prod.getId().getProducto().getNombre());
			productos.add(producto);
			
			asignacionDTO.setProductos(productos);
			
		}
		return asignacionDTO;
	}
	
	public static List<ClienteDTO> clienteToDTO(Iterable<Cliente> entitys) {
		ModelMapper modelMapper =new ModelMapper();
		List<Cliente> list= IteratorUtils.toList(entitys.iterator());
		List<ClienteDTO> dtos = list
				  .stream()
				  .map(entity -> modelMapper.map(entity, ClienteDTO.class))
				  .collect(Collectors.toList());
		return dtos;
	}
	
	public static Cliente clienteToDTO(ClienteDTO dto) {
		return new ModelMapper().map(dto, Cliente.class);
	}
	
	public static List<Pedido> dtoToPedido(PedidoDTO dto){
		List<Pedido> pedidos=new ArrayList<Pedido>();
		int i=0;
		for (Orden orden : dto.getPedidos()) {
			for (ProductoOrden prod : orden.getProductos()) {
				Pedido pedido=new Pedido();
				pedido.setIdPedido(Long.valueOf(0));
				pedido.setFechaTransaccion(new Date());
				pedido.setCodigo("O"+i);
				pedido.setCliente(new Cliente(dto.getIdCliente()));
				pedido.setTienda(new Tienda(orden.getIdTienda()));
				
				pedido.setCantidad(prod.getCantidad());
				pedido.setProducto(new Producto(prod.getIdProducto()));
				pedidos.add(pedido);
				
			}
			i++;
		}
		return pedidos;
		
	}
	
	public static List<ReporteNumeroTransaccionDTO> entityToReporteNumero(List<ReporteNumeroTransaccion> list) {
		List<ReporteNumeroTransaccionDTO> listDto=new ArrayList<>();
		int i=0;
		Long idTiendaAnterior = Long.valueOf(0);
		for (ReporteNumeroTransaccion rpt : list) {
			
			ReporteNumeroTransaccionDTO dto=new ReporteNumeroTransaccionDTO();
			List<ReporteListaTransaccionDTO> listaTransaccionDTOs=new ArrayList<ReporteListaTransaccionDTO>();
			dto.setIdTienda(rpt.getIdTienda());
			dto.setNombre(rpt.getNombre());
			ReporteListaTransaccionDTO transaccionDTO=new ReporteListaTransaccionDTO();
			transaccionDTO.setFechaTransaccion(ApiUtil.format(rpt.getFechaTransaccion(), InventarioConstant.YYYYMMDD) );
			transaccionDTO.setNumeroTranssaccion(rpt.getNumeroTranssaccion());
			listaTransaccionDTOs.add(transaccionDTO);
			dto.setTransacciones(listaTransaccionDTOs);
			
			if(idTiendaAnterior==rpt.getIdTienda()) {
				listDto.get(i-1).getTransacciones().add(transaccionDTO);
				i--;
			}else {
				listDto.add(dto);
			}
			idTiendaAnterior=rpt.getIdTienda();	
			i++;
		}
		return listDto;
	}
	
	public static List<ReporteMontoVentaDTO> entityToReporteMonto (List<ReporteMontoVenta> list) {
		List<ReporteMontoVentaDTO> listDto=new ArrayList<>();
		int i=0;
		Long idTiendaAnterior = Long.valueOf(0);
		for (ReporteMontoVenta rpt : list) {
			ReporteMontoVentaDTO dto=new ReporteMontoVentaDTO();
			dto.setIdTienda(rpt.getIdTienda());
			dto.setNombre(rpt.getNombre());
			ReporteListaProductoVentaDTO transaccionDTO=new ReporteListaProductoVentaDTO();
			transaccionDTO.setIdProducto(rpt.getIdProducto());
			transaccionDTO.setNombreProducto(rpt.getNombreProducto());
			transaccionDTO.setMonto(rpt.getMonto());
			List<ReporteListaProductoVentaDTO> listaTransaccionDTOs=new ArrayList<ReporteListaProductoVentaDTO>();
			listaTransaccionDTOs.add(transaccionDTO);
			dto.setProductosVendidos(listaTransaccionDTOs);
			if(idTiendaAnterior==rpt.getIdTienda()) {
				listDto.get(i-1).getProductosVendidos().add(transaccionDTO);
				i--;
			}else {
				listDto.add(dto);
			}
			idTiendaAnterior=rpt.getIdTienda();	
			i++;
		}
		return listDto;
	}
	
	public static List<ReporteClienteDTO> pedidoToReporteCliente(List<Pedido> list){
		List<ReporteClienteDTO> clienteDTOs=new ArrayList<ReporteClienteDTO>();
		for (Pedido pedido : list) {
			ReporteClienteDTO dto=new ReporteClienteDTO();
			dto.setIdentificacion(pedido.getCliente().getIdentificacion());
			dto.setNombreCliente(pedido.getCliente().getNombre());
			dto.setFechaTransaccion(ApiUtil.format(pedido.getFechaTransaccion(), InventarioConstant.YYYYMMDDHH24MISS));
			dto.setNombreTienda(pedido.getTienda().getNombre());
			dto.setNombreProducto(pedido.getProducto().getNombre());
			dto.setPrecioUnitario(pedido.getProducto().getPrecio());
			dto.setCantidad(pedido.getCantidad());
			dto.setTotal(dto.getPrecioUnitario()*dto.getCantidad());
			clienteDTOs.add(dto);
		}
		return clienteDTOs;
	}

}
