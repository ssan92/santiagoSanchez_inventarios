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

import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.Respuesta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Santiago
 *
 */

@Api(tags = "CLIENTE", description = "Servicios CRUD para clientes")
public interface IClienteController {
	
	
	@ApiOperation(value = "", nickname = "consultaAllCliente", notes = "consultar todos los clientes", tags={ "CLIENTE", },response = ClienteDTO.class, responseContainer = "List")
	@RequestMapping(value = "/cliente",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> consultaAllCliente(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "crearCliente", notes = "crear un cliente", tags={ "CLIENTE", },response = Respuesta.class)
	@RequestMapping(value = "/cliente",
    produces = "application/json", 
    method = RequestMethod.POST)
	default ResponseEntity<?> crearCliente(@RequestBody(required = true) ClienteDTO clienteDTO) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "actualizarCliente", notes = "actualiza un cliente", tags={ "CLIENTE", },response = Respuesta.class)
	@RequestMapping(value = "/cliente",
    produces = "application/json", 
    method = RequestMethod.PUT)
	default ResponseEntity<?> actualizarCliente(@RequestBody(required = true) ClienteDTO clienteDTO) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "borrarCliente", notes = "borra un cliente", tags={ "CLIENTE", },response = Respuesta.class)
	@RequestMapping(value = "/cliente/{idCliente}",
    produces = "application/json", 
    method = RequestMethod.DELETE)
	default ResponseEntity<?> borrarCliente(@ApiParam(value = "",required=true, example = "1") @PathVariable("idCliente") Long idCliente){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	

}
