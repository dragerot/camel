package net.toregard.jsonlog4jtesting;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


/**
 *
 * @author toregard
 */
public class NewMain {
   static XStream xStream=null;
   
   private XStream getXStream(){
       if(xStream==null) xStream=new XStream();
       return xStream;
   }
   
   public static org.apache.log4j.Logger getLogger(){
       return Logger.getRootLogger();
   }
   
   public void run(){
       Person person=new Person("id","Tore Gard", "Andersen");
       String xmlPerson=getXStream().toXML(person);
       getLogger().log(Priority.INFO, person);
   }
   
   public static void main(String[] args) {
       getLogger().log(Priority.INFO, "Starter");
       new NewMain().run();
    }
    
}
