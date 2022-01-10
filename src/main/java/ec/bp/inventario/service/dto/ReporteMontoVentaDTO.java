/**
 * 
 */
package ec.bp.inventario.service.dto;

import java.util.List;

/**
 * @author Santiago
 *
 */
public class ReporteMontoVentaDTO {
	
	private Long idTienda;
	private String nombre;
	private List<ReporteListaProductoVentaDTO> productosVendidos;
	
	public Long getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<ReporteListaProductoVentaDTO> getProductosVendidos() {
		return productosVendidos;
	}
	public void setProductosVendidos(List<ReporteListaProductoVentaDTO> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}
	
	

}
