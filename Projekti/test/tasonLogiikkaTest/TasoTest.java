/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikkaTest;

import tasonLogiikka.Piste;
import tasonLogiikka.Este;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Taso;

/**
 *
 * @author 41407
 */
public class TasoTest {

    Este e;
    Taso t;
    Piste p;

    public TasoTest() {
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
        this.e = new Este(0, 0, 10, 10);
        this.t = new Taso();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void esteKuuluukoPiste() {

        /*
         * Tää testi saattaa kämähtää jos teen hankalamman muotoisia
         * esteitä kuin suorakaiteita!
         */

        assertTrue("Vasen yläkulma pieleen", e.kuuluukoPiste(p) == true);
        p.siirra(10, 0);
        assertTrue("Oikea yläkulma pieleen", e.kuuluukoPiste(p) == true);
        p.siirra(0, 10);
        assertTrue("Oikea alakulma pieleen", e.kuuluukoPiste(p) == true);
        p.siirra(-10, 0);
        assertTrue("Vasen alakulma pieleen", e.kuuluukoPiste(p) == true);
    }

    @Test
    public void tasoKuuluukoPiste() {
        t.lisaaEste(e);

        this.esteKuuluukoPiste();
    }
}
