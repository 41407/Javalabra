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
import tasonLogiikka.Taso;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Kuuntelija kuuntelija;
    private Taso taso;
    
    public Kayttoliittyma(Taso taso) {
        this.taso = taso;
    }

    @Override
    public void run() {
        frame = new JFrame("Otsikko");
        frame.setPreferredSize(new Dimension(400, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta();
        container.add(piirtoalusta);
        frame.addKeyListener(new Kuuntelija(taso));
    }

    public JFrame getFrame() {
        return frame;
    }
}