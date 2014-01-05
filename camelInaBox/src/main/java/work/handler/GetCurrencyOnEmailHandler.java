/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.handler;

import org.apache.camel.Exchange;
import org.apache.camel.language.Constant;
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

    public void sendemail(Exchange exchng) throws Exception {
        exchng.getIn().getHeaders().clear(); //DETTE ER VIKTIG ELLERS SÅ tar den med all header info (xml dokument!)
        //exchng.getIn().setHeader("email.host",EMAIL_HOST);
        exchng.getIn().setHeader("emailhost", emailhost);
        exchng.getIn().setHeader("subject", "Dette er en test");
        exchng.getIn().setHeader("from", "dragerot@gmail.com"); //?to=dragerot@gmail.com&from=tg3@ifint.biz
        exchng.getIn().setHeader("to", "tore.gard.andersen@if.no");
        exchng.getIn().setHeader("contentType", "text/html"); // text/html text/plain
        exchng.getIn().setHeader("body", "Heisan");
        exchng.getIn().setHeader("alternativeBodyHeader", "CamelMailAlternativeBody"); //MAIL_ALTERNATIVE_BODY 
        exchng.getIn().setHeader("CamelMailAlternativeBody", "ALLE DER UTE, HALLO alterantiv");
        exchng.getIn().setBody("<html><body>ALLE DER UTE, HALLO </body></html>", String.class);
    }

    public void CurrencyRequest(Exchange exchange) throws Exception {
    }

    public void CurrencyResponse(Exchange exchange) throws Exception {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setResponseStatus(ResponseStatusType.SENT_EMAIL);
        exchange.getIn().setBody(currencyResponse);
    }
}