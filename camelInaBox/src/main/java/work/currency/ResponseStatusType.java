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

@XmlType(name = "ResponseStatusType", namespace = "http://net/toregard/work/currency")
@XmlEnum
public enum ResponseStatusType {
 
    @XmlEnumValue("SentEmail")
    SENT_EMAIL("SentEmail"),
    @XmlEnumValue("NotSentEmail")
    NOT_SENT_EMAIL("NotSentEmail");
    
    private final String value;

    ResponseStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseStatusType fromValue(String v) {
        for (ResponseStatusType c: ResponseStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}