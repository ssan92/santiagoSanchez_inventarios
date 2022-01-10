/**
 * 
 */
package ec.bp.inventario.service.ifc;

import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */
public interface IClienteService {
	
	public Iterable<ClienteDTO> consultaAllCliente();
	
	public Respuesta crearCliente(ClienteDTO peticion) throws ApplicationException;
	
	public Respuesta actualizarCliente(ClienteDTO peticion) throws ApplicationException;
	
	public Respuesta borrarCliente(Long idCliente) throws ApplicationException;

}
