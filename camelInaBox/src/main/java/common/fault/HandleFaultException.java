/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.fault;

import common.info.SystemInfo;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.camel.Exchange;

/**
 *
 * @author TG3
 */
public class HandleFaultException {
    
    private String faultIdentifier="";
    private final String INTERNAL_ERROR="Internal error";
    
    public void HandleRuntimeException(Exchange exchange) {
        String _faultIdentifier= 
                (faultIdentifier==null || faultIdentifier.trim().length()==0) ? INTERNAL_ERROR : faultIdentifier;
        FaultUtility faultUtility = new FaultUtility();
        String traceText = "";
        Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        if (caused != null && caused.getStackTrace() != null) {
            StringWriter errors = new StringWriter();
            caused.printStackTrace(new PrintWriter(errors));
            traceText = errors.toString();
        }
        Object object = exchange.getIn().getHeader("request");
        String _requestInfo=object!=null ? object.getClass().getName()+":::"+object.toString() : "";
        SystemInfo systemInfo = exchange.getIn().getHeader("systemInfo",SystemInfo.class);
        String requestIdentifier = systemInfo!=null ? systemInfo.getRequestIdentifier() : "";
        String sessionIdentifier = systemInfo!=null ? systemInfo.getSessionIdentifier() : "";
        String channel=  systemInfo!=null ? systemInfo.getServiceClient().toString() : "";
        
        throw faultUtility.getSystemFaultException(
                _faultIdentifier, 
                _faultIdentifier, 
                "Request:"+_requestInfo + " Tracelog:"+ traceText, 
                requestIdentifier, 
                sessionIdentifier, 
                "", 
               systemInfo.getServiceClient());
      }
    
     public void HandleSystemException(Exchange exchange,String afaultIdentifier) {
            faultIdentifier=afaultIdentifier;
            HandleRuntimeException(exchange);
      }
}
