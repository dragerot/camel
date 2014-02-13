/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.handler;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Value;
import work.currency.CurrencyResponse;
import work.currency.ResponseStatusType;

/**
 *
 * @author TG3
 */
public class GetCurrencyOnEmailHandler {

    private @Value("${email.host}")
    String emailhost;
     private @Value("${creditcheck.recipient}")
    String recipient;
      private @Value("${creditcheck.sender}")
    String sender;
       private @Value("${creditcheck.subject}")
    String subject;
        private @Value("${creditcheck.prefix}")
    String prefix;

    public void valideringAnalyse(Exchange exchange) throws Exception {
        System.out.println("E");
    }   
    
    public void sendemail(Exchange exchange) throws Exception {
        //exchange.getIn().getHeaders().clear(); //DETTE ER VIKTIG ELLERS SÅ tar den med all header info (xml dokument!)
        ////exchng.getIn().setHeader("email.host",EMAIL_HOST);
        //EmailRequest  emailRequest=exchange.getIn().getBody(EmailRequest.class);
        exchange.getIn().setHeader("emailhost", emailhost);
        exchange.getIn().setHeader("subject", subject);
        exchange.getIn().setHeader("from", sender); //?to=dragerot@gmail.com&from=tg3@ifint.biz
        exchange.getIn().setHeader("to", recipient);
        exchange.getIn().setHeader("contentType", "text/html"); // text/html text/plain
        exchange.getIn().setHeader("body", "Heisan");
        exchange.getIn().setHeader("alternativeBodyHeader", "CamelMailAlternativeBody"); //MAIL_ALTERNATIVE_BODY 
        exchange.getIn().setHeader("CamelMailAlternativeBody", "ALLE DER UTE, HALLO alterantiv");
        exchange.getIn().setBody("<html><body>ALLE DER UTE, HALLO </body></html>", String.class);
    }

    public void CurrencyRequest(Exchange exchange) throws Exception {
    }

    public void CurrencyResponse(Exchange exchange) throws Exception {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setResponseStatus(ResponseStatusType.SENT_EMAIL);
        exchange.getIn().setBody(currencyResponse);
    }
}