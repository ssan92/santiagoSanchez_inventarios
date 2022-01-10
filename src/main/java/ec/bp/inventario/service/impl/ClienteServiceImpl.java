/**
 * 
 */
package ec.bp.inventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.ifc.IClienteDAO;
import ec.bp.inventario.service.business.InventarioValidacion;
import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.ifc.IClienteService;
import ec.bp.inventario.util.Convert;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */

@Service
@Transactional( rollbackFor = ApplicationException.class)
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDAO repository;

	@Override
	public Iterable<ClienteDTO> consultaAllCliente() {
		return Convert.clienteToDTO(repository.findAll());
	}

	@Override
	public Respuesta crearCliente(ClienteDTO peticion) throws ApplicationException {
		InventarioValidacion.validarCamposCliente(peticion);
		repository.save(Convert.clienteToDTO(peticion));
		return new Respuesta(InventarioConstant.CODIGO_OK,InventarioConstant.MENSAJE_OK);
	}

	@Override
	public Respuesta actualizarCliente(ClienteDTO peticion) throws ApplicationException {
		InventarioValidacion.validarCliente(repository, peticion.getIdCliente());
		repository.save(Convert.clienteToDTO(peticion));
		return new Respuesta(InventarioConstant.CODIGO_OK,InventarioConstant.MENSAJE_OK);
		
	}

	@Override
	public Respuesta borrarCliente(Long idCliente) throws ApplicationException {
		repository.delete(InventarioValidacion.validarCliente(repository, idCliente));
		return new Respuesta(InventarioConstant.CODIGO_OK,InventarioConstant.MENSAJE_OK);
	}

}
