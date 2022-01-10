/**
 * 
 */
package ec.bp.inventario.controller.ifc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.bp.inventario.service.dto.ConsultaAsignacionDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.dto.TiendaProductoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Santiago
 *
 */

@Api(tags = "TIENDA", description = "Servicio para las tiendas")
public interface ITiendaController {
	
	@ApiOperation(value = "", nickname = "consultaAsignacionByTienda", notes = "consultar todos los productos que vende esa tienda", tags={ "TIENDA", },response = ConsultaAsignacionDTO.class)
	@RequestMapping(value = "/tienda/{idTienda}",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> consultaAsignacionByTienda(@ApiParam(value = "",required=true, example = "1") @PathVariable("idTienda") Long idTienda){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "asignarProductosByTienda", notes = "actualizar el stock de un producto por el id", tags={ "TIENDA", },response = Respuesta.class)
	@RequestMapping(value = "/tienda",
    produces = "application/json", 
    method = RequestMethod.POST)
	default ResponseEntity<?> asignarProductosByTienda(@RequestBody(required = true) TiendaProductoDTO tiendaProductoDTO){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
