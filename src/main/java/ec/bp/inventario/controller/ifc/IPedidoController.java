/**
 * 
 */
package ec.bp.inventario.controller.ifc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.Respuesta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Santiago
 *
 */

@Api(tags = "PEDIDO", description = "Servicios para que un cliente realice pedidos")
public interface IPedidoController {
	
	@ApiOperation(value = "", nickname = "realizarPedido", notes = "solicitar una orden de productos", tags={ "PEDIDO", },response = Respuesta.class)
	@RequestMapping(value = "/pedido",
    produces = "application/json", 
    method = RequestMethod.POST)
	default ResponseEntity<?> realizarPedido(@RequestBody(required = true) PedidoDTO dto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
