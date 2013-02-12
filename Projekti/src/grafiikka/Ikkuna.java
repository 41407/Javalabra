/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

/**
 *
 * @author jiji
 */
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tasonLogiikka.Pelaaja;

public class Ikkuna extends JFrame implements Runnable {

    private Pelaaja pelaaja;
    private Kamera kamera;
    private Piirtoalusta piirtoalusta;

    public Ikkuna(Pelaaja pelaaja, Kamera kamera) {
        super();
        this.pelaaja = pelaaja;
        this.kamera = kamera;
    }

    @Override
    public void run() {
        this.setPreferredSize(new Dimension(1024, 800));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(this.getContentPane());

        this.pack();
        this.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        setPiirtoalusta(new Piirtoalusta(pelaaja,kamera));
        container.add(piirtoalusta);
    }
    
    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
        this.addKeyListener(new Kuuntelija(pelaaja));
    }
}