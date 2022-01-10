/**
 * 
 */
package ec.bp.inventario.controller.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ec.bp.inventario.controller.ifc.IReporteController;
import ec.bp.inventario.service.ifc.IPedidoService;
import ec.bp.inventario.util.exception.ApplicationException;
import ec.bp.inventario.util.exception.Error.TipoErrorEnum;

/**
 * @author Santiago
 *
 */
@Controller
public class ReporteController implements IReporteController {
	
	@Autowired
	private IPedidoService service;

	@Override
	public ResponseEntity<?> consultaReporteNumeroTransaccion() {
		return ResponseEntity.ok(service.consultaReporteNumeroTransaccion());
	}

	@Override
	public ResponseEntity<?> consultaReporteMontos() {
		return ResponseEntity.ok(service.consultaReporteMontos());
	}


	@Override
	public ResponseEntity<?> generarArchivo(Long idCliente,String fechaInicio,String fechaFin){
		File file;
		try {
			file = (File) service.generarArchivo("reporte.csv",idCliente,fechaInicio,fechaFin);
		} catch (ApplicationException e) {
			return ApplicationException.validarResultado(e);
		}
		Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource;
		try {
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (IOException e) {
			return ApplicationException.validarResultado(new ApplicationException("Problema al generar el archivo",TipoErrorEnum.SOLICITUD_INVALIDA));
		}

	    HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.csv");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        
	    return ResponseEntity.ok()
	    		.headers(header)
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}

}
