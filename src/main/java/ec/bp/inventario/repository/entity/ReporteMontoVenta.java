/**
 * 
 */
package ec.bp.inventario.repository.entity;

/**
 * @author Santiago
 *
 */
public class ReporteMontoVenta {
	
	private Long idTienda;
	private String nombre;
	private Long idProducto;
	private String nombreProducto;
	private Double monto;
	
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
	
	public ReporteMontoVenta(Long idTienda, String nombre, Long idProducto, String nombreProducto, Double monto) {
		this.idTienda = idTienda;
		this.nombre = nombre;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.monto = monto;
	}
	
	public ReporteMontoVenta() {
	}
	
	

}
