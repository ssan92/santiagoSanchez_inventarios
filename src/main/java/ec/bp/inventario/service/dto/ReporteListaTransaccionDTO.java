/**
 * 
 */
package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 *
 */
public class ReporteListaTransaccionDTO {
	
	private String fechaTransaccion;
	private Long numeroTranssaccion;
	
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public Long getNumeroTranssaccion() {
		return numeroTranssaccion;
	}
	public void setNumeroTranssaccion(Long numeroTranssaccion) {
		this.numeroTranssaccion = numeroTranssaccion;
	}
	
	

}
