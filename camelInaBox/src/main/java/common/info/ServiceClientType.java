
package common.info;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ServiceClientType", namespace = "http://common/info")
@XmlEnum
public enum ServiceClientType {

    @XmlEnumValue("WebShop")
    WEB_SHOP("WebShop"),
    @XmlEnumValue("Finansportalen")
    FINANSPORTALEN("Finansportalen"),
    @XmlEnumValue("Partnerportal")
    PARTNERPORTAL("Partnerportal"),
    @XmlEnumValue("BilForhandler")
    BIL_FORHANDLER("BilForhandler");
    private final String value;

    ServiceClientType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceClientType fromValue(String v) {
        for (ServiceClientType c: ServiceClientType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
