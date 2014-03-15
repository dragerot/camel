/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.enumtesting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrukturType")
@XmlEnum
public enum StrukturType {
    StructurertType(Structurert.class),UStrukturertType(UStrukturert.class);
    
     private final Class<?> c;
     
      private StrukturType (Class<?> c) {
        this.c = c;
    }

    public Class<?> getClassObject() {
        return c;
    }
}
