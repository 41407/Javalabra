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
import tasonLogiikka.EsteenTyyppi;
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

        juokse();
    }

    /**
     * Iskee tulille leveelin ja käyttöliittymän
     */
    public void initialisoiSysteemit(int i) {
        Taso taso = new Taso();


        try {
            Tiedostonlukija lukija = new Tiedostonlukija("src/" + i + ".txt");
            this.pelaaja = new Pelaaja(lukija.luoTaso());
        } catch (Exception ex) {
            Logger.getLogger(Juoksu.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.kamera = new Kamera(taso.getPelaajanAlkusijainti().getX(),
                taso.getPelaajanAlkusijainti().getY() - 2000);

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelaaja, kamera);
        SwingUtilities.invokeLater(kayttoliittyma);
    }

    /**
     * Pääluuppi
     */
    public void juokse() {
        int i = 0;
        while (true) {
            initialisoiSysteemit(i);

            while (true) {

                try {
                    Thread.sleep(16);
                } catch (InterruptedException ex) {
                    System.out.println("joku meni vituiks :D");
                }

                pelaaja.eksistoi();
                kamera.seuraa(pelaaja);
                if (pelaaja.getOsuikoJohonkin() == EsteenTyyppi.MAALI) {
                    i++;
                    break;
                } else if (pelaaja.getOsuikoJohonkin() == EsteenTyyppi.KUOLO) {
                    i--;
                    break;
                }
            }
        }
    }
}
