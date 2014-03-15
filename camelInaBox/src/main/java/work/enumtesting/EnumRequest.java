/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package work.enumtesting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author TG3
 */
@XmlAccessorType(XmlAccessType.FIELD)           
@XmlType(name = "EnumRequest", namespace = "http://work/enumtesting", propOrder = {
    "id",
    "struktur",
    "navn"
})
public class EnumRequest {
    @XmlElement(name = "Id",  required = true)
    String id;
    @XmlElement(name = "Struktur",  required = true)
    StrukturType struktur;
    @XmlElement(name = "Navn",  required = true)
    String navn;

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public StrukturType getStruktur() {
        return struktur;
    }

    public void setStryktur(StrukturType struktur) {
        this.struktur = struktur;
    }
    
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    
}
