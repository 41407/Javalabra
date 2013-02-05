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

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Pelaaja pelaaja;
    private Kamera kamera;

    public Kayttoliittyma(Pelaaja pelaaja, Kamera kamera) {
        this.pelaaja = pelaaja;
        this.kamera = kamera;
    }

    @Override
    public void run() {
        
        frame = new JFrame("Itseironinen otsikko");

        frame.setPreferredSize(new Dimension(1024, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(pelaaja, kamera);
        container.add(piirtoalusta);
        frame.addKeyListener(new Kuuntelija(pelaaja));
    }

    public JFrame getFrame() {
        return frame;
    }
}