/**
 * 
 */
package ec.bp.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.TiendaProducto;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.repository.ifc.ITiendaDAO;
import ec.bp.inventario.repository.ifc.ITiendaProductoDAO;
import ec.bp.inventario.service.business.InventarioValidacion;
import ec.bp.inventario.service.dto.ConsultaAsignacionDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.dto.TiendaProductoDTO;
import ec.bp.inventario.service.ifc.ITiendaProductoService;
import ec.bp.inventario.util.Convert;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */

@Service
@Transactional( rollbackFor = ApplicationException.class)
public class TiendaProductoServiceImpl implements ITiendaProductoService {
	
	@Autowired
	private ITiendaProductoDAO repository;
	
	@Autowired
	private ITiendaDAO repoTienda;
	
	@Autowired
	private IProductoDAO repoProd;

	@Override
	public Respuesta asignarProductosByTienda(TiendaProductoDTO peticion) throws ApplicationException {
		InventarioValidacion.validarTienda(repoTienda, peticion.getIdTienda());
		InventarioValidacion.validarProductos(repoProd, peticion.getIdProductos());
		List<TiendaProducto> productos= Convert.dtoToTiendaProducto(peticion);
		repository.saveAll(productos);
		return new Respuesta(InventarioConstant.CODIGO_OK, InventarioConstant.MENSAJE_OK);
		
	}

	@Override
	public ConsultaAsignacionDTO consultaAsignacionByTienda(Long idTienda) throws ApplicationException {
		InventarioValidacion.validarTienda(repoTienda, idTienda);
		List<TiendaProducto> list=repository.findbyIdTienda(idTienda);
		return Convert.tiendaProductoTodto(list);

	}

}
