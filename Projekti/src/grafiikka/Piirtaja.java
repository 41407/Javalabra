/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

import java.awt.Graphics;
import java.util.ArrayList;
import tasonLogiikka.Este;
import tasonLogiikka.Pelaaja;
import tasonLogiikka.Piste;
import tasonLogiikka.Taso;

/**
 *
 * @author jiji
 */
public class Piirtaja {

    Pelaaja pelaaja;
    Kamera kamera;

    public Piirtaja(Pelaaja pelaaja, Kamera kamera) {
        this.pelaaja = pelaaja;
        this.kamera = kamera;
    }

    public void piirra(Graphics graphics) {
        piirraPelaaja(graphics);
        piirraTaso(graphics, pelaaja.getTaso());
    }

    private void piirraPelaaja(Graphics graphics) {
        graphics.fillRect(-kamera.getX() + pelaaja.getX() - 12,
                -kamera.getY() + pelaaja.getY() - 32,
                24,
                32);
    }

    private void piirraTaso(Graphics graphics, Taso taso) {
        ArrayList<Este> esteet = new ArrayList();
        esteet = taso.getEsteet();

        for (Este este : esteet) {
            piirraEste(graphics, este);
        }
    }

    private void piirraEste(Graphics graphics, Este este) {
        ArrayList<Piste> pisteet = este.getPisteet();
        graphics.fillRect(-kamera.getX() + pisteet.get(0).getX(),
                -kamera.getY() + pisteet.get(0).getY(),
                pisteet.get(1).getX() - pisteet.get(0).getX(),
                pisteet.get(1).getY() - pisteet.get(0).getY());
    }
}
