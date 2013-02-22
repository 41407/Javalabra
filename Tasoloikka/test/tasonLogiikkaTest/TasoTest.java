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
import logiikka.EsteenTyyppi;
import logiikka.Pelaaja;
import logiikka.Taso;

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
        this.p = new Piste(5, 5);
        this.e = new Este(0, 0, 10, 10);
        this.t = new Taso();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tasoKuuluukoPiste() {
        t.lisaaEste(e);
        assertTrue("onkoPisteessaEste() antoi väärän arvon olisi pitänyt olla true", 
                t.onkoPisteessaEste(p) != null);
        p.siirra(20, 0);
        assertTrue("onkoPisteessaEste() antoi väärän arvon", 
                t.onkoPisteessaEste(p) == null);
    }

    @Test
    public void tasoAsetaPelaajanAlkusijaintiToimii() {
        Piste r = new Piste(20, 20);
        t.asetaPelaajanAlkusijainti(r);
        Pelaaja pe = new Pelaaja();
        pe.setTaso(t);
        assertTrue("Pelaajan alkusijainti oli väärä", pe.getX() == 20 && pe.getY() == 20);
        assertTrue("GetPelaajanAlkusijainti palautti väärin",
                t.getPelaajanAlkusijainti() == r);
    }
}
