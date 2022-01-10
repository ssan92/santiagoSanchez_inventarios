/**
 * 
 */
package ec.bp.inventario.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Santiago
 *
 */
public class Orden implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idTienda;
	private List<ProductoOrden> productos;
	
	public Long getIdTienda() {
		return idTienda;
	}
	
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public List<ProductoOrden> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoOrden> productos) {
		this.productos = productos;
	}
	

	

}
