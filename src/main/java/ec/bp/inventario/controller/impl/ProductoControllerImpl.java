package ec.bp.inventario.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ec.bp.inventario.controller.ifc.IProductoController;
import ec.bp.inventario.service.dto.ActualizarStockDTO;
import ec.bp.inventario.service.ifc.IProductoService;
import ec.bp.inventario.util.exception.ApplicationException;

@Controller
public class ProductoControllerImpl implements IProductoController {
	
	@Autowired
	private IProductoService service;

	@Override
	public ResponseEntity<?> consultaProductos() {
		return ResponseEntity.ok(service.consultaProductos());
	}

	@Override
	public ResponseEntity<?> actualizarStockByIdProducto(ActualizarStockDTO actualizarStockDTO) {
		try {
			return ResponseEntity.ok(service.actualizarStockByIdProducto(actualizarStockDTO));
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
	}

}
