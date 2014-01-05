/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.currency;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlType(name = "CurrencyType", namespace = "http://net/toregard/work/currency")
@XmlEnum
public enum CurrencyType {
 
    @XmlEnumValue("NOK")
    NOK("NOK"),
    @XmlEnumValue("EUR")
    EUR("USD"),
     @XmlEnumValue("USD")
    USR("USD");
    
    private final String value;

    CurrencyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CurrencyType fromValue(String v) {
        for (CurrencyType c: CurrencyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}