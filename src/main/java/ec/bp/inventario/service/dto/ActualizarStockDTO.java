package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 * */

public class ActualizarStockDTO {
	
	private Long idProducto;
	private Integer stock;
	
	public Long getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	

}
