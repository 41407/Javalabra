/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;

/**
 *
 * @author 41407
 */
public class Kamera extends Piste {

    int xNopeus;
    int yNopeus;

    public Kamera(int x, int y) {
        super(x, y);
        xNopeus = 0;
        yNopeus = 0;
    }

    public void seuraa(Pelaaja pelaaja) {

        // -50 koska näin sijoitamme pelaajan kultaiseen leikkaukseen
        int xKeski = 1024 / 2 + this.getX() - 75;
        int yKeski = 800 / 2 + this.getY() + 75;
        int xDelta = pelaaja.getX() + pelaaja.getxNopeus() - xKeski;
        int yDelta = pelaaja.getY() + pelaaja.getyNopeus() - yKeski;

        xNopeus = xDelta / 15;
        yNopeus = yDelta / 30;

        liiku();
    }

    private void liiku() {
        this.siirra(xNopeus, yNopeus);
    }
}
