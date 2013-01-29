/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tasonLogiikka.Pelaaja;

public class Kuuntelija implements KeyListener {

    private Pelaaja pelaaja;
    private Painike ensiksiPainettu = Painike.NULL;
    private boolean vasen = false;
    private boolean oikea = false;
    private boolean hyppy = false;

    public Kuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // Liepä turha metodi!
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

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = true;
            if (ensiksiPainettu == Painike.NULL) {
                ensiksiPainettu = Painike.OIKEA;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            hyppy = true;

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
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = false;
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            hyppy = false;
        }
        kutsuMetodeja();
    }

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

        if (hyppy == true) {
            pelaaja.aloitaHyppy();
        } else {
            pelaaja.pysaytaHyppy();
        }
    }
}