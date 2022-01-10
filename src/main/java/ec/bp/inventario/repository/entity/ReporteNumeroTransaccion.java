/**
 * 
 */
package ec.bp.inventario.repository.entity;

import java.util.Date;

/**
 * @author Santiago
 *
 */

public class ReporteNumeroTransaccion {
	
	private Long idTienda;
	private String nombre;
	private Date fechaTransaccion;
	private Long numeroTranssaccion;
	
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
	
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	
	public Long getNumeroTranssaccion() {
		return numeroTranssaccion;
	}
	
	public void setNumeroTranssaccion(Long numeroTranssaccion) {
		this.numeroTranssaccion = numeroTranssaccion;
	}

	public ReporteNumeroTransaccion() {
	}

	public ReporteNumeroTransaccion(Long idTienda, String nombre, Date fechaTransaccion, Long numeroTranssaccion) {

		this.idTienda = idTienda;
		this.nombre = nombre;
		this.fechaTransaccion = fechaTransaccion;
		this.numeroTranssaccion = numeroTranssaccion;
	}
	
	

}
