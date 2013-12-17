/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.domain.message;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tg3
 */
@XmlRootElement(name = "StatusType", namespace = "http://domain/message")
@XmlType(name = "StatusType")
@XmlEnum
public enum StatusType {
    @XmlEnumValue("Ok")
    Ok("Ok"),
    @XmlEnumValue("TilKarantene")
    TilKarantene("TilKarantene"),
    @XmlEnumValue("Karantene")
    Karantene("Karantene"),
    @XmlEnumValue("IDTHEFT_DISABLED")
    Id_Theft_Disabled("IDTHEFT_DISABLED");
    
    private final String value;
    
     public static boolean contains(String s) 
     {
        try 
        {
            StatusType.valueOf(s);
            return true;
        } catch (Exception e) 
        {
            return false;
        }
    }
     
    StatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusType fromValue(String v) {
        for (StatusType c: StatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}