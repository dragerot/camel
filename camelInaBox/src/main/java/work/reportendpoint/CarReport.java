package work.reportendpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarReport", namespace = "http://work/reportendpoint", propOrder = {
    "bilnummer",
    "farge"  
})
public class CarReport extends AbstractMessageReportData {
    @XmlElement(name = "Bilnummer", required = true)
    private String bilnummer;
    @XmlElement(name = "Farge", required = true)
    private String farge;

    public String getBilnummer() {
        return bilnummer;
    }

    public void setBilnummer(String bilnummer) {
        this.bilnummer = bilnummer;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }
    
    
    
    
}
