/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;

/**
 * Dynaamisen kuvakulman toteuttava luokka
 *
 * @author 41407
 */
public class Kamera extends Piste {

    /**
     * Kameran nopeus x-suunnassa (pikseliä/frame)
     */
    int xNopeus;
    /**
     * Sama y-akselilla.
     */
    int yNopeus;

    public Kamera(int x, int y) {
        super(x, y);
        xNopeus = 0;
        yNopeus = 0;
    }

    /**
     * Käskee kameraa laskemaan nopeudelleen uuden arvon pelaajan ja kameran
     * keskinäisestä sijainnista
     *
     * @param pelaaja jota seurataan
     */
    public void seuraa(Pelaaja pelaaja) {

        // -75 koska näin sijoitamme pelaajan kultaiseen leikkaukseen ;-D
        int xKeski = 1024 / 2 + this.getX() - 75;
        int yKeski = 800 / 2 + this.getY() + 75;
        int xDelta = pelaaja.getX() + pelaaja.getxNopeus() - xKeski;
        int yDelta = pelaaja.getY() + pelaaja.getyNopeus() - yKeski;

        /**
         * Tämän rivin seurauksena kamera saavuttaa pelaajan x-akselilla 15
         * framen aikana
         */
        xNopeus = xDelta / 15;

        /**
         * Seuraamme pelaajaa y-akselilla vähän hitaammin koska hyppiminen on
         * niin äkkinäinen liike.
         */
        yNopeus = yDelta / 30;

        this.siirra(xNopeus, yNopeus);
    }
}
