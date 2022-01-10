/**
 * 
 */
package ec.bp.inventario.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ec.bp.inventario.controller.ifc.IPedidoController;
import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.ifc.IPedidoService;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */
@Controller
public class PedidoController implements IPedidoController {
	
	@Autowired
	private IPedidoService service;

	@Override
	public ResponseEntity<?> realizarPedido(PedidoDTO dto) {
		try {
			return ResponseEntity.ok(service.realizarPedido(dto));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

}
