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
import tiedosto.TallennuksenKasittelija;
import tiedosto.TasonLuonti;
import tiedosto.Tiedostonkirjoittaja;
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
    /**
     * Peli-ikkuna
     */
    private Ikkuna ikkuna;

    public Juoksu() {
        this.pelaaja = new Pelaaja();
        this.kamera = new Kamera(0, 0);
        this.ikkuna = new Ikkuna(pelaaja, kamera);

        juokse();
    }

    /**
     * Iskee tulille tason ja käyttöliittymän
     */
    public void alustaIkkuna(int i) {
        alustaTaso(i);
        SwingUtilities.invokeLater(ikkuna);
    }

    private void alustaTaso(int i) {
        /**
         * Tason lukeminen ja parsiminen tiedostosta, ja sen asettaminen
         * pelaajalle
         */
        try {
            Tiedostonlukija lukija = new Tiedostonlukija("src/" + i + ".txt");
            TasonLuonti luonti = new TasonLuonti(lukija.getRivit());
            this.pelaaja.setTaso(luonti.getTaso());
        } catch (Exception ex) {
            /**
             * Jos tasot loppuvat, gg.txt :D
             */
            try {
                Tiedostonlukija lukija = new Tiedostonlukija("src/gg.txt");
                TasonLuonti luonti = new TasonLuonti(lukija.getRivit());
                this.pelaaja.setTaso(luonti.getTaso());
            } catch (Exception ex1) {
                /**
                 * Jos gg.txt loppuu, exception :(
                 */
                Logger.getLogger(Juoksu.class.getName()).log(Level.SEVERE,
                        null, ex1);
            }
        }
        /**
         * Sijoitetaan kamera sweet spottiin
         */
        this.kamera.setXY(new Piste(pelaaja.getTaso().getPelaajanAlkusijainti().getX() - 405,
                pelaaja.getTaso().getPelaajanAlkusijainti().getY() - 1000));

        /**
         * Määritetään uusi taso + siihen liittyvät systeemit ikkunan uudeksi
         * sisällöksi
         */
        this.ikkuna.setPiirtoalusta(new Piirtoalusta(pelaaja, kamera));
    }

    /**
     * Pääluuppi
     */
    public void juokse() {
        /**
         * Ladattavan tason määrittävä iteraattori
         */
        int tasonNumero = 0;

        /**
         * Valmistellaan ikkuna
         */
        alustaIkkuna(tasonNumero);

        /**
         * Luuppi joka tapahtuu niin kauan kuin tasoja riittää
         */
        while (true) {
            /**
             * Luuppi joka tapahtuu niin kauan kuin pelaaja pysyy hengissä tai
             * ei ole läpäissyt tasoa
             */
            while (true) {
                /**
                 * 60fps get
                 */
                try {
                    Thread.sleep(16);
                } catch (InterruptedException ex) {
                    System.out.println("joku meni vituiks :D");
                }
                /**
                 * Pelaajan päivitys
                 */
                pelaaja.eksistoi();
                /**
                 * Kameran päivitys
                 */
                kamera.seuraa(pelaaja);
                /**
                 * Tason lopettamisen määrittävä ehtolause
                 */
                EsteenTyyppi este = pelaaja.getOsuikoJohonkin();
                if (este == EsteenTyyppi.MAALI) {
                    tasonNumero++;
                    tallennaPelaajanTilanne(tasonNumero);
                    System.out.println(tasonNumero);
                    break;
                } else if (este == EsteenTyyppi.KUOLO) {
                    break;
                    /**
                     * Tasokohtaiset erityisbehaviour systseemit eli tässä
                     * kohtaa tuleva pelaajan tallennetun tasonNumeron lataus
                     * tiedostosta,
                     */
                } else if (este == EsteenTyyppi.SPECIAL) {
                    if (tasonNumero == 0) {
                        tasonNumero = lataaPelaajanTallennustiedosto();
                        break;
                    }
                }
                /**
                 * Grafiikan päivitys
                 */
                this.ikkuna.repaint();
            }
            /**
             * Tason alu-
             */
            alustaTaso(tasonNumero);
        }
    }

    private int lataaPelaajanTallennustiedosto() {
        try {
            Tiedostonlukija lukija = new Tiedostonlukija("src/save.txt");
            TallennuksenKasittelija kasittelija = new TallennuksenKasittelija();
            return kasittelija.etsiTasonNumero(lukija.getRivit());
        } catch (Exception ex) {
            Logger.getLogger(Juoksu.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private void tallennaPelaajanTilanne(int tasonNumero) {
        try {
            Tiedostonkirjoittaja kirjoittaja = new Tiedostonkirjoittaja();
            TallennuksenKasittelija kasittelija =
                    new TallennuksenKasittelija(tasonNumero);
            kirjoittaja.kirjoitaTiedosto(
                    "src/save.txt",
                    kasittelija.toString(),
                    true);
        } catch (Exception ex1) {
            System.out.println("Tallennus feil'd!");
        }
    }
}
