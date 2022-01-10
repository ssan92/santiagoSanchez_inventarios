package ec.bp.inventario.repository.entity;

/**
 * @author Santiago
 * 
 * */

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class TiendaProductoId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda")
	private Tienda tienda;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
	private Producto producto;
	
	

	public TiendaProductoId() {
	}

	public TiendaProductoId(Tienda tienda, Producto producto) {
		super();
		this.tienda = tienda;
		this.producto = producto;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

}
