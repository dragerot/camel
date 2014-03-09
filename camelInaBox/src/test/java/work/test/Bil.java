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
public class Bil {
    private String id;
    private String farge;

    public Bil(String id, String farge){
        this.id=id;
        this.farge=farge;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Bil)
        {
            sameSame = this.id== ((Bil) object).id;
        }

        return sameSame;
    
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }
    
    
    
}
