/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juoksu;

import grafiikka.Kayttoliittyma;
import javax.swing.SwingUtilities;
import tasonLogiikka.Este;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Taso;

/**
 *
 * @author 41407
 */
public class Juoksu {

    private Pelaaja pelaaja;

    public Juoksu() {
        initialisoiSysteemit();
        juokse();
    }

    /**
     * Iskee tulille leveelin ja käyttöliittymän
     */
    public void initialisoiSysteemit() {
        Taso taso = new Taso();
        this.pelaaja = new Pelaaja(0, 0, taso);
        taso.lisaaEste(new Este(0, 20, 500, 50));

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelaaja);
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

            pelaaja.eksistoi();
            System.out.println("Pelaajan y-sijainti: " + pelaaja.getY());
            System.out.println("Pelaajan x-sijainti: " + pelaaja.getX());
        }
    }
}
