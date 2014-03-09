/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.db;

import common.exception.ExceptionHandler;
import org.apache.camel.Exchange;

/**
 *
 * @author TG3
 */
public class HouseGlobalExceptionHandler extends ExceptionHandler {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("");
    }
       
    
}
