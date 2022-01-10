/**
 * 
 */
package ec.bp.inventario.service.ifc;

import java.util.List;

import ec.bp.inventario.service.dto.ActualizarStockDTO;
import ec.bp.inventario.service.dto.ProductoDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */


public interface IProductoService {
	
	public List<ProductoDTO> consultaProductos();
	
	public Respuesta actualizarStockByIdProducto(ActualizarStockDTO peticion) throws ApplicationException;

}
