/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.handler;

import org.apache.camel.Exchange;
import work.reportendpoint.CarReport;
import work.reportendpoint.MeldingRetur;
import work.reportendpoint.PersonReport;
import work.reportendpoint.SendMessageRequest;

/**
 *
 * @author TG3
 */
public class sendMessageHandler {
      public void sendMessage(Exchange exchange) throws Exception {
          System.out.println("*******************KILLROY");
          MeldingRetur meldingRetur=new MeldingRetur();
          SendMessageRequest sendMessageRequest = exchange.getIn().getBody(SendMessageRequest.class);
          Object object =sendMessageRequest.getMessageData();
          String className=object.getClass().getSimpleName();
        
          if(className.equals("CarReport")) 
          {
              CarReport car=(CarReport)object;
              System.out.println("***car***************************************");
              System.out.println("car.getBilnummer()"+car.getBilnummer());
              System.out.println("car.getFarge()"+car.getFarge());
              
          }else if(className.equals("PersonReport"))
          {
               PersonReport person=(PersonReport)object;
               System.out.println("*PersonReport*****************************************");
               System.out.println("person.getName()"+person.getName());
               System.out.println("person.getSsn()"+person.getSsn());
          }
          /*
          work.reportendpoint.CarReport
          work.reportendpoint.PersonReport
          */
          
          meldingRetur.setResult(className);
          exchange.getIn().setBody(meldingRetur);
      }
      /*
      private String findClassName(String value)
      {
          String value = "work.reportendpoint.CarReport";
        String[] _list = className.split("[.]");
        int a = _list.length;
        String cmd=_list[a-1];
        int sss=0;
        return value;
      }
      */
}
