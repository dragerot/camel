
package common.fault;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;


@XmlType(name = "", propOrder = {"systemFault"})
@XmlRootElement(name = "SystemFault", namespace = "http://common/fault")
@WebFault(name = "SystemFault",targetNamespace = "http://common/fault")
public class SystemFault extends RuntimeException{
    
    private common.fault.types.SystemFault systemFault;

    public SystemFault() {
        super();
    }
    
    public SystemFault(String message) {
        super(message);
    }

    public SystemFault(String message, Throwable cause) {
        super(message, cause);
    }

     public SystemFault(String message, common.fault.types.SystemFault systemFault) {
        super(message);
        this.systemFault = systemFault;
    }

    public SystemFault(String message, common.fault.types.SystemFault systemFault, Throwable cause) {
        super(message, cause);
        this.systemFault = systemFault;
    }

    public common.fault.types.SystemFault getFaultInfo() {
        return this.systemFault;
    }
    
    public common.fault.types.SystemFault setFaultInfo(common.fault.types.SystemFault systemFault) {
        return this.systemFault=systemFault;
    }    
}
