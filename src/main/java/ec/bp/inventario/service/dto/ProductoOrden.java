/**
 * 
 */
package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 *
 */
public class ProductoOrden {
	
	private Long idProducto;
	private Integer Cantidad;
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getCantidad() {
		return Cantidad;
	}
	public void setCantidad(Integer cantidad) {
		Cantidad = cantidad;
	}
	
	

}
