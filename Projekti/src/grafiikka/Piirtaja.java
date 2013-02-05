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
 * Piirtää tason ominaisuudet.
 * 
 * @author jiji
 */
public class Piirtaja {

    /**
     * Käsiteltävä pelaaja
     */
    private Pelaaja pelaaja;

    /**
     * Pelaajaan liittyvä kamera
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
        piirraTaso(graphics, pelaaja.getTaso());
        piirraPelaaja(graphics);
    }

    /**
     * Piirretään pelaaja
     * 
     * @param graphics 
     */
    private void piirraPelaaja(Graphics graphics) {
        graphics.setColor(new Color(64, 0, 0));
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
            graphics.setColor(new Color(64, 0, 0));
        } else {
            graphics.setColor(Color.black);
        }
        ArrayList<Piste> pisteet = este.getPisteet();
        graphics.fillRect(-kamera.getX() + pisteet.get(0).getX(),
                -kamera.getY() + pisteet.get(0).getY(),
                pisteet.get(1).getX() - pisteet.get(0).getX(),
                pisteet.get(1).getY() - pisteet.get(0).getY());
    }
}