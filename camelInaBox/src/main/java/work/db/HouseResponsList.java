/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.db;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HouseResponsList", namespace = "http://work/house", propOrder = {
    "houseResponses"
})
public class HouseResponsList {
    @XmlElement(name = "HouseResponses", required = true)
    ArrayList<HouseResponse> houseResponses;

    public List<HouseResponse> getHouseResponses() {
        if(houseResponses==null) houseResponses=new ArrayList();
        return houseResponses;
    }

    public void setHouseResponses(ArrayList<HouseResponse> houseResponses) {
        this.houseResponses = houseResponses;
    }
  
    
}
