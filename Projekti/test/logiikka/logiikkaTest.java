/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 41407
 */
public class logiikkaTest {

    public logiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void pisteGetXJaGetYToimivat() {
        Piste p = new Piste(0, 0);
        assertTrue("Väärä arvo", p.getX() == 0);
        assertTrue(p.getY() == 0);
    }

    @Test
    public void pisteenSiirtaminenToimii() {
        Piste p = new Piste(0, 0);
        p.siirra(1, 1);
        
        assertTrue(p.getX() == 1);
        assertTrue(p.getY() == 1);
    }
}
