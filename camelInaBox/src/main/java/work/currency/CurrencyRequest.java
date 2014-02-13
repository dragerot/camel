/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.currency;

import common.info.SystemInfo;
import domain.insuranceParty.InsurancePartyLookup;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    "manufacturer",
    "insurancePartyLookup",
    "options",
    "systemInfo"
})
public class CurrencyRequest {
    @XmlElement(name = "Manufacturer")
    @NotNull
    @Size(min = 5, max = 14)
    private String manufacturer;
    @XmlElement(name = "InsurancePartyLookup", required = true)
    private InsurancePartyLookup insurancePartyLookup;
    @XmlElement(name = "Options", required = true)
    private Options options;
    @XmlElement(name = "SystemInfo", required = true)
    private SystemInfo systemInfo;
}