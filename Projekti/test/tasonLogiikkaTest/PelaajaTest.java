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
        this.t = new Taso(new Pelaaja(5, 0));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hahmoPutoaaAlkeellisesti() {
        int hahmonKoordinaattiAlussa = t.getHahmo().getY();
        t.getHahmo().putoa(t);

        assertTrue("Hahmo ei liikkunut alaspäin!", t.getHahmo().getY()
                > hahmonKoordinaattiAlussa);
    }

    @Test
    public void hahmonPutoaminenPysahtyyEsteeseenAlkeellisesti() {
        /* Aika liberaali testi koska putoamisen mekaniikka
         * tulee muuttumaan
         */

        int hahmonKoordinaattiAlussa = t.getHahmo().getY();
        t.lisaaEste(e);
        for (int i = 0; i < 20; i++) {
            t.getHahmo().putoa(t);
        }
        assertTrue("Hahmo ei pysähtynyt esteesen vaan jatkoi y = "
                + t.getHahmo().getY(), t.getHahmo().getY() <= 10);
    }

    @Test
    public void hahmoPutoaaKorkealta() {
        /* Aika liberaali testi koska putoamisen mekaniikka
         * tulee muuttumaan
         */
        t.getHahmo().siirra(0, -50);
        int hahmonKoordinaattiAlussa = t.getHahmo().getY();
        
        t.lisaaEste(e);
        for (int i = 0; i < 100; i++) {
            t.getHahmo().putoa(t);
        }
        assertTrue("Hahmo ei pysähtynyt esteesen vaan jatkoi y = "
                + t.getHahmo().getY(), t.getHahmo().getY() <= 10);
    }

    @Test
    public void hahmoLiikkuuVasemmalle() {
        int hahmonKoordinaattiAlussa = t.getHahmo().getX();
        t.getHahmo().liikuVasemmalle(t);

        assertTrue("Hahmo ei liikkunut vasemmalle", t.getHahmo().getX()
                < hahmonKoordinaattiAlussa);
    }

    @Test
    public void hahmoLiikkuuOikealle() {
        int hahmonKoordinaattiAlussa = t.getHahmo().getX();
        t.getHahmo().liikuOikealle(t);

        assertTrue("Hahmo ei liikkunut oikealle", t.getHahmo().getX()
                > hahmonKoordinaattiAlussa);
    }

    // Apumetodi
    public void laitetaasPariEstetta() {
        t = new Taso(new Pelaaja(5, 95));
        t.lisaaEste(new Este(-50, 0, 0, 200));
        t.lisaaEste(new Este(-100, 100, 200, 150));
    }

    @Test
    public void hahmoEiPaaseVasemmallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        for (int i = 0; i < 100; i++) {
            t.getHahmo().liikuVasemmalle(t);
        }

        assertTrue("Hahmo pääsi seinästä läpi vasemmalle paahtaessaan. x = "
                + t.getHahmo().getX(), t.getHahmo().getX() > 0);

    }

    @Test
    public void hahmoEiPaaseOikeallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        t.getHahmo().siirra(-60, 0);
        for (int i = 0; i < 100; i++) {
            t.getHahmo().liikuOikealle(t);
        }

        assertTrue("Hahmo pääsi seinästä läpi oikealle paahtaessaan. x = "
                + t.getHahmo().getX(), t.getHahmo().getX() < -50);
    }
}
