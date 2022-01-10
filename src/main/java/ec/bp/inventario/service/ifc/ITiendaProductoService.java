package ec.bp.inventario.service.ifc;

import ec.bp.inventario.service.dto.ConsultaAsignacionDTO;
import ec.bp.inventario.service.dto.Respuesta;
import ec.bp.inventario.service.dto.TiendaProductoDTO;
import ec.bp.inventario.util.exception.ApplicationException;

public interface ITiendaProductoService {
	
	public Respuesta asignarProductosByTienda(TiendaProductoDTO peticion) throws ApplicationException;
	
	public ConsultaAsignacionDTO consultaAsignacionByTienda(Long idTienda) throws ApplicationException;

}
