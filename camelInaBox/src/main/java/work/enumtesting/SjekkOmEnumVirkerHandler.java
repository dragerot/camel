/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.enumtesting;

import org.apache.camel.Exchange;

/**
 *
 * @author TG3
 */
public class SjekkOmEnumVirkerHandler {
    public void requestA(Exchange exchange) throws Exception {
        System.out.println("SjekkOmEnumVirkerHandler A");
    }   
    
     public void requestB(Exchange exchange) throws Exception {
        System.out.println("SjekkOmEnumVirkerHandler B");
    }   
}
