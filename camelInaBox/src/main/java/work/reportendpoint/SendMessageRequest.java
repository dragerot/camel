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
@XmlType(name = "SendMessageRequest", namespace = "http://work/reportendpoint", propOrder = {
    "reportId",
    "messageData"
})
  public class SendMessageRequest {
    @XmlElement(name = "ReportId",  required = true)
    private String reportId;
    @XmlElement(name = "MessageData",  required = true)
    private AbstractMessageReportData messageData;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public AbstractMessageReportData getMessageData() {
        return messageData;
    }

    public void setMessageData(AbstractMessageReportData messageData) {
        this.messageData = messageData;
    }
    
    

         
}
