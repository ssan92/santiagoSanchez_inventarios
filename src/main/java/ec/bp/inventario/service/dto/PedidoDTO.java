/**
 * 
 */
package ec.bp.inventario.service.dto;

import java.util.List;

/**
 * @author Santiago
 *
 */
public class PedidoDTO {
	
	private Long idCliente;
	private List<Orden> pedidos;
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Orden> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Orden> pedidos) {
		this.pedidos = pedidos;
	}

	
	

}
