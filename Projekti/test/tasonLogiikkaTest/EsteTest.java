/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikkaTest;

import logiikka.Piste;
import logiikka.Este;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import logiikka.Pelaaja;
import logiikka.Taso;

/**
 *
 * @author 41407
 */
public class EsteTest {

    Piste p;
    Este e;
    Taso t;

    public EsteTest() {
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
        assertTrue("Vasen yläkulma pieleen", e.kuuluukoPiste(p) != null);
        p.siirra(10, 0);
        assertTrue("Oikea yläkulma pieleen", e.kuuluukoPiste(p) != null);
        p.siirra(0, 10);
        assertTrue("Oikea alakulma pieleen", e.kuuluukoPiste(p) != null);
        p.siirra(-10, 0);
        assertTrue("Vasen alakulma pieleen", e.kuuluukoPiste(p) != null);
    }
}
