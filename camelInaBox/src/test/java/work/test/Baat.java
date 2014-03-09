/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.test;

/**
 *
 * @author TG3
 */
public class Baat {
    String id;
    String merke;

    public Baat(String id,String merke){
        this.id=id;
        this.merke=merke;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Baat)
        {
            sameSame = this.id== ((Baat) object).id;
        }

        return sameSame;
    
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }
    
    
}
