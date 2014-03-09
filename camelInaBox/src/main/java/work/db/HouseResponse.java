/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.db;

import common.util.date.DateTimeUtility;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)           
@XmlType(name = "HouseResponse", namespace = "http://work/house", propOrder = {
    "postalCode",
    "streetName",
    "housageNum",
    "buildingYear" 
})
public class HouseResponse {
   @XmlElement(name = "PostalCode", required = true)
    String postalCode;
   @XmlElement(name = "StreetName", required = true)
   String streetName;
   @XmlElement(name = "HousageNum", required = true)
   int housageNum;
   @XmlElement(name = "BuildingYear", required = true)
   int buildingYear;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHousageNum() {
        return housageNum;
    }

    public void setHousageNum(int housageNum) {
        DateTimeUtility dateTimeUtility=new  DateTimeUtility();
        int calcYear=dateTimeUtility.retrieveLocalDateTimeYear("NO")-housageNum;
        setBuildingYear(calcYear);
        this.housageNum = housageNum;
    }

    public int getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(int buildingYear) {
        this.buildingYear = buildingYear;
    }
   
   
}
