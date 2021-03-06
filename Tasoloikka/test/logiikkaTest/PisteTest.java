/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikkaTest;

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
public class PisteTest {

    Piste p;

    public PisteTest() {
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
    public void pisteSetXJaSetYToimivat() {
        p.setX(10);
        assertTrue("Väärä siirretyn pisteen x", p.getX() == 10);
        p.setY(10);
        assertTrue("Väärä siirretyn pisteen y", p.getY() == 10);
        p.setXY(new Piste(20, 50));
        assertTrue("Väärä siirretyn pisteen x", p.getX() == 20);
        assertTrue("Väärä siirretyn pisteen y", p.getY() == 50);
    }
}
