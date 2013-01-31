/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juoksu;

import grafiikka.Kamera;
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
        this.kamera = new Kamera(0,0);
        this.pelaaja = new Pelaaja(20, 0, taso);
        taso.lisaaEste(new Este(0, 269, 300, 480));
        taso.lisaaEste(new Este(0, 270, 500, 400));
        taso.lisaaEste(new Este(550, 340, 700, 342));
        taso.lisaaEste(new Este(50, 500, 650, 560));
        
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
            System.out.println("Kameran x-sijainti: " + kamera.getX());
  //          System.out.println("Pelaajan x-sijainti: " + pelaaja.getX());
        }
    }
}
