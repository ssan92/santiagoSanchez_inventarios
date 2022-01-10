/**
 * 
 */
package ec.bp.inventario.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ec.bp.inventario.controller.ifc.ITiendaController;
import ec.bp.inventario.service.dto.TiendaProductoDTO;
import ec.bp.inventario.service.ifc.ITiendaProductoService;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */

@Controller
public class TiendaController implements ITiendaController {
	
	@Autowired
	private ITiendaProductoService service;

	@Override
	public ResponseEntity<?> consultaAsignacionByTienda(Long idTienda) {
		try {
			return ResponseEntity.ok(service.consultaAsignacionByTienda(idTienda));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

	@Override
	public ResponseEntity<?> asignarProductosByTienda(TiendaProductoDTO tiendaProductoDTO) {
		try {
			return ResponseEntity.ok(service.asignarProductosByTienda(tiendaProductoDTO));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

}
