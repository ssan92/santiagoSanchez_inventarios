/**
 * 
 */
package ec.bp.inventario.service.command.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.bp.inventario.service.command.ifc.ICommand;
import ec.bp.inventario.service.command.ifc.IParam;
import ec.bp.inventario.service.command.model.Stock;

/**
 * @author Santiago
 *
 */
@Component
public class ConsultaStockMockCommand implements ICommand {
	
	@Value("${UriConsultaStock10}")
	private String uri;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Object execute(IParam parametro) throws JsonProcessingException {
		Stock response=new Stock();
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity = new HttpEntity<>("");
		response = restTemplate.exchange(uri, HttpMethod.GET, entity,
				new ParameterizedTypeReference<Stock>() {
				}).getBody();
		//System.out.println(objectMapper.writeValueAsString(response));
		return response;
	}

}
