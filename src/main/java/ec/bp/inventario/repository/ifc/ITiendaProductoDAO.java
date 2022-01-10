/**
 * 
 */
package ec.bp.inventario.repository.ifc;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ec.bp.inventario.repository.entity.TiendaProducto;
import ec.bp.inventario.repository.entity.TiendaProductoId;

/**
 * @author Santiago
 *
 */
public interface ITiendaProductoDAO extends CrudRepository<TiendaProducto, TiendaProductoId> {
	
	@Query("select t from TiendaProducto t where t.id.tienda.idTienda=?1")
	public List<TiendaProducto> findbyIdTienda(Long idTienda);

}
