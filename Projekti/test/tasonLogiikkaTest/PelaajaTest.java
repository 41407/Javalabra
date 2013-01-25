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
public class PelaajaTest {

    Piste p;
    Este e;
    Taso t;
    Pelaaja pe;

    public PelaajaTest() {
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
        this.pe = new Pelaaja(0,0,t);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hahmoPutoaaAlkeellisesti() {
        int hahmonKoordinaattiAlussa = pe.getY();
        pe.eksistoi();

        assertTrue("Hahmo ei liikkunut alaspäin!", pe.getY()
                > hahmonKoordinaattiAlussa);
    }

    @Test
    public void hahmonPutoaminenPysahtyyEsteeseenAlkeellisesti() {
        /* Aika liberaali testi koska putoamisen mekaniikka
         * tulee muuttumaan
         */

        int hahmonKoordinaattiAlussa = pe.getY();
        t.lisaaEste(e);
        for (int i = 0; i < 20; i++) {
            pe.eksistoi();
        }
        assertTrue("Hahmo ei pysähtynyt esteesen vaan jatkoi y = "
                + pe.getY(), pe.getY() <= 10);
    }

    @Test
    public void hahmoPutoaaKorkealta() {
        /* Aika liberaali testi koska putoamisen mekaniikka
         * tulee muuttumaan
         */
        pe.siirra(0, -50);
        int hahmonKoordinaattiAlussa = pe.getY();
        
        t.lisaaEste(e);
        for (int i = 0; i < 100; i++) {
            pe.eksistoi();
        }
        assertTrue("Hahmo ei pysähtynyt esteesen vaan jatkoi y = "
                + pe.getY(), pe.getY() <= 10);
    }

    @Test
    public void hahmoLiikkuuVasemmalle() {
        int hahmonKoordinaattiAlussa = pe.getX();
        pe.liikuVasemmalle();

        assertTrue("Hahmo ei liikkunut vasemmalle", pe.getX()
                < hahmonKoordinaattiAlussa);
    }

    @Test
    public void hahmoLiikkuuOikealle() {
        int hahmonKoordinaattiAlussa = pe.getX();
        pe.liikuOikealle();

        assertTrue("Hahmo ei liikkunut oikealle", pe.getX()
                > hahmonKoordinaattiAlussa);
    }

    // Apumetodi
    public void laitetaasPariEstetta() {
        t = new Taso();
        pe = new Pelaaja(5,95, t);
        t.lisaaEste(new Este(-50, 0, 0, 200));
        t.lisaaEste(new Este(-100, 100, 200, 150));
    }

    @Test
    public void hahmoEiPaaseVasemmallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        for (int i = 0; i < 100; i++) {
            pe.liikuVasemmalle();
        }

        assertTrue("Hahmo pääsi seinästä läpi vasemmalle paahtaessaan. x = "
                + pe.getX(), pe.getX() > 0);

    }

    @Test
    public void hahmoEiPaaseOikeallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        pe.siirra(-60, 0);
        for (int i = 0; i < 100; i++) {
            pe.liikuOikealle();
        }

        assertTrue("Hahmo pääsi seinästä läpi oikealle paahtaessaan. x = "
                + pe.getX(), pe.getX() < -50);
    }
}
