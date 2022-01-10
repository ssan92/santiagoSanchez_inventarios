/**
 * 
 */
package ec.bp.inventario.repository.ifc;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ec.bp.inventario.repository.entity.Pedido;
import ec.bp.inventario.repository.entity.ReporteMontoVenta;
import ec.bp.inventario.repository.entity.ReporteNumeroTransaccion;

/**
 * @author Santiago
 *
 */
public interface IPedidoDAO extends CrudRepository<Pedido, Long>{
	
	@Query(nativeQuery = true)
	public List<ReporteNumeroTransaccion> consultaNumeroDeTransacciones();
	
	@Query(nativeQuery = true)
	public List<ReporteMontoVenta> consultaMontoVenta();
	
	@Query("SELECT p FROM Pedido p where p.cliente.idCliente=?1 and  trunc(p.fechaTransaccion) between ?2 and ?3")
	public List<Pedido> consultaPedidoByCliente(Long idCliente, Date fechaInicio, Date fechaFin);

}
