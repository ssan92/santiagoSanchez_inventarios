/**
 * 
 */
package ec.bp.inventario.controller.ifc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.bp.inventario.service.dto.ReporteMontoVentaDTO;
import ec.bp.inventario.service.dto.ReporteNumeroTransaccionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Santiago
 *
 */
@Api(tags = "REPORTE", description = "Servicio para los reportes")
public interface IReporteController {
	
	@ApiOperation(value = "", nickname = "consultaReporteNumeroTransaccion", notes = "consultar todos las transacciones agrupadas por tienda y fecha", tags={ "REPORTE", },response = ReporteNumeroTransaccionDTO.class,responseContainer = "List")
	@RequestMapping(value = "/transaccionNumero",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> consultaReporteNumeroTransaccion(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "consultaReporteMontos", notes = "consultar todos los montos agrupados por tienda y producto", tags={ "REPORTE", },response = ReporteMontoVentaDTO.class,responseContainer = "List")
	@RequestMapping(value = "/transaccionMonto",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> consultaReporteMontos(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "generarArchivo", notes = "generar un arhivo de las transacciones de un cliente por un rango de fecha", tags={ "REPORTE", })
	@RequestMapping(value = "/reporte/{idCliente}/{fechaInicio}/{fechaFin}",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> generarArchivo(@ApiParam(value = "",required=true, example = "1") @PathVariable("idCliente") Long idCliente,@ApiParam(value = "",required=true, example = "2022-01-08") @PathVariable("fechaInicio") String fechaInicio,@ApiParam(value = "",required=true, example = "2022-01-09") @PathVariable("fechaFin") String fechaFin){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
