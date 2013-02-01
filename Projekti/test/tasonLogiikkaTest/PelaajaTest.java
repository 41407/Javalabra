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
        this.e = new Este(0, 0, 100, 100);
        this.t = new Taso(50, 0);
        this.pe = new Pelaaja(t);
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

        int hahmonKoordinaattiAlussa = pe.getY();
        t.lisaaEste(e);
        for (int i = 0; i < 200; i++) {
            pe.eksistoi();
        }
        assertTrue("Hahmo ei pysähtynyt esteesen vaan jatkoi y = "
                + pe.getY(), pe.getY() < 15);
    }

    @Test
    public void hahmoPutoaaKorkealta() {
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
        pe.eksistoi();

        assertTrue("Hahmo ei liikkunut vasemmalle", pe.getX()
                < hahmonKoordinaattiAlussa);
    }

    @Test
    public void hahmoLiikkuuOikealle() {
        int hahmonKoordinaattiAlussa = pe.getX();
        pe.liikuOikealle();
        pe.eksistoi();

        assertTrue("Hahmo ei liikkunut oikealle", pe.getX()
                > hahmonKoordinaattiAlussa);
    }

    // Apumetodi
    public void laitetaasPariEstetta() {
        t = new Taso();
        pe = new Pelaaja(t);
        pe.siirra(5, 95);
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

    // Apumetodi
    public void laitetaasPariPientaEstetta() {
        t = new Taso(10, 10);
        pe = new Pelaaja(t);
        t.lisaaEste(new Este(0, 100, 1000, 200));
        t.lisaaEste(new Este(400, 90, 600, 200));
    }

    @Test
    public void pieniEsteOikealla() {
        laitetaasPariPientaEstetta();
        pe.liikuOikealle();
        for (int i = 0; i < 100; i++) {
            pe.eksistoi();
        }

        assertTrue("Hahmo pääsi matalasta seinästä läpi oikealle paahtaessaan. x = "
                + pe.getX(), pe.getX() < 400);
    }

    @Test
    public void pieniEsteVasemmalla() {
        laitetaasPariPientaEstetta();
        pe.siirra(900, 0);
        pe.liikuVasemmalle();
        for (int i = 0; i < 100; i++) {
            pe.eksistoi();
        }

        assertTrue("Hahmo pääsi matalasta seinästä läpi vasemmalle paahtaessaan. x = "
                + pe.getX(), pe.getX() > 600);
    }

    @Test
    public void tosiPieniEsteOikealla() {
        laitetaasPariPientaEstetta();
        t.lisaaEste(new Este(0, 92, 1000, 200));
        pe.liikuOikealle();
        for (int i = 0; i < 100; i++) {
            pe.eksistoi();
        }

        assertTrue("Hahmo ei päässyt matalan esteen yli. x = "
                + pe.getX(), pe.getX() > 600);
    }

    @Test
    public void tosiPieniEsteVasemmalla() {
        laitetaasPariPientaEstetta();
        t.lisaaEste(new Este(0, 92, 1000, 200));
        pe.siirra(900, 0);
        pe.liikuVasemmalle();
        for (int i = 0; i < 100; i++) {
            pe.eksistoi();
        }

        assertTrue("Hahmo ei päässyt matalan esteen yli. x = "
                + pe.getX(), pe.getX() < 600);
    }

    @Test
    public void hyppy() {
        laitetaasPariEstetta();
        pe.setXY(new Piste(0, 0));
        for (int i = 0; i < 55; i++) {
            pe.eksistoi();
        }
        int y1 = pe.getY();
        pe.aloitaHyppy();
        pe.eksistoi();

        assertTrue("Hahmo ei hypännyt :( y: "
                + pe.getY() + ", pitäisi olla alle " + y1, pe.getY() < y1);
    }
}
