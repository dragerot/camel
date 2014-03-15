package work.reportendpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMessageReportData", namespace = "http://work/reportendpoint", propOrder = {
    "messageType"
})
@XmlSeeAlso({CarReport.class,PersonReport.class,A.class})
abstract class AbstractMessageReportData {
    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    
  }