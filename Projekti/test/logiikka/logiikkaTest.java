/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import tasonLogiikka.Piste;
import tasonLogiikka.Este;
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

    Piste p;
    Este e;

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
        this.p = new Piste(0, 0);
        this.e = new Este(0,0,10,10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pisteGetXJaGetYToimivat() {

        assertTrue("Väärä x:n arvo", p.getX() == 0);
        assertTrue("Väärä y:n arvo", p.getY() == 0);
    }

    @Test
    public void pisteenSiirtaminenToimii() {
        p.siirra(1, 1);

        assertTrue("Väärä siirretyn pisteen x", p.getX() == 1);
        assertTrue("Väärä siirretyn pisteen y", p.getY() == 1);
    }
    
    @Test
    public void esteKuuluukoPiste() {
        
        /*
         * Tää testi saattaa kämähtää jos teen hankalamman muotoisia
         * esteitä kuin suorakaiteita!
         */
        
        assertTrue("Vasen yläkulma pieleen", e.kuuluukoPiste(p)==true);
        p.siirra(10, 0);
        assertTrue("Oikea yläkulma pieleen", e.kuuluukoPiste(p)==true);
        p.siirra(0, 10);
        assertTrue("Oikea alakulma pieleen", e.kuuluukoPiste(p)==true);
        p.siirra(-10, 0);
        assertTrue("Vasen alakulma pieleen", e.kuuluukoPiste(p)==true);
        
    }
}
