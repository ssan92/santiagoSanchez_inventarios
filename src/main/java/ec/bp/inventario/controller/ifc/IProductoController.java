package ec.bp.inventario.controller.ifc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.bp.inventario.service.dto.ActualizarStockDTO;
import ec.bp.inventario.service.dto.ProductoDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.util.exception.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Santiago
 * 
 **/

@Api(tags = "PRODUCTO", description = "Servicio para los productos")
public interface IProductoController {
	
	
	@ApiOperation(value = "", nickname = "consultaProductos", notes = "consultar todos los productos", tags={ "PRODUCTO", },response = ProductoDTO.class, responseContainer = "List")
	@RequestMapping(value = "/producto",
    produces = "application/json", 
    method = RequestMethod.GET)
	default ResponseEntity<?> consultaProductos(){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ApiOperation(value = "", nickname = "actualizarStockByIdProducto", notes = "actualizar el stock de un producto por el id", tags={ "PRODUCTO", },response = Respuesta.class)
	@RequestMapping(value = "/producto",
    produces = "application/json", 
    method = RequestMethod.PUT)
	default ResponseEntity<?> actualizarStockByIdProducto(@RequestBody(required = true) ActualizarStockDTO actualizarStockDTO) throws ApplicationException{
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
	

}
