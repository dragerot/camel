/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.db4o;

/**
 * test
 * @author TG3
 */
public class CustomerPersist implements java.io.Serializable {
    String knr;
    long periodeSeconds = 0;
    
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
