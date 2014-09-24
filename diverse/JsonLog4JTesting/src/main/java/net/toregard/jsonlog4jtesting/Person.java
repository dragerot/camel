/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.toregard.jsonlog4jtesting;

/**
 *
 * @author toregard
 */
public class Person {
    String id;
    String fName;
    String eName;

    public Person(String id, String fName, String eName) {
        this.id = id;
        this.fName = fName;
        this.eName = eName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }
    
    
}
