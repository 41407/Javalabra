/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juoksu;

import grafiikka.Ikkuna;
import grafiikka.Kamera;
import grafiikka.Piirtoalusta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import tasonLogiikka.EsteenTyyppi;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;
import tiedosto.Tiedostonlukija;

/**
 * Pelin keskeisen logiikan käynnistävä ja hallinnoiva luokka
 *
 * @author 41407
 */
public class Juoksu {

    /**
     * Pelaajahahmo jota käsitellään
     */
    private Pelaaja pelaaja;
    /**
     * Pelaajahahmoon liitettävä kameraolio
     */
    private Kamera kamera;
    private Ikkuna kayttoliittyma;

    public Juoksu() {
        this.pelaaja = new Pelaaja();
        this.kamera = new Kamera(0, 0);
        this.kayttoliittyma = new Ikkuna(pelaaja, kamera);
        juokse();
    }

    /**
     * Iskee tulille leveelin ja käyttöliittymän
     */
    public void initialisoiSysteemit(int i) {
        initialisoiTaso(i);
        SwingUtilities.invokeLater(kayttoliittyma);

    }

    private void initialisoiTaso(int i) {
        try {
            Tiedostonlukija lukija = new Tiedostonlukija("src/" + i + ".txt");
            this.pelaaja.setTaso(lukija.luoTaso());
        } catch (Exception ex) {
            Tiedostonlukija lukija;
            try {
                lukija = new Tiedostonlukija("src/gg.txt");
                this.pelaaja.setTaso(lukija.luoTaso());
            } catch (Exception ex1) {
                Logger.getLogger(Juoksu.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        this.kamera.setXY(new Piste(pelaaja.getTaso().getPelaajanAlkusijainti().getX()-405,
                pelaaja.getTaso().getPelaajanAlkusijainti().getY()));
        this.kamera.seuraa(pelaaja);

        this.kayttoliittyma.setPiirtoalusta(new Piirtoalusta(pelaaja, kamera));
    }

    /**
     * Pääluuppi
     */
    public void juokse() {
        int i = 0;
        initialisoiSysteemit(i);

        while (true) {
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
                    break;
                }
                this.kayttoliittyma.repaint();
            }
            initialisoiTaso(i);
        }
    }
}
