/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.camel.Exchange;
import org.apache.cxf.message.MessageContentsList;
import work.reportendpoint.MeldingRetur;

/**
 *
 * @author TG3
 */
public class HouseTransform {
    
    /**
     * SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM LOEP511_V2_BA WHERE SSN_NUM = '03086529520' AND AND POSTAL_KDE ='1450'
     * sql:SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM LOEP511_V2_BA WHERE SSN_NUM=:#ssn AND AND POSTAL_KDE=:#postalcode
     * @param exchange 
     */
     public void getHouseRequestTransform(Exchange exchange) {
         HouseRequest houseRequest =exchange.getIn().getBody(HouseRequest.class);
      
         String sql="SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM F2ORA.LOEP511_V2_BA WHERE SSN_NUM='#SSN_NUM#' AND POSTAL_KDE='#POSTAL_KDE#'";
         sql=sql.replace("#SSN_NUM#", houseRequest.getSsn()).replace("#POSTAL_KDE#", houseRequest.getPostalCode());
         //String sql="SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM F2ORA.LOEP511_V2_BA WHERE SSN_NUM='02055829384' OR SSN_NUM='02055830358'";
         exchange.getIn().setBody(sql);
     }
     
     public void getHouseResponseTransform(Exchange exchange) {
         int CamelJdbcRowCount=exchange.getIn().getHeader("CamelJdbcRowCount", int.class);
         ArrayList<LinkedHashMap>  list=exchange.getIn().getBody(ArrayList.class);
         HouseResponsList houseResponsList=new HouseResponsList();
         
         for(LinkedHashMap row : list)
         {
             String postalCode=(String)row.get("POSTAL_KDE");
             String addressKDE=(String)row.get("ADDRESS_KDE");
             BigDecimal houseAgeNum= (BigDecimal)row.get("HOUSEAGE_NUM");
             
             HouseResponse houseResponse=new HouseResponse();
             houseResponse.setPostalCode(postalCode);
             houseResponse.setStreetName(addressKDE);
             
             houseResponse.setHousageNum(houseAgeNum.intValue());
             houseResponsList.getHouseResponses().add(houseResponse);
         }
         exchange.getIn().setBody(houseResponsList);
     }
     
    
    public void clearHeader(Exchange exchange) throws Exception {
        exchange.getIn().removeHeaders("*");
        MessageContentsList messageContentsList = exchange.getIn().getBody(MessageContentsList.class);
        exchange.getIn().setBody(messageContentsList.get(0));
    }
}
