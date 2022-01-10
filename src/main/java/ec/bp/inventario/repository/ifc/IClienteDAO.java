/**
 * 
 */
package ec.bp.inventario.repository.ifc;

import org.springframework.data.repository.CrudRepository;

import ec.bp.inventario.repository.entity.Cliente;

/**
 * @author Santiago
 *
 */
public interface IClienteDAO extends CrudRepository<Cliente, Long>{

}
