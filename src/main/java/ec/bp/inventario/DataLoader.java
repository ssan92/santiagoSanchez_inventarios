package ec.bp.inventario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ec.bp.inventario.repository.entity.Producto;
import ec.bp.inventario.repository.entity.Tienda;
import ec.bp.inventario.repository.entity.TiendaProducto;
import ec.bp.inventario.repository.entity.TiendaProductoId;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.repository.ifc.ITiendaProductoDAO;
import ec.bp.inventario.service.command.consumer.ConsultaProductoMockCommand;
import ec.bp.inventario.service.command.model.ConsultaProductoMockRespuesta;
import ec.bp.inventario.util.Convert;

/**
 * @author Santiago
 *
 */
@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private IProductoDAO repository;
	
	@Autowired
	private ConsultaProductoMockCommand command;
	
	@Autowired
	private ITiendaProductoDAO repoTiendaProd;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/**
		 * Se envia null porque el servicio a consumir no necesita parametros
		 * */
		ConsultaProductoMockRespuesta mock= (ConsultaProductoMockRespuesta) command.execute(null);
		repository.saveAll(Convert.mockToEntity(mock.getProds()));
		
		//por pruebas //comentar antes de enviar
		cargaDatosPrueba();
		
	}
	
	//por pruebas //comentar antes de enviar
	private void cargaDatosPrueba() {
		List<TiendaProducto> list=new ArrayList<TiendaProducto>();
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(1)), new Producto(Long.valueOf(1)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(1)), new Producto(Long.valueOf(2)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(1)), new Producto(Long.valueOf(3)))));
		
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(2)), new Producto(Long.valueOf(1)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(2)), new Producto(Long.valueOf(2)))));
		
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(3)), new Producto(Long.valueOf(1)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(3)), new Producto(Long.valueOf(2)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(3)), new Producto(Long.valueOf(3)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(3)), new Producto(Long.valueOf(4)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(3)), new Producto(Long.valueOf(5)))));
		
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(4)), new Producto(Long.valueOf(1)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(4)), new Producto(Long.valueOf(2)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(4)), new Producto(Long.valueOf(3)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(4)), new Producto(Long.valueOf(4)))));
		list.add(new TiendaProducto(new TiendaProductoId(new Tienda(Long.valueOf(4)), new Producto(Long.valueOf(5)))));
		
		repoTiendaProd.saveAll(list);
	}
}
