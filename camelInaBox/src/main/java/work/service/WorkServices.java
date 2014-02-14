/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.service;

import common.fault.BusinessFault;
import common.fault.SystemFault;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import work.currency.CurrencyRequest;
import work.currency.CurrencyResponse;
import work.db4o.Db4oRequest;
import work.db4o.Db4oResponse;
import work.domain.message.Request;
import work.domain.message.Response;
import work.reportendpoint.SendMessageRequest;
import work.reportendpoint.MeldingRetur;

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
    
   @WebResult(name = "db4oResponse")
   @WebMethod(operationName = "saveTotDb4o")
   Db4oResponse saveTotDb4o(@WebParam(name = "Db4oRequest")Db4oRequest request) throws BusinessFault,SystemFault; 

   @WebResult(name = "db4oResponse")
   @WebMethod(operationName = "gettDb4o")
   Db4oResponse gettDb4o(@WebParam(name = "Db4oRequest")Db4oRequest request) throws BusinessFault,SystemFault; 
   
   @WebResult(name = "Response")
   @WebMethod(operationName = "Ping")
   String Ping();
   
   @WebResult(name = "MeldingRetur")
   @WebMethod(operationName = "sendMessage")
   public MeldingRetur sendMessage(@WebParam(name = "sendMessageRequest") SendMessageRequest sendMessageRequest);
        
}
