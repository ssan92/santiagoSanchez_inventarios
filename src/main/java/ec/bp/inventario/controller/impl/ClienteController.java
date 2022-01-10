/**
 * 
 */
package ec.bp.inventario.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ec.bp.inventario.controller.ifc.IClienteController;
import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.ifc.IClienteService;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */

@Controller
public class ClienteController implements IClienteController{
	
	@Autowired
	private IClienteService service;

	@Override
	public ResponseEntity<?> consultaAllCliente() {
		return ResponseEntity.ok(service.consultaAllCliente());
	}

	@Override
	public ResponseEntity<?> crearCliente(ClienteDTO clienteDTO) {
		try {
			return ResponseEntity.ok(service.crearCliente(clienteDTO));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

	@Override
	public ResponseEntity<?> actualizarCliente(ClienteDTO clienteDTO) {
		try {
			return ResponseEntity.ok(service.actualizarCliente(clienteDTO));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

	@Override
	public ResponseEntity<?> borrarCliente(Long idCliente) {
		try {
			return ResponseEntity.ok(service.borrarCliente(idCliente));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

}
