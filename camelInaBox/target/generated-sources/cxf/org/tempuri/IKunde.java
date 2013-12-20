package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-12-20T14:28:21.400+01:00
 * Generated source version: 2.6.1
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IKunde")
@XmlSeeAlso({ObjectFactory.class, com.microsoft.schemas._2003._10.serialization.ObjectFactory.class, org.datacontract.schemas._2004._07.wcfserviceenumvalues.ObjectFactory.class})
public interface IKunde {

    @WebResult(name = "GetDataUsingDataContractResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IKunde/GetDataUsingDataContract", output = "http://tempuri.org/IKunde/GetDataUsingDataContractResponse")
    @RequestWrapper(localName = "GetDataUsingDataContract", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDataUsingDataContract")
    @WebMethod(operationName = "GetDataUsingDataContract", action = "http://tempuri.org/IKunde/GetDataUsingDataContract")
    @ResponseWrapper(localName = "GetDataUsingDataContractResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDataUsingDataContractResponse")
    public org.datacontract.schemas._2004._07.wcfserviceenumvalues.CompositeType getDataUsingDataContract(
        @WebParam(name = "composite", targetNamespace = "http://tempuri.org/")
        org.datacontract.schemas._2004._07.wcfserviceenumvalues.CompositeType composite
    );

    @WebResult(name = "GetDataResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IKunde/GetData", output = "http://tempuri.org/IKunde/GetDataResponse")
    @RequestWrapper(localName = "GetData", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetData")
    @WebMethod(operationName = "GetData", action = "http://tempuri.org/IKunde/GetData")
    @ResponseWrapper(localName = "GetDataResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDataResponse")
    public java.lang.String getData(
        @WebParam(name = "value", targetNamespace = "http://tempuri.org/")
        java.lang.Integer value
    );

    @WebResult(name = "getKundeResult", targetNamespace = "http://tempuri.org/")
    @Action(input = "http://tempuri.org/IKunde/getKunde", output = "http://tempuri.org/IKunde/getKundeResponse")
    @RequestWrapper(localName = "getKunde", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetKunde")
    @WebMethod(action = "http://tempuri.org/IKunde/getKunde")
    @ResponseWrapper(localName = "getKundeResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetKundeResponse")
    public org.datacontract.schemas._2004._07.wcfserviceenumvalues.Kunde getKunde(
        @WebParam(name = "velg", targetNamespace = "http://tempuri.org/")
        java.lang.Integer velg
    );
}
