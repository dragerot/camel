package common.exception;

import common.fault.SystemFault;
import common.info.SystemInfo;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract class for top level exception handler. Defaults to SystemException if not
 * resolved by other exception handlers.
 *
 * @author fh6
 */
public abstract class ExceptionHandler implements Processor {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public abstract void process(Exchange exchange) throws Exception;

    protected void throwGenericSystemException(Throwable throwable, SystemInfo systemInfo, String faultIdentifier){
        logException(throwable, systemInfo);
        SystemFault systemException = new SystemFault();
        common.fault.types.SystemFault systemFault=new common.fault.types.SystemFault();
        systemFault.setSystemInfo(systemInfo);
        systemFault.setFaultIdentifier(faultIdentifier);
        systemFault.setTechnicalMessage("Internal error");
        throw systemException;
    }

    protected void logException(Throwable throwable, SystemInfo systemInfo){
        String text = "Got exception: " + (systemInfo != null ? systemInfo : "no_system_info");
        logger.error(text, throwable);
    }
}
