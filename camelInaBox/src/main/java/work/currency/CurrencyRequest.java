/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.currency;

import common.info.SystemInfo;
import domain.insuranceParty.InsurancePartyLookup;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuoteRequest", namespace = "http://net/toregard/work/currency", propOrder = {
    "insurancePartyLookup",
    "options",
    "systemInfo"
})
public class CurrencyRequest {

    @XmlElement(name = "InsurancePartyLookup", required = true)
    private InsurancePartyLookup insurancePartyLookup;
    @XmlElement(name = "Options", required = true)
    private Options options;
    @XmlElement(name = "SystemInfo", required = true)
    private SystemInfo systemInfo;
}