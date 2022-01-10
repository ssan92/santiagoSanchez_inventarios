/**
 * 
 */
package ec.bp.inventario.service.impl;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.Pedido;
import ec.bp.inventario.repository.entity.ReporteMontoVenta;
import ec.bp.inventario.repository.entity.ReporteNumeroTransaccion;
import ec.bp.inventario.repository.ifc.IClienteDAO;
import ec.bp.inventario.repository.ifc.IPedidoDAO;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.repository.ifc.ITiendaDAO;
import ec.bp.inventario.repository.ifc.ITiendaProductoDAO;
import ec.bp.inventario.service.business.InventarioValidacion;
import ec.bp.inventario.service.command.consumer.ConsultaAsincStockMockCommand;
import ec.bp.inventario.service.command.consumer.ConsultaStockMockCommand;
import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.ReporteMontoVentaDTO;
import ec.bp.inventario.service.dto.ReporteNumeroTransaccionDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.ifc.IPedidoService;
import ec.bp.inventario.util.ApiUtil;
import ec.bp.inventario.util.Convert;
import ec.bp.inventario.util.CrearArchivo;
import ec.bp.inventario.util.exception.ApplicationException;
import ec.bp.inventario.util.exception.Error.TipoErrorEnum;

/**
 * @author Santiago
 *
 */
@Service
@Transactional( rollbackFor = ApplicationException.class)
public class PedidoService implements IPedidoService {
	
	@Autowired
	private IPedidoDAO repository;
	
	@Autowired
	private IClienteDAO repoCliente;
	
	@Autowired
	private ITiendaDAO repoTienda;
	
	@Autowired
	private IProductoDAO repoProd;
	
	@Autowired
	private ITiendaProductoDAO repoTiendaProd;
	
	@Autowired
	private ConsultaStockMockCommand stockCommand;
	
	@Autowired
	private ConsultaAsincStockMockCommand asincStockCommand;
	
	@Autowired
	CrearArchivo crearArchivo;

	@Override
	@Transactional(rollbackFor = ApplicationException.class)
	public Respuesta realizarPedido(PedidoDTO peticion) throws ApplicationException {
		InventarioValidacion.validarOrden(peticion, repoCliente, repoTienda, repoProd, repoTiendaProd);
		InventarioValidacion.validarStockEnBodega(peticion, repoProd, stockCommand, asincStockCommand);
		repository.saveAll(Convert.dtoToPedido(peticion));
		return new Respuesta(InventarioConstant.CODIGO_OK,InventarioConstant.MENSAJE_OK);
	}

	@Override
	public List<ReporteNumeroTransaccionDTO> consultaReporteNumeroTransaccion() {
		List<ReporteNumeroTransaccion> list= repository.consultaNumeroDeTransacciones();
		return Convert.entityToReporteNumero(list);
	}

	@Override
	public List<ReporteMontoVentaDTO> consultaReporteMontos() {
		List<ReporteMontoVenta> list= repository.consultaMontoVenta();
		return Convert.entityToReporteMonto(list);
	}


	@Override
	public Object generarArchivo(String ruta,Long idCliente, String fechaInicio, String fechaFin) throws ApplicationException {
		InventarioValidacion.validarCliente(repoCliente, idCliente);
		List<Pedido> list;
		try {
			list = repository.consultaPedidoByCliente(idCliente, ApiUtil.format(fechaInicio, InventarioConstant.YYYYMMDD), ApiUtil.format(fechaFin, InventarioConstant.YYYYMMDD));
		} catch (ParseException e) {
			throw new ApplicationException("El formato de fecha es incorrecto",TipoErrorEnum.LOGICA_NEGOCIO);
		}
		File file= crearArchivo.crearArchivo(Convert.pedidoToReporteCliente(list), ruta);
		return file;
	}
	
	
	
	

}
