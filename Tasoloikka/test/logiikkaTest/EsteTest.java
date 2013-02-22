/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikkaTest;

import java.util.ArrayList;
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

    @Test
    public void virheellinenFormaattiTapausA() {
        Este s = new Este(200, 200, 100, 100);
        Este r = new Este(100, 100, 200, 200);

        assertTrue("Este ei muuttanut kulmia oikeiksi. " + s.toString(),
                s.toString().equals(r.toString()));
    }

    @Test
    public void virheellinenFormaattiTapausB() {
        Este s = new Este(100, 200, 200, 100);
        Este r = new Este(100, 100, 200, 200);

        assertTrue("Este ei muuttanut kulmia oikeiksi. " + s.toString(),
                s.toString().equals(r.toString()));
    }

    @Test
    public void virheellinenFormaattiTapausC() {
        Este s = new Este(200, 100, 100, 200);
        Este r = new Este(100, 100, 200, 200);

        assertTrue("Este ei muuttanut kulmia oikeiksi. " + s.toString(),
                s.toString().equals(r.toString()));
    }

    @Test
    public void virheellinenFormaattiPisteKonstruktoriTapausA() {
        Este s = new Este(new Piste(200, 200), new Piste(100, 100));
        Este r = new Este(100, 100, 200, 200);

        assertTrue("Este ei muuttanut kulmia oikeiksi. " + s.toString(),
                s.toString().equals(r.toString()));
    }

    @Test
    public void virheellinenFormaattiListaKonstruktoriTapausA() {
        ArrayList<Piste> p = new ArrayList();
        p.add(new Piste(200, 200));
        p.add(new Piste(100, 100));
        Este s = new Este(p);
        Este r = new Este(100, 100, 200, 200);

        assertTrue("Este ei muuttanut kulmia oikeiksi. " + s.toString(),
                s.toString().equals(r.toString()));
    }
}
