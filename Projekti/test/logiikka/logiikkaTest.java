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
import tasonLogiikka.Hahmo;
import tasonLogiikka.Taso;

/**
 *
 * @author 41407
 */
public class logiikkaTest {

    Piste p;
    Este e;
    Taso t;

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
        this.e = new Este(0, 0, 10, 10);
        this.t = new Taso(new Hahmo(5, 0));
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
        t = new Taso(new Hahmo(5, 95));
        t.lisaaEste(new Este(-50, 0, 0, 200));
        t.lisaaEste(new Este(-100, 100, 200, 150));
    }

    @Test
    public void hahmoEiPaaseVasemmallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        for (int i = 0; i < 100; i++) {
            t.getHahmo().liikuVasemmalle(t);
        }

        assertTrue("Hahmo pääsi seinästä läpi vasemmalle paahtaessaan. x = "+ 
                t.getHahmo().getX(), t.getHahmo().getX() > 0);

    }

    @Test
    public void hahmoEiPaaseOikeallaPuolellaOlevastaEsteestaLapi() {
        laitetaasPariEstetta();
        t.getHahmo().siirra(-60, 0);
        for (int i = 0; i < 100; i++) {
            t.getHahmo().liikuOikealle(t);
        }

        assertTrue("Hahmo pääsi seinästä läpi oikealle paahtaessaan. x = "+ 
                t.getHahmo().getX(), t.getHahmo().getX() < -50);

    }
}
