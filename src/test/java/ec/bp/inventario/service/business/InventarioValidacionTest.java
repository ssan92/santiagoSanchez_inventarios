/**
 * 
 */
package ec.bp.inventario.service.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.repository.entity.Cliente;
import ec.bp.inventario.repository.entity.Producto;
import ec.bp.inventario.repository.entity.Tienda;
import ec.bp.inventario.repository.ifc.IClienteDAO;
import ec.bp.inventario.repository.ifc.IProductoDAO;
import ec.bp.inventario.repository.ifc.ITiendaDAO;
import ec.bp.inventario.repository.ifc.ITiendaProductoDAO;
import ec.bp.inventario.service.command.consumer.ConsultaAsincStockMockCommand;
import ec.bp.inventario.service.command.consumer.ConsultaStockMockCommand;
import ec.bp.inventario.service.dto.ClienteDTO;
import ec.bp.inventario.service.dto.Orden;
import ec.bp.inventario.service.dto.PedidoDTO;
import ec.bp.inventario.service.dto.ProductoOrden;
import ec.bp.inventario.util.exception.ApplicationException;

/**
 * @author Santiago
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class InventarioValidacionTest {

	/**
	 * Test method for {@link ec.bp.inventario.service.business.InventarioValidacion#validarStock(int)}.
	 */
	@Test
	void testValidarStock() {
		 
		try {
			InventarioValidacion.validarStock(9);
		} catch (ApplicationException e) {
			assertEquals(InventarioConstant.STOCK_0, e.getMessage());
		}
	}

	/**
	 * Test method for {@link ec.bp.inventario.service.business.InventarioValidacion#validarProductos(ec.bp.inventario.repository.ifc.IProductoDAO, java.util.List)}.
	 */
	@Test
	void testValidarProductos() {
		IProductoDAO dao=Mockito.mock(IProductoDAO.class);
		Producto producto=new Producto(Long.valueOf(2));
		Optional<Producto> optional=Optional.of(producto);
		Mockito.when(dao.findById(Long.valueOf(2))).thenReturn(optional);
		Producto producto2 = null;
		try {
			producto2 = InventarioValidacion.validarProducto(dao, Long.valueOf(2));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		assertEquals(producto, producto2);
	}

	
	/**
	 * Test method for {@link ec.bp.inventario.service.business.InventarioValidacion#validarCamposCliente(ec.bp.inventario.service.dto.ClienteDTO)}.
	 */
	@Test
	void testValidarCamposCliente() {
		ClienteDTO clienteDTO=new ClienteDTO();
		clienteDTO.setNombre("santiago");
		try {
			InventarioValidacion.validarCamposCliente(clienteDTO);
		} catch (ApplicationException e) {
			assertEquals(InventarioConstant.IDENTIFICACION, e.getMessage());
		}
	}

	/**
	 * Test method for {@link ec.bp.inventario.service.business.InventarioValidacion#validarOrden(ec.bp.inventario.service.dto.PedidoDTO, ec.bp.inventario.repository.ifc.IClienteDAO, ec.bp.inventario.repository.ifc.ITiendaDAO, ec.bp.inventario.repository.ifc.IProductoDAO, ec.bp.inventario.repository.ifc.ITiendaProductoDAO)}.
	 */
	@Test
	void testValidarOrden() {
		IProductoDAO dao=Mockito.mock(IProductoDAO.class);
		IClienteDAO daoCliente=Mockito.mock(IClienteDAO.class);
		ITiendaDAO daoTienda=Mockito.mock(ITiendaDAO.class);
		ITiendaProductoDAO daoTiendaProd=Mockito.mock(ITiendaProductoDAO.class);
		
		PedidoDTO dto=new PedidoDTO();
		dto.setIdCliente(Long.valueOf(1));
		List<Orden> ordens=new ArrayList<Orden>();
		Orden orden=new Orden();
		orden.setIdTienda(Long.valueOf(2));
		
		List<ProductoOrden> ordens2=new ArrayList<ProductoOrden>();
		ProductoOrden orden2= new ProductoOrden();
		orden2.setCantidad(5);
		orden2.setIdProducto(Long.valueOf(3));
		ordens2.add(orden2);
		
		orden.setProductos(ordens2);
		ordens.add(orden);
		dto.setPedidos(ordens);
		
		Cliente cliente=new Cliente(Long.valueOf(1));
		Optional<Cliente> opCliente=Optional.of(cliente);
		Mockito.when(daoCliente.findById(Long.valueOf(1))).thenReturn(opCliente);
		
		Tienda tienda =new Tienda(Long.valueOf(2));
		Optional<Tienda> opTienda=Optional.of(tienda);
		Mockito.when(daoTienda.findById(Long.valueOf(2))).thenReturn(opTienda);
		
		
		Producto producto=new Producto(Long.valueOf(3));
		Optional<Producto> optional=Optional.empty();
		Mockito.when(dao.findById(Long.valueOf(3))).thenReturn(optional);
		try {
			InventarioValidacion.validarOrden(dto, daoCliente, daoTienda, dao, daoTiendaProd);
		} catch (ApplicationException e) {
			assertEquals(InventarioConstant.NO_PRODUCTO+producto.getIdProducto(), e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link ec.bp.inventario.service.business.InventarioValidacion#validarStockEnBodega(ec.bp.inventario.service.dto.PedidoDTO, ec.bp.inventario.repository.ifc.IProductoDAO, ec.bp.inventario.service.command.consumer.ConsultaStockMockCommand, ec.bp.inventario.service.command.consumer.ConsultaAsincStockMockCommand)}.
	 */
	@Test
	void testValidarStockEnBodega() {
		IProductoDAO dao=Mockito.mock(IProductoDAO.class);
		ConsultaStockMockCommand mockCommand=Mockito.mock(ConsultaStockMockCommand.class);
		ConsultaAsincStockMockCommand asicCommand=Mockito.mock(ConsultaAsincStockMockCommand.class);
		
		PedidoDTO dto=new PedidoDTO();
		dto.setIdCliente(Long.valueOf(1));
		List<Orden> ordens=new ArrayList<Orden>();
		Orden orden=new Orden();
		orden.setIdTienda(Long.valueOf(2));
		
		List<ProductoOrden> ordens2=new ArrayList<ProductoOrden>();
		ProductoOrden orden2= new ProductoOrden();
		orden2.setCantidad(20);
		orden2.setIdProducto(Long.valueOf(3));
		ordens2.add(orden2);
		
		orden.setProductos(ordens2);
		ordens.add(orden);
		dto.setPedidos(ordens);
		
		Producto producto=new Producto(Long.valueOf(3));
		producto.setStock(5);
		Optional<Producto> optional=Optional.of(producto);
		Mockito.when(dao.findById(Long.valueOf(3))).thenReturn(optional);
		
		try {
			InventarioValidacion.validarStockEnBodega(dto, dao, mockCommand, asicCommand);
		} catch (ApplicationException e) {
			assertEquals(InventarioConstant.NO_UNIDAD+producto.getIdProducto(), e.getMessage());
		}
	}

}
