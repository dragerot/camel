
package common.info;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemInfo", namespace = "http://common/info", propOrder = {
    "requestIdentifier",
    "sessionIdentifier",
    "serviceClient"
})
public class SystemInfo {

    @XmlElement(name = "RequestIdentifier", required = true)
    protected String requestIdentifier;
    @XmlElement(name = "SessionIdentifier", required = true)
    protected String sessionIdentifier;
    @XmlElement(name = "ServiceClient", required = true)
    protected ServiceClientType serviceClient;

    /**
     * Gets the value of the requestIdentifier property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    /**
     * Sets the value of the requestIdentifier property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRequestIdentifier(String value) {
        this.requestIdentifier = value;
    }

    /**
     * Gets the value of the sessionIdentifier property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Sets the value of the sessionIdentifier property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

    /**
     * Gets the value of the serviceClient property.
     *
     * @return
     *     possible object is
     *     {@link ServiceClientType }
     *
     */
    public ServiceClientType getServiceClient() {
        return serviceClient;
    }

    /**
     * Sets the value of the serviceClient property.
     *
     * @param value
     *     allowed object is
     *     {@link ServiceClientType }
     *
     */
    public void setServiceClient(ServiceClientType value) {
        this.serviceClient = value;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "requestIdentifier='" + requestIdentifier + '\'' +
                ", sessionIdentifier='" + sessionIdentifier + '\'' +
                ", serviceClient=" + serviceClient +
                '}';
    }
}
