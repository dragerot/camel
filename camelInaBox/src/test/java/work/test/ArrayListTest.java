/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.test;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author TG3
 */
public class ArrayListTest {

    public ArrayListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testarrayList() {
        ArrayList list = new ArrayList();
        list.add(new Baat("Baat1", "baat1"));
        list.add(new Bil("Bil1", "Bil1"));
        list.add(new Hus("Hus1", "111"));
        list.add(new Baat("Baat2", "Baat2"));
        list.add(new Hus("Hus1", "3333"));
        Hus hus=new Hus("Hus1", "44444");
        int ihus=list.indexOf(hus);
        assertEquals(true, list.contains(hus));
        Baat baat=new Baat("Baat2", "Baat2");
        int ibaat=list.indexOf(baat);
        assertEquals(true, list.contains(baat));
        
        
    }

}
