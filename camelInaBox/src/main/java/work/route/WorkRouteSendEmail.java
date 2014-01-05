/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import common.fault.BusinessFault;
import common.fault.SystemFault;
import org.apache.camel.builder.RouteBuilder;
import work.handler.GetCurrencyOnEmailHandler;
/**
 *
 * @author TG3
 */
public class WorkRouteSendEmail extends RouteBase {

                    
    @Override
    public void configure() throws Exception {
       super.configure();
       onException(SystemFault.class, BusinessFault.class).stop();
      
               
        from("direct:esl:getCurrencyOnEmail").
        bean(GetCurrencyOnEmailHandler.class,"CurrencyResponse");
        
    }
    
}
