/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import tasonLogiikka.Este;
import tasonLogiikka.EsteenTyyppi;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;
import tasonLogiikka.Taso;

/**
 * Piirtää tason ominaisuudet piirtoalustalle.
 *
 * @author jiji
 */
public class Piirtaja {

    /**
     * Käsiteltävä pelaaja
     */
    private Pelaaja pelaaja;
    /**
     * Piirtäjään liittyvä kamera
     */
    private Kamera kamera;

    public Piirtaja(Pelaaja pelaaja, Kamera kamera) {
        this.pelaaja = pelaaja;
        this.kamera = kamera;
    }

    /**
     * Käskee Piirtäjää toteuttamaan metodit joilla tason piirteet realisoidaan
     * näytölle.
     *
     * @param graphics
     */
    public void piirra(Graphics graphics) {
        /**
         * ja järjestys tietenkin määrittelee mitkä menevät taka-alalle ja mitkä
         * etualalle. Pelaaja piirretään ennen tasoa, koska pelaajan hahmo on
         * (tarkoituksella) hieman isompi kuin sen logiikan määrittelemät rajat,
         * jotta se ei ikinä leijailisi maan yläpuolella tai overlappaa sitä.
         */
        piirraTausta(graphics);
        piirraPelaaja(graphics);
        piirraTaso(graphics, pelaaja.getTaso());
    }

    /**
     * Piirretään pelaaja
     *
     * @param graphics
     */
    private void piirraPelaaja(Graphics graphics) {
        graphics.setColor(new Color(64, 0, 64));
        graphics.fillRect(-kamera.getX() + pelaaja.getX() - 14,
                -kamera.getY() + pelaaja.getY() - 36,
                28,
                38);
    }

    /**
     * Piirretään parametrin taso
     *
     * @param graphics
     * @param taso
     */
    private void piirraTaso(Graphics graphics, Taso taso) {
        ArrayList<Este> esteet = new ArrayList();
        esteet = taso.getEsteet();

        for (Este este : esteet) {
            piirraEste(graphics, este);
        }
    }

    /**
     * Piirretään este
     *
     * @param graphics
     * @param este
     */
    private void piirraEste(Graphics graphics, Este este) {
        if (este.getTyyppi() == EsteenTyyppi.KUOLO) {
            /**
             * Kuolonpalikka VILKKUU 30 kertaa sekunnissa! >:(
             */
            graphics.setColor(new Color(255 * System.currentTimeMillis() / 30 % 2, 0, 0));
        } else {
            graphics.setColor(new Color(20, 20, 20));
        }
        ArrayList<Piste> pisteet = este.getPisteet();
        graphics.fillRect(-kamera.getX() + pisteet.get(0).getX(),
                -kamera.getY() + pisteet.get(0).getY(),
                pisteet.get(1).getX() - pisteet.get(0).getX(),
                pisteet.get(1).getY() - pisteet.get(0).getY());
    }

    /**
     * Koska valkoinen ei riitä
     *
     * @param graphics
     */
    private void piirraTausta(Graphics graphics) {
        /**
         * Ao. yhtälöissä jakolasku johtuu siitä että halajamme viivoihin mageen
         * parallaksiefektin.
         *
         * Ja jooooo for-loopin pituus on kovakoodattu, eli jos pelaaja menee
         * tarpeeksi kauas vasemmalle, oikealle, ylös tai alas, viivat loppuvat.
         * En nähnyt tarpeelliseksi generoida loputonta viivakenttää tässä
         * vaiheessa
         */
        /**
         * Vaakaraita
         */
        for (int i = 0; i < 10; i++) {
            graphics.setColor(new Color(
                    Math.max(0, 255 - i * 4),
                    Math.max(0, 255 - i * 8),
                    Math.max(0, 255 - i * 4)));
            graphics.fillRect(0, -kamera.getY() / 5 + i * 180 + 100, 1024, 100);
        }

        /**
         * Toinen vaakaraita!
         */
        for (int i = 0; i < 10; i++) {
            graphics.setColor(new Color(
                    Math.max(0, 255 - i * 4),
                    Math.max(0, 255 - i * 4),
                    Math.max(0, 255 - i * 8)));
            graphics.fillRect(0, -kamera.getY() / 4 + i * 180 + 100, 1024, 80);
        }

        /**
         * Nooooh pistetään nty vielä opaquoottinen pystyraita!!!1
         */
        for (int i = 0; i < 10; i++) {
            graphics.setColor(new Color(
                    Math.max(0, 255 - i * 4),
                    Math.max(0, 255 - i * 4),
                    Math.max(0, 255 - i * 8),
                    128));
            graphics.fillRect(-kamera.getX() / 20 + i * 200, 0, 100, 800);
        }
    }
}