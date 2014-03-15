/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.route;

import common.fault.BusinessFault;
import common.fault.SystemFault;
import org.apache.camel.Predicate;
import work.enumtesting.SjekkOmEnumVirkerHandler;

/**
 *
 * @author TG3
 */
public class WorkRouteSjekkOmEnumVirker extends RouteBase{
    Predicate structurert = simple("${in.header.enumRequest.struktur.structurert}");
    
    @Override
    public void configure() throws Exception {
       super.configure();
       onException(SystemFault.class, BusinessFault.class).stop();
   
       from("direct:esl:sjekkOmEnumVirker").
       choice().when(structurert).
       bean(SjekkOmEnumVirkerHandler.class,"requestA").
       otherwise().
       bean(SjekkOmEnumVirkerHandler.class,"requestB").
       end();        
       
        
       
    }
}
