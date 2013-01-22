/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juoksu;

import grafiikka.Kayttoliittyma;
import javax.swing.SwingUtilities;
import tasonLogiikka.Este;
import tasonLogiikka.Hahmo;
import tasonLogiikka.Taso;

/**
 *
 * @author 41407
 */
public class Juoksu {

    private Taso taso;

    public Juoksu() {
        initialisoiSysteemit();
        juokse();
    }

    /**
     * Iskee tulille leveelin ja käyttöliittymän
     */
    public void initialisoiSysteemit() {
        this.taso = new Taso(new Hahmo(0, 0));
        taso.lisaaEste(new Este(0, 20, 500, 50));

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(taso);
        SwingUtilities.invokeLater(kayttoliittyma);
    }

    /**
     * Pääluuppi
     */
    public void juokse() {
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("joku meni vituiks :D");
            }

            taso.getHahmo().putoa(taso);
            System.out.println("Pelaajan y-sijainti: " + taso.getHahmo().getY());
            System.out.println("Pelaajan x-sijainti: " + taso.getHahmo().getX());
        }
    }
}
