/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.insuranceParty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlType(name = "InsurancePartyLookup",namespace = "http://net/toregard/domain/insuranceparty" , 
        propOrder = {
    "ssn",
    "givenName",
    "familyName"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class InsurancePartyLookup {
    @XmlElement(name = "Ssn")
    private String ssn;
    @XmlElement(name = "GivenName")
    private String givenName;
    @XmlElement(name = "FamilyName")
    private String familyName;
    
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "InsurancePartyLookup{" + "ssn=" + ssn + ", givenName=" + givenName + ", familyName=" + familyName + '}';
    }
    
}
