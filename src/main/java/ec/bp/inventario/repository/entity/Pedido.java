/**
 * 
 */
package ec.bp.inventario.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Santiago
 *
 */
@Entity
@Table(name = "Pedido")
@SqlResultSetMapping(name = "consultaNumeroDeTransacciones", classes = { 
		@ConstructorResult(targetClass = ReporteNumeroTransaccion.class,
				columns = {
						 @ColumnResult(name = "idTienda",type = Long.class),
						 @ColumnResult(name = "nombre",type = String.class),
						 @ColumnResult(name = "fechaTransaccion",type = Date.class),
						 @ColumnResult(name = "numeroTranssaccion",type = Long.class)
				})
		}
)
@SqlResultSetMapping(name = "consultaMontoVenta", classes = { 
		@ConstructorResult(targetClass = ReporteMontoVenta.class,
				columns = {
						 @ColumnResult(name = "idTienda",type = Long.class),
						 @ColumnResult(name = "nombre",type = String.class),
						 @ColumnResult(name = "idProducto",type = Long.class),
						 @ColumnResult(name = "nombreProducto",type = String.class),
						 @ColumnResult(name = "monto",type = Double.class)
				})
		}
)
@NamedNativeQuery(name = "Pedido.consultaNumeroDeTransacciones",query = "SELECT p.id_tienda as idTienda,t.nombre, trunc(p.fecha_transaccion) as fechaTransaccion, count(*) as numeroTranssaccion FROM PEDIDO p, TIENDA t where p.id_tienda=t.id_tienda  group by p.id_tienda, t.nombre, trunc(p.fecha_transaccion)  order by p.id_tienda,  trunc(p.fecha_transaccion)  ",resultSetMapping ="consultaNumeroDeTransacciones" )
@NamedNativeQuery(name = "Pedido.consultaMontoVenta",query = "SELECT p.id_tienda as idTienda,t.nombre, pr.id_producto as idProducto, pr.nombre as nombreProducto , sum(pr.precio) as monto FROM PEDIDO p, TIENDA t, PRODUCTO pr where p.id_tienda=t.id_tienda  and p.id_producto=pr.id_producto group by p.id_tienda, t.nombre, pr.id_producto, pr.nombre  order by p.id_tienda", resultSetMapping = "consultaMontoVenta" )
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long idPedido;
	
	@Column(length = 30)
	private String codigo;
	
	private Integer cantidad;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name="fecha_transaccion")
	private Date fechaTransaccion;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
	private Producto producto;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda")
	private Tienda tienda;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	

}
