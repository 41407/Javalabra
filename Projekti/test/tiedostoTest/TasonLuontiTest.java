/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostoTest;

import java.util.ArrayList;
import logiikka.EsteenTyyppi;
import logiikka.Piste;
import logiikka.Taso;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiedosto.TasonLuonti;

/**
 *
 * @author jiji
 */
public class TasonLuontiTest {

    TasonLuonti l;
    Taso t;

    public TasonLuontiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ArrayList<String> r = new ArrayList();
        r.add("E 100 100 200 200");
        r.add("K 200 200 300 300");
        r.add("M 300 300 400 400");
        r.add("X 400 400 500 500");
        this.l = new TasonLuonti(r);
        this.t = this.l.getTaso();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void PelaajanAlkusijaintiKunEiKerrottu() {
        assertTrue("Pelaajan alkusijainti oli väärä",
                t.getPelaajanAlkusijainti().getX() == 0
                && t.getPelaajanAlkusijainti().getY() == 0);
    }

/*    @Test
    public void PelaajanAlkusijaintiKunOnKerrottu() {
        this.r.add("P 20 20");
        assertTrue("Pelaajan alkusijainti oli väärä",
                t.getPelaajanAlkusijainti().getX() == 0
                && t.getPelaajanAlkusijainti().getY() == 0);
    }
*/
    @Test
    public void NormiEste() {
        assertTrue("Este ei ollut oikeassa paikassa",
                t.onkoPisteessaEste(new Piste(150, 150)) == EsteenTyyppi.ESTE);
    }

    @Test
    public void MaaliEste() {
        assertTrue("Este oli väärän tyyppinen",
                t.onkoPisteessaEste(new Piste(350, 350)) == EsteenTyyppi.MAALI);
    }

    @Test
    public void SpecialEste() {
        assertTrue("Este oli väärän tyyppinen",
                t.onkoPisteessaEste(new Piste(450, 450)) == EsteenTyyppi.SPECIAL);
    }

    @Test
    public void KuoloEste() {
        assertTrue("Este oli väärän tyyppinen",
                t.onkoPisteessaEste(new Piste(250, 250)) == EsteenTyyppi.KUOLO);
    }
}
