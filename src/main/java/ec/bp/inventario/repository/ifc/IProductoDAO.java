/**
 * 
 */
package ec.bp.inventario.repository.ifc;

import org.springframework.data.repository.CrudRepository;

import ec.bp.inventario.repository.entity.Producto;

/**
 * @author Santiago
 *
 */
public interface IProductoDAO extends CrudRepository<Producto, Long> {

}
