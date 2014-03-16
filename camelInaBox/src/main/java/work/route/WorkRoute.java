/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import common.camel.PrepareMessageProcessor;
import common.fault.BusinessFault;
import common.fault.SystemFault;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import work.handler.Db4oHandler;
import work.handler.GetCurrencyOnEmailHandler;
import work.handler.IdTheftCache;
import work.service.WorkServices;

/**
 *
 * @author tg3
 */
public class WorkRoute extends RouteBase {

    /* webshop-travel
     http://localhost:8080/camelInaBox/webservices/workservices?wsdl
     */
    private String uri = "cxf:/workservices?serviceClass=" + WorkServices.class.getName();

    @Override
    public void configure() throws Exception {
        super.configure();
        onException(SystemFault.class, BusinessFault.class).stop();
        
        //interceptFrom("direct:*").to("log:CamelInaBox:IN");

        from(uri).
                to("log:Entered CamelInaBox").
                recipientList(simple("direct:${header.operationName}"));

        from("direct:CacheTest").
                bean(IdTheftCache.class, "IdentificationIdTheftValidation").
                to("log:END").end();

        from("direct:getCurrencyOnEmail").to("direct:csl:getCurrencyOnEmail").end();

        from("direct:saveTotDb4o").
                bean(Db4oHandler.class, "saveTotDb4o").
                to("log:END").end();

        from("direct:gettDb4o").
                bean(Db4oHandler.class, "gettDb4o").
                to("log:END").end();

        from("direct:Ping").
                //to("direct:esl:gmail").
                process(new Processor() {
            public void process(Exchange exchng) throws Exception {
                exchng.getOut().setBody("Ok-autostart");
            }
        }).end();

        from("direct:csl:getCurrencyOnEmail").
                to("bean-validator://x").
                bean(GetCurrencyOnEmailHandler.class, "valideringAnalyse").
                process(new PrepareMessageProcessor()).
                bean(GetCurrencyOnEmailHandler.class, "sendemail").
                /*
                 process(new Processor() {
                 public void process(Exchange exchng) throws Exception {
                 exchng.getIn().getHeaders().clear(); //DETTE ER VIKTIG ELLERS SÅ tar den med all header info (xml dokument!)
                 exchng.getIn().setHeader("subject", constant("Dette er en test"));
                 exchng.getIn().setHeader("from", constant("dragerot@gmail.com")); //?to=dragerot@gmail.com&from=tg3@ifint.biz
                 exchng.getIn().setHeader("to", constant("tore.gard.andersen@if.no"));
                 exchng.getIn().setHeader("contentType", constant("text/html")); // text/html text/plain
                 exchng.getIn().setHeader("body", constant("Heisan")); 
                 exchng.getIn().setHeader("alternativeBodyHeader","CamelMailAlternativeBody"); //MAIL_ALTERNATIVE_BODY 
                 exchng.getIn().setHeader("CamelMailAlternativeBody","ALLE DER UTE, HALLO alterantiv");
                 exchng.getIn().setBody("<html><body>ALLE DER UTE, HALLO </body></html>", String.class);
                 }             
                 })*/
                
                recipientList().expression(simple("smtp://${in.header.emailhost}")). //recipientList().el("smtp://"+simple("${in.header.emailhost}")//to("smtp://"+simple("${in.header.emailhost}")). //to("smtp:mock").//to("smtp://smtp.ifint.biz").
                to("direct:esl:getCurrencyOnEmail");
        //.to("gmail://dragerot@gmail.com").

         from("direct:sendMessage").to("direct:esl:sendMessage").end();
          
         
        from("direct:sjekkOmEnumVirker").to("direct:esl:sjekkOmEnumVirker").end();
               
         
    }
}
