/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.fault.types;

import common.info.SystemInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessFault", propOrder = {
    "faultIdentifier","technicalMessage","userFriendlyMessage","payload","systemInfo"
})
public class BusinessFault {
    @XmlElement(name = "FaultIdentifier",required = true )
    protected String faultIdentifier;
    @XmlElement(name = "TechnicalMessage",required = true)
    protected String technicalMessage;
    @XmlElement(name = "UserFriendlyMessage")
    protected String userFriendlyMessage;
    @XmlElement(name = "Payload")
    protected Object payload;
    @XmlElement(name = "SystemInfo")
    protected SystemInfo systemInfo;
    
    public String getFaultIdentifier() {
        return faultIdentifier;
    }

    public void setFaultIdentifier(String faultIdentifier) {
        this.faultIdentifier = faultIdentifier;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }

    public void setUserFriendlyMessage(String userFriendlyMessage) {
        this.userFriendlyMessage = userFriendlyMessage;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
    
}
