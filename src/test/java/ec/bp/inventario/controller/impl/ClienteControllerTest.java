/**
 * 
 */
package ec.bp.inventario.controller.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.bp.inventario.constant.InventarioConstant;
import ec.bp.inventario.service.dto.ClienteDTO;

/**
 * @author Santiago
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

	
	HttpHeaders headers;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.headers = new HttpHeaders();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		this.headers = null;
	}

	/**
	 * Test method for {@link ec.bp.inventario.controller.impl.ClienteController#consultaAllCliente()}.
	 * @throws Exception 
	 */
	@Test
	void testConsultaAllCliente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/cliente")
				.headers(this.headers)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link ec.bp.inventario.controller.impl.ClienteController#crearCliente(ec.bp.inventario.service.dto.ClienteDTO)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	void testCrearCliente() throws JsonProcessingException, Exception {
		ClienteDTO dto=new ClienteDTO();
		dto.setIdCliente(Long.valueOf(0));
		dto.setFoto(null);
		dto.setIdentificacion("123");
		dto.setNombre("Santiago");
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/cliente")
				.content(objectMapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.headers(this.headers)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link ec.bp.inventario.controller.impl.ClienteController#actualizarCliente(ec.bp.inventario.service.dto.ClienteDTO)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	void testActualizarCliente() throws JsonProcessingException, Exception {
		ClienteDTO dto=new ClienteDTO();
		dto.setIdCliente(Long.valueOf(10));
		dto.setFoto(null);
		dto.setIdentificacion("123");
		dto.setNombre("Santiago");
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/cliente")
				.content(objectMapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.headers(this.headers)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].detalle", Matchers.containsString(InventarioConstant.NO_CLIENTE+"10")));;
	}

	/**
	 * Test method for {@link ec.bp.inventario.controller.impl.ClienteController#borrarCliente(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testBorrarCliente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				.delete("/cliente/10")
				.headers(this.headers)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
