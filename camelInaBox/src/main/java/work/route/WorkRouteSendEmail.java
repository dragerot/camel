/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import common.camel.PrepareMessageProcessor;
import common.fault.BusinessFault;
import common.fault.SystemFault;
import org.apache.camel.Predicate;
import static org.apache.camel.builder.ProcessorBuilder.setHeader;
import org.apache.camel.builder.RouteBuilder;
import work.handler.GetCurrencyOnEmailHandler;
import work.handler.sendMessageHandler;
import work.reportendpoint.SendMessageRequest;

/**
 *
 * @author TG3
 */
public class WorkRouteSendEmail extends RouteBase {
Predicate isCarReport = simple("${in.header.messageData} is 'work.reportendpoint.CarReport'");
Predicate isPersonReport = simple("${in.header.messageData} is 'work.reportendpoint.PersonReport'");     
Predicate isA = simple("${in.header.messageData} is 'work.reportendpoint.A'");  

    @Override
    public void configure() throws Exception {
       super.configure();
       onException(SystemFault.class, BusinessFault.class).stop();
      
               
        from("direct:esl:getCurrencyOnEmail").
        bean(GetCurrencyOnEmailHandler.class,"CurrencyResponse");
         
        from("direct:esl:sendMessage").
        process(new PrepareMessageProcessor()). 
        bean(sendMessageHandler.class,"pre").        
        setHeader("reportId", simple("${body.reportId}", String.class)).
        setHeader("messageData", simple("${body.messageData}")).   
         choice().when(isA).to("log:A***********"). 
        bean(sendMessageHandler.class,"a").end().          
        choice().when(isPersonReport).to("log:PersonReport***********"). 
        bean(sendMessageHandler.class,"personReport").end().  
        choice().when(isCarReport).to("log:CarReport***********"). 
        bean(sendMessageHandler.class,"carReport").
        end();          
                
    }
    
    /*
    sendMessageRequest.messageData
                   CarReport.bilnummer
				   PersonReport.ssn 
    
     Predicate structurert = simple("${in.header.enumRequest.struktur.structurert}");
    
    @Override
    public void configure() throws Exception {
       super.configure();
       onException(SystemFault.class, BusinessFault.class).stop();
   
       from("direct:esl:sjekkOmEnumVirker").
       choice().when(structurert).
       bean(SjekkOmEnumVirkerHandler.class,"requestA").
       otherwise().
       bean(SjekkOmEnumVirkerHandler.class,"requestB").
       end();   
    */
    
}
