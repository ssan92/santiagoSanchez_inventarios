/**
 * 
 */
package ec.bp.inventario.service.command.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import ec.bp.inventario.service.command.ifc.ICommand;
import ec.bp.inventario.service.command.ifc.IParam;
import ec.bp.inventario.service.command.model.Stock;

/**
 * @author Santiago
 *
 */
@Component
public class ConsultaAsincStockMockCommand implements ICommand {
	
	@Value("${UriConsultaStock5}")
	private String uri;

	@Override
	public Object execute(IParam parametro){
		AsyncRestTemplate asyncRestTemplate=new AsyncRestTemplate();
		HttpEntity<String>  request = new HttpEntity<>("");
        ListenableFuture<ResponseEntity<Stock>> responseFuture
                = asyncRestTemplate.exchange(uri, HttpMethod.GET, request, new ParameterizedTypeReference<Stock>() {
				});
        responseFuture.addCallback(new ListenableFutureCallback<ResponseEntity<Stock>>() {

			@Override
			public void onSuccess(ResponseEntity<Stock> result) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				
			}
		});
        return responseFuture;
	}

}
