/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@XmlType(name = "A", namespace = "http://work/reportendpoint", propOrder = {
    "nokkeleId"
})
public class A extends AbstractMessageReportData{
    @XmlElement(name = "NokkeleId", required = true)
    String nokkeleId;

    public String getNokkeleId() {
        return nokkeleId;
    }

    public void setNokkeleId(String nokkeleId) {
        this.nokkeleId = nokkeleId;
    }
    
    
}
