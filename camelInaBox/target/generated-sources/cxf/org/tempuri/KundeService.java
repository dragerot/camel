package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-12-20T14:28:21.556+01:00
 * Generated source version: 2.6.1
 * 
 */
@WebServiceClient(name = "KundeService", 
                  wsdlLocation = "file:/E:/utv/git/camel/camelInaBox/src/main/resources/wsdl/kundeservice/KundeService_1.wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class KundeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "KundeService");
    public final static QName BasicHttpBindingIKunde = new QName("http://tempuri.org/", "BasicHttpBinding_IKunde");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/utv/git/camel/camelInaBox/src/main/resources/wsdl/kundeservice/KundeService_1.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(KundeService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/E:/utv/git/camel/camelInaBox/src/main/resources/wsdl/kundeservice/KundeService_1.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public KundeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public KundeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public KundeService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public KundeService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public KundeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public KundeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IKunde
     */
    @WebEndpoint(name = "BasicHttpBinding_IKunde")
    public IKunde getBasicHttpBindingIKunde() {
        return super.getPort(BasicHttpBindingIKunde, IKunde.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IKunde
     */
    @WebEndpoint(name = "BasicHttpBinding_IKunde")
    public IKunde getBasicHttpBindingIKunde(WebServiceFeature... features) {
        return super.getPort(BasicHttpBindingIKunde, IKunde.class, features);
    }

}
