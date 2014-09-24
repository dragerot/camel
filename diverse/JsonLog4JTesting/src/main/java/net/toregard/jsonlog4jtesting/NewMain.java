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
   
   public static XStream getXStream(Object object){
       if(xStream==null){
           xStream=new XStream();
           xStream.alias(object.getClass().getSimpleName(), object.getClass());
       }
       
       return xStream;
   }
   
   public static String transformJavaToXML(Object object){
       return getXStream(object).toXML(object);
   }
   
   public static org.apache.log4j.Logger getLogger(){
       return Logger.getRootLogger();
   }
   
   public static void log(Object obj){
       getLogger().log(Priority.INFO, obj);
   }
   
   public void run(){
      
   }
   
   public static void main(String[] args) {
       NewMain.log("Starter");
       NewMain.log(NewMain.transformJavaToXML(new Person("id","Tore Gard", "Andersen")));
       NewMain.log( "Ferdig");
    }
    
}
