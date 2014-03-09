package work.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)           
@XmlType(name = "HouseRequest", namespace = "http://work/house", propOrder = {
    "ssn",
    "postalCode"
})
public class HouseRequest {
    @XmlElement(name = "Ssn",  required = true)
    String ssn;
     @XmlElement(name = "PostalCode",  required = true)
    String postalCode;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
     
}
