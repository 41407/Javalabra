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

public class Piirtoalusta extends JPanel {

    private Kuvio kuvio;

    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
        this.kuvio = new Kuvio(20,20);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        kuvio.piirra(graphics);
        
    }
}