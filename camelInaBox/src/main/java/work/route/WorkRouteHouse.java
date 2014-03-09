/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import work.db.DBHouseExceptionHandler;
import work.db.HouseTransform;
import work.service.WorkServices;

/**
 *
 * @author TG3
 */
public class WorkRouteHouse extends RouteBase {

    private String uri = "cxf:/workservices?serviceClass=" + WorkServices.class.getName();

    @Override
    public void configure() throws Exception {
        super.configure();

        from("direct:getHouse").
                bean(HouseTransform.class, "clearHeader").
                bean(HouseTransform.class, "getHouseRequestTransform").
                to("jdbc:F2ORAdataSource").onException(Exception.class).process(new DBHouseExceptionHandler()).handled(true).end().
                bean(HouseTransform.class, "getHouseResponseTransform");
        
        //from("direct:esl:db").onException(Exception.class).process(new HouseGlobalExceptionHandler()).
        //bean(HouseTransform.class, "getHouseRequestTransform"). 
       //setHeader("ssn", constant("02055829384")).        
        //setHeader("postalcode", constant("")).        
        //to("sql:SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM LOEP511_V2_BA WHERE SSN_NUM=:'02055829384'").       
        //to("jdbc:DBdataSource").onException(Exception.class).process(new DBHouseExceptionHandler()).
        //setBody("select * from projects where license = :?lic and id > :?min order by id").
        //bean(HouseTransform.class, "getHouseResponseTransform").end();
        
        
        //.to("sql:SELECT POSTAL_KDE, ADDRESS_KDE, HOUSEAGE_NUM FROM LOEP511_V2_BA WHERE SSN_NUM=:#ssn?dataSourceRef=jdbc/myDataSource");
        
    }
    
    
}
