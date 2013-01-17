/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import java.awt.Graphics;
import logiikka.Piste;

/**
 *
 * @author jiji
 */
public class Kuvio {

    Piste sijainti;

    public Kuvio(int x, int y) {
        this.sijainti = new Piste(x, y);
    }

    public void piirra(Graphics graphics) {
        graphics.fillOval(sijainti.getX(), sijainti.getY(), 10, 10);
    }
}
