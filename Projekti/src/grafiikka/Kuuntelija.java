/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tasonLogiikka.Pelaaja;

/**
 * Näppäimistön kuuntelija.
 * 
 * Disclaimer:
 * Toisin kuin Windowsissa tai Macissa, hyppynapin pohjassa pitäminen johtaa
 * Linux-ympäristössä hahmon jatkuvaan hypiskelyyn. Syynä lienee erilainen
 * näppäimistösyötteiden käsittely käyttöjärjestelmien välillä. :(.
 *
 * @author 41407
 */
public class Kuuntelija implements KeyListener {

    /**
     * Käsiteltävä pelaaja
     */
    private Pelaaja pelaaja;
    /**
     * Muuttuja joka pitää kirjaa ensiksi painetusta suuntanapista
     */
    private Painike ensiksiPainettu = Painike.NULL;
    /**
     * Nappien tilasta kirjaa pitävät booleanit
     */
    private boolean vasen = false;
    private boolean oikea = false;
    private boolean hyppy = false;
    /**
     * Muuttuja joka estää pelaajan jatkuvan hyppimisen kun hyppynappia pidetään
     * pohjassa.
     */
    private int hyppynapinAbusointiMuuttuja;

    public Kuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    /**
     * Kuuntelee näppäimistöä. Hyppy on helppo, sivuttainen liikkuminen ei. Kun
     * pelaaja painaa nuolinäppäimiä vasemmalle tai oikealle, pelaajan hahmo
     * näytöllä liikkuu. Samalla metodi tallettaa kumpaa nappia pelaaja painoi
     * ensimmäiseksi. Tämä siksi, että tilanne, jossa pelaaja painaa toista
     * nappia toisen ollessa vielä pohjassa ja sitten vapauttaa sen toimii
     * oikein. Sekavahkoa :)
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            vasen = true;
            if (ensiksiPainettu == Painike.NULL) {
                ensiksiPainettu = Painike.VASEN;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = true;
            if (ensiksiPainettu == Painike.NULL) {
                ensiksiPainettu = Painike.OIKEA;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            hyppy = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pelaaja.lopetaTaso();
        }
        kutsuMetodeja();
    }

    /**
     * Kuuntelee näppäimenvapautuseventtejä. Kun pelaaja päästää irti
     * suuntanäppäimestä, toinen skenaarioista tapahtuu (lista ei ole
     * täydellinen):
     *
     * 1. Pelaaja painaa vasemmalle, pelaaja vapauttaa vasemman näppäimen.
     * Hahmon liike pysähtyy.
     *
     * 2. Pelaaja painaa vasemmalle ja oikealle, pelaaja vapauttaa vasemman
     * näppäimen. Tällöin hahmo aloittaa liikkumisen oikealle.
     *
     * Ja hyppy on myös käsitelty tässä.
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            vasen = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            hyppy = false;
        }
        kutsuMetodeja();
    }

    /**
     * Logiikka joka käsittelee pelaajan käskemisen näppäinpainallusten
     * perusteella.
     */
    private void kutsuMetodeja() {
        if (oikea && vasen) {
            if (ensiksiPainettu == Painike.VASEN) {
                pelaaja.liikuOikealle();
            } else {
                pelaaja.liikuVasemmalle();
            }
        } else if (vasen) {
            pelaaja.liikuVasemmalle();
        } else if (oikea) {
            pelaaja.liikuOikealle();
        } else if (!oikea && !vasen) {
            pelaaja.pysaytaLiike();
            ensiksiPainettu = Painike.NULL;
        }

        /**
         * Eli näillä estetään toistuvat hypyt hyppynapin ollessa pohjassa. Kun
         * hyppynappi on pohjassa, hyppynapinAbusointiMuuttuja kasvattaa arvoaan
         * lineaarisesti. Hypätä voi vain jos sen arvo on vähemmän tai yhtäsuuri
         * kuin yksi, eli vain sinä hetkenä kun käyttäjä painaa hyppynappia.
         */
        if (hyppy) {
            this.hyppynapinAbusointiMuuttuja++;
        } else {
            this.hyppynapinAbusointiMuuttuja = 0;
        }

        if (hyppy && this.hyppynapinAbusointiMuuttuja <= 1) {
            pelaaja.aloitaHyppy();

            /**
             * Tämä ehtolause on tarpeen, jotta pelaaja voi säädellä hypyn
             * korkeutta vapauttamalla hyppynapin aikaisemmin.
             */
        } else if (!hyppy) {
            pelaaja.pysaytaHyppy();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // TUrha metodi.
    }
}