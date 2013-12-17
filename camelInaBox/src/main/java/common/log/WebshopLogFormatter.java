package common.log;


import org.apache.camel.Exchange;
import org.apache.camel.spi.ExchangeFormatter;

/**
 * Custom exchange log formatter.
 *
 * @author fh6
 */
public class WebshopLogFormatter implements ExchangeFormatter {

    @Override
    public String format(Exchange exchange) {
        StringBuilder sb = new StringBuilder();
        sb.append("Exchange[")
                .append("\n Endpoint: ").append(getInterceptedEndpoint(exchange))
                .append("\n SystemInfo: ").append(getSystemInfo(exchange))
                .append("\n Body: ").append(getBody(exchange))
                .append("\n]");
        return sb.toString();
    }

    private String getSystemInfo(Exchange exchange){
        Object systemInfo = exchange.getIn().getHeader("systemInfo");
        if(systemInfo != null){
            return systemInfo.toString();
        }else{
            return "-";
        }
    }

    private String getInterceptedEndpoint(Exchange exchange){
        Object endpoint = exchange.getIn().getHeader(Exchange.INTERCEPTED_ENDPOINT);
        if(endpoint != null){
            return endpoint.toString();
        }else{
            return "-";
        }
    }

    private String getBody(Exchange exchange){
        Object body = exchange.getIn().getBody();
        if(body != null){
            return body.toString();
        }else{
            return "null";
        }
    }
}
