
package common.fault;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;

@XmlType(name = "", propOrder = {"businessFault"})
@WebFault(name = "BusinessFault", targetNamespace = "http://common/fault")
@XmlRootElement(name = "BusinessFault", namespace = "http://common/fault")
public class BusinessFault extends RuntimeException{
    private common.fault.types.BusinessFault businessFault;

     public BusinessFault() {
       super();
    }

    public BusinessFault(String message) {
        super(message);
    }

    public BusinessFault(String message, Throwable cause) {
        super(message, cause);
    }
    
     public BusinessFault(String message, common.fault.types.BusinessFault businessFault) {
        super(message);
        this.businessFault = businessFault;
    }

    public BusinessFault(String message, common.fault.types.BusinessFault businessFault, Throwable cause) {
        super(message, cause);
        this.businessFault = businessFault;
    }

    public common.fault.types.BusinessFault getFaultInfo() {
        return this.businessFault;
    }
    
    public common.fault.types.BusinessFault setFaultInfo(common.fault.types.BusinessFault businessFault) {
        return this.businessFault=businessFault;
    }
  
}
