/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlType(name = "Options", namespace = "http://net/toregard/work/currency",
        propOrder = {
    "fromCurrency",
    "toCurrency"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Options {

    @XmlElement(name = "FromCurrency")
    private CurrencyType fromCurrency;
    @XmlElement(name = "ToCurrency")
    private CurrencyType toCurrency;

    public CurrencyType getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyType FromCurrency) {
        this.fromCurrency = FromCurrency;
    }

    public CurrencyType getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyType ToCurrency) {
        this.toCurrency = ToCurrency;
    }
}
