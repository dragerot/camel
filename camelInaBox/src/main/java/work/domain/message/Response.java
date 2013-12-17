/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.domain.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tg3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "status"})
public class Response {
     @XmlElement(name = "StatusType",  required = true)
    private StatusType status;

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }
    
    
}
