/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.handler;

import org.apache.camel.Exchange;
import work.domain.message.Request;
import work.domain.message.Response;
import work.domain.message.StatusType;

/**
 *
 * @author tg3
 */
public class HandleCache {
    
    public void RequestTransform(Exchange exchange) throws Exception {
         Request request=exchange.getIn().getBody(Request.class);
         Response response=new Response();
         response.setStatus(StatusType.Ok);
         exchange.getIn().setBody(response);
    }
    
     public void ResponseTransform(Exchange exchange) throws Exception {
        
    }
}
