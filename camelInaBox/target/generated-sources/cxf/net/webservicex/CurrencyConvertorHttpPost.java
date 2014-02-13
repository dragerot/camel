package net.webservicex;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2014-02-11T17:34:34.751+01:00
 * Generated source version: 2.6.1
 * 
 */
@WebService(targetNamespace = "http://www.webserviceX.NET/", name = "CurrencyConvertorHttpPost")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CurrencyConvertorHttpPost {

    @WebResult(name = "double", targetNamespace = "http://www.webserviceX.NET/", partName = "Body")
    @WebMethod(operationName = "ConversionRate")
    public double conversionRate(
        @WebParam(partName = "FromCurrency", name = "FromCurrency", targetNamespace = "http://www.webserviceX.NET/")
        java.lang.String fromCurrency,
        @WebParam(partName = "ToCurrency", name = "ToCurrency", targetNamespace = "http://www.webserviceX.NET/")
        java.lang.String toCurrency
    );
}
