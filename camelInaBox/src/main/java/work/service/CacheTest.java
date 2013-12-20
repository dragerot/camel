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

/**
 *
 * @author tg3
 */
@WebService(portName = "CacheTest",targetNamespace = "http://net/toregard/service")
public interface CacheTest {
   @WebResult(name = "Response")
   @WebMethod(operationName = "Run")
   Response Run(@WebParam(name = "Request")Request request) throws BusinessFault,SystemFault; 
   
   @WebResult(name = "Response")
   @WebMethod(operationName = "Ping")
   String Ping();
}

