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

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Kuvio kuvio;
    
    public Kayttoliittyma(Kuvio kuvio) {
        this.kuvio = kuvio;
    }

    @Override
    public void run() {
        frame = new JFrame("Otsikko");
        frame.setPreferredSize(new Dimension(400, 200));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(kuvio);
        container.add(piirtoalusta);
    }

    public JFrame getFrame() {
        return frame;
    }
}