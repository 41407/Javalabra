/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostoTest;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiedosto.TallennuksenKasittelija;

/**
 *
 * @author jiji
 */
public class TallennuksenKasitelijaTest {

    TallennuksenKasittelija k;

    public TallennuksenKasitelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.k = new TallennuksenKasittelija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TallennuksenKasittelijaLoytaaTasonNumeronArrayListista() {
        ArrayList<String> rivit = new ArrayList();
        rivit.add("Taso: 4");
        int x = k.etsiTasonNumero(rivit);

        assertTrue("taso oli väärä!", x == 4);
    }
}
