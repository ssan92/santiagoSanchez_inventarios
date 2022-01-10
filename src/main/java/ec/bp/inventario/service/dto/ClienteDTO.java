package ec.bp.inventario.service.dto;

/**
 * @author Santiago
 * 
 * */

public class ClienteDTO {
	
	private Long idCliente;
	private String nombre;
	private String identificacion;
	
	//@JsonInclude(JsonInclude.Include.NON_NULL)
	private byte[] foto;
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	

}
