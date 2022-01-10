/**
 * 
 */
package ec.bp.inventario.service.dto;

import java.util.List;

/**
 * @author Santiago
 *
 */
public class ReporteNumeroTransaccionDTO {
	
	private Long idTienda;
	private String nombre;
	private List<ReporteListaTransaccionDTO> transacciones;
	
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
	public List<ReporteListaTransaccionDTO> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<ReporteListaTransaccionDTO> transacciones) {
		this.transacciones = transacciones;
	}
	
	

}
