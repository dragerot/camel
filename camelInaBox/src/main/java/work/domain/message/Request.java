/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.domain.message;

import common.info.SystemInfo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tg3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "ssn",
    "familyName",
    "systemInfo"})
public class Request {

    @XmlElement(name = "Ssn", required = true)
    private String ssn;
    @XmlElement(name = "FamilyName", required = true)
    private String familyName;
    @XmlElement(name = "SystemInfo", required = true)
    private SystemInfo systemInfo;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }
}
