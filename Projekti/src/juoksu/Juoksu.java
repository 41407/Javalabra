/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juoksu;

import grafiikka.Kamera;
import grafiikka.Kayttoliittyma;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import tasonLogiikka.Este;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Taso;
import tiedosto.Tiedostonlukija;

/**
 *
 * @author 41407
 */
public class Juoksu {

    private Pelaaja pelaaja;
    private Kamera kamera;

    public Juoksu() {
        initialisoiSysteemit();
        juokse();
    }

    /**
     * Iskee tulille leveelin ja käyttöliittymän
     */
    public void initialisoiSysteemit() {
        Taso taso = new Taso();
        this.kamera = new Kamera(0, 0);


        try {
            Tiedostonlukija lukija = new Tiedostonlukija("src/taso.txt");
            this.pelaaja = new Pelaaja(20, 0, lukija.luoTaso());
        } catch (Exception ex) {
            Logger.getLogger(Juoksu.class.getName()).log(Level.SEVERE, null, ex);
        }

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelaaja, kamera);
        SwingUtilities.invokeLater(kayttoliittyma);

    }

    /**
     * Pääluuppi
     */
    public void juokse() {
        while (true) {

            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("joku meni vituiks :D");
            }

            pelaaja.eksistoi();
            kamera.seuraa(pelaaja);
            //          System.out.println("Kameran x-sijainti: " + kamera.getX());
            //          System.out.println("Pelaajan x-sijainti: " + pelaaja.getX());
        }
    }
}
