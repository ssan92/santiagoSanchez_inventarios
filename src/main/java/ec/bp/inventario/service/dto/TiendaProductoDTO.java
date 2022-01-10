/**
 * 
 */
package ec.bp.inventario.service.dto;

import java.util.List;

/**
 * @author Santiago
 *
 */
public class TiendaProductoDTO {
	
	private Long idTienda;
	private List<Long> idProductos;


	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public List<Long> getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(List<Long> idProductos) {
		this.idProductos = idProductos;
	}

}
