/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.service;

import common.fault.SystemFault;
import common.fault.BusinessFault;
import work.domain.message.Request;
import work.domain.message.Response;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import work.currency.CurrencyRequest;
import work.currency.CurrencyResponse;

/**
 *
 * @author tg3
 */
@WebService(portName = "WorkServices",targetNamespace = "http://net/toregard/service")
public interface WorkServices {
   @WebResult(name = "Response")
   @WebMethod(operationName = "CacheTest")
   Response CacheTest(@WebParam(name = "Request")Request request) throws BusinessFault,SystemFault; 
   
   @WebResult(name = "CurrencyResponse")
   @WebMethod(operationName = "getCurrencyOnEmail")
   CurrencyResponse getCurrencyOnEmail(@WebParam(name = "CurrencyRequest")CurrencyRequest request) throws BusinessFault,SystemFault; 
    
   @WebResult(name = "Response")
   @WebMethod(operationName = "Ping")
   String Ping();
}

