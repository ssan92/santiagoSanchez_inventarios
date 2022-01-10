package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 * 
 * */

import java.util.List;

public class ConsultaAsignacionDTO {
	
	private Long idTienda;
	private String nombre;
	private List<ProductoDTO> productos;
	
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
	
	public List<ProductoDTO> getProductos() {
		return productos;
	}
	
	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}
	
	

}
