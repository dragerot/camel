/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import common.fault.BusinessFault;
import common.fault.SystemFault;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import work.handler.HandleCache;
import work.handler.IdTheftCache;
import work.service.CacheTest;

/**
 *
 * @author tg3
 */
public class WorkRoute extends RouteBuilder  {

    /* webshop-travel
     http://localhost:8080/camelInaBox/webservices/cachetest?wsdl
     */
    private String uri = "cxf:/cachetest?serviceClass=" + CacheTest.class.getName();
  
     
    @Override
    public void configure() throws Exception {
         onException(SystemFault.class, BusinessFault.class).stop();
    
         interceptFrom("direct:*").to("log:CamelInaBox:IN");
    
         from(uri).
                to("log:Entered CamelInaBox").
                recipientList(simple("direct:${header.operationName}"));  
       
        from("direct:Ping").
                process(new Processor() {

             public void process(Exchange exchng) throws Exception {
                 exchng.getOut().setBody("Ok");
             }
         }).end();
                 
        
        from("direct:Run").
                bean(IdTheftCache.class,"IdentificationIdTheftValidation").
                to("log:END").end();
                }
    
}
