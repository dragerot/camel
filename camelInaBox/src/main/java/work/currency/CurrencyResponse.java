/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurrencyResponse",namespace = "http://net/toregard/work/currency", propOrder = {
    "responseStatus"
})
public class CurrencyResponse {
    @XmlElement(name = "ResponseStatus",  required = true)
    private ResponseStatusType responseStatus;

    public ResponseStatusType getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatusType responseStatus) {
        this.responseStatus = responseStatus;
    }

 
    
    
}
