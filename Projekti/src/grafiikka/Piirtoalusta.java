/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafiikka;

/**
 *
 * @author jiji
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tasonLogiikka.Pelaaja;

public class Piirtoalusta extends JPanel {

    private Piirtaja piirtaja;

    public Piirtoalusta(Pelaaja pelaaja) {
        super.setBackground(Color.WHITE);
        this.piirtaja = new Piirtaja(pelaaja);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        piirtaja.piirra(graphics);
        this.repaint();
        
    }
}