
package org.datacontract.schemas._2004._07.wcfserviceenumvalues;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KundeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KundeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Privat"/>
 *     &lt;enumeration value="Commercial"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KundeType")
@XmlEnum
public enum KundeType {

    @XmlEnumValue("Privat")
    PRIVAT("Privat"),
    @XmlEnumValue("Commercial")
    COMMERCIAL("Commercial");
    private final String value;

    KundeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KundeType fromValue(String v) {
        for (KundeType c: KundeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
