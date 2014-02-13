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
@XmlType(name = "Db4oRequest", namespace = "http://net/toregard/work/db4o", propOrder = {
    "knr",
    "periodeSeconds"
})
public class Db4oRequest {
    @XmlElement(name = "Knr", required = true)
    String knr;
    @XmlElement(name = "PeriodeSeconds")
    long periodeSeconds;

    public String getKnr() {
        return knr;
    }

    public void setKnr(String knr) {
        this.knr = knr;
    }

    public long getPeriodeSeconds() {
        return periodeSeconds;
    }

    public void setPeriodeSeconds(long periodeSeconds) {
        this.periodeSeconds = periodeSeconds;
    }
    
    
}
