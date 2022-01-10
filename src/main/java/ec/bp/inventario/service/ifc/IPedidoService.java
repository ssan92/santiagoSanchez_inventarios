/**
 * 
 */
package ec.bp.inventario.service.ifc;

import java.util.List;

import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.ReporteMontoVentaDTO;
import ec.bp.inventario.service.dto.ReporteNumeroTransaccionDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */
public interface IPedidoService {
	
	public Respuesta realizarPedido(PedidoDTO peticion) throws ApplicationException;
	
	public List<ReporteNumeroTransaccionDTO> consultaReporteNumeroTransaccion();
	
	public List<ReporteMontoVentaDTO> consultaReporteMontos();
	
	public Object generarArchivo(String ruta,Long idCliente, String fechaInicio, String fechaFin) throws ApplicationException;

}
