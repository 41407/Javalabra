/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikkaTest;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tasonLogiikka.Piste;
import tasonLogiikka.TasonLuonti;

/**
 *
 * @author jiji
 */
public class TasonLuontiTest {

    private TasonLuonti t;
    
    public TasonLuontiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        TasonLuonti t = new TasonLuonti(new ArrayList<String>());
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void muutaPisteiksiTest() {

        Piste a = new Piste(100, 150);
        Piste b = new Piste(200, 250);
        
        ArrayList<Integer> koordinaatit = new ArrayList();
        
        int j = 100;
        for (int i = 0; i < 4; i++) {
            koordinaatit.add(j);
            j += 50;
        }
    }
}
