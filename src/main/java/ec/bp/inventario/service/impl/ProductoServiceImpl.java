/**
 * 
 */
package ec.bp.inventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.Producto;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.service.business.InventarioValidacion;
import ec.bp.inventario.service.dto.ActualizarStockDTO;
import ec.bp.inventario.service.dto.ProductoDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.ifc.IProductoService;
import ec.bp.inventario.util.Convert;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */

@Service
@Transactional( rollbackFor = ApplicationException.class)
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDAO repository;

	@Override
	public List<ProductoDTO> consultaProductos() {
		Iterable<Producto> productos= repository.findAll();
		return Convert.entityToProductoDTO(productos);
	}

	@Override
	public Respuesta actualizarStockByIdProducto(ActualizarStockDTO peticion) throws ApplicationException {
		Producto producto=InventarioValidacion.validarProducto(repository, peticion.getIdProducto());
		producto.setStock(peticion.getStock());
		InventarioValidacion.validarStock(peticion.getStock());
		repository.save(producto);
		return new Respuesta(InventarioConstant.CODIGO_OK,InventarioConstant.MENSAJE_OK);
		
	}
	

}
