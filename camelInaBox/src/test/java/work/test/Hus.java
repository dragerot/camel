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
public class Hus {
    String id;
    String addresse;

    public Hus(String id, String addresse){
     this.id=id;
     this.addresse=addresse;
    }
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Hus)
        {
            sameSame = this.id== ((Hus) object).id;
        }

        return sameSame;
    
    }

   
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }
    
    
}
