/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.db4o;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Db4oResponse", namespace = "http://net/toregard/work/db4o", propOrder = {
    "result"})
public class Db4oResponse {
      @XmlElement(name = "Result", required = true)
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
