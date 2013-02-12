/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;

/**
 * Dynaamisen kuvakulman toteuttava luokka. Käytännössä Piste, jolle tehdään
 * erinäisiä laskutoimituksia ja jonka x- ja y-arvoja käytetään sen
 * määrittelyssä, mihin kohtaan näyttöä tason ominaisuudet piirretään.
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
        /**
         * Alkunopeus on säädetty 50:een, koska pelaaja aloittaa tason
         * putoamalla.
         */
        yNopeus = 50;
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
         * framen aikana, jos pelaaja pysyy paikallaan
         */
        xNopeus = xDelta / 15;

        /**
         * Seuraamme pelaajaa y-akselilla vähän hitaammin koska hyppiminen on
         * niin äkkinäinen liike. Myös artistinen valinta, että nopeasti putoava
         * pelaaja häviää kuvasta.
         */
        yNopeus = yDelta / 20;

        /**
         * Itse siirtometodin kutsu.
         */
        this.siirra(xNopeus, yNopeus);
    }
}
