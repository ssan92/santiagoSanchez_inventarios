/**
 * 
 */
package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 *
 */
public class ReporteListaProductoVentaDTO {
	
	private Long idProducto;
	private String nombreProducto;
	private Double monto;
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	

}
