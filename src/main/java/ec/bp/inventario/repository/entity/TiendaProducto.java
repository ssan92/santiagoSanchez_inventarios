/**
 * 
 */
package ec.bp.inventario.repository.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Santiago
 *
 */
@Entity
@Table(name = "Tienda_Producto")
public class TiendaProducto {
	
	@EmbeddedId
	private TiendaProductoId id;

	public TiendaProductoId getId() {
		return id;
	}

	public void setId(TiendaProductoId id) {
		this.id = id;
	}

	public TiendaProducto(TiendaProductoId id) {
		this.id = id;
	}

	public TiendaProducto() {
	}
	
	

}
