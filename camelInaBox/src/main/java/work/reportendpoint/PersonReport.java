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
@XmlType(name = "PersonReport", namespace = "http://work/reportendpoint", propOrder = {
    "ssn",
    "name"  
})
public class PersonReport extends AbstractMessageReportData{
    @XmlElement(name = "Ssn", required = true)
    private String ssn;
    @XmlElement(name = "Name", required = true)
    private String name;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
