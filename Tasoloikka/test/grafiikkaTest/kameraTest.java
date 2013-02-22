/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikkaTest;

import grafiikka.Kamera;
import logiikka.Pelaaja;
import logiikka.Piste;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jiji
 */
public class kameraTest {

    private Kamera k;
    private Pelaaja p;

    public kameraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kameraSeuraaOikein() {
        this.k = new Kamera(0, 0);
        this.p = new Pelaaja();
        p.setXY(new Piste(0, 0));

        for (int i = 0; i < 100; i++) {
            k.seuraa(p);
        }

        assertTrue("Kamera ei liikkunut oikeaan x - koordinaattiin, vaan "
                + k.toString(), k.getX() == -423);
        assertTrue("Kamera ei liikkunut oikeaan y - koordinaattiin, vaan "
                + k.toString(), k.getY() == -406);
    }
}
