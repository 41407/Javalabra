/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafiikka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tasonLogiikka.Pelaaja;

public class Kuuntelija implements KeyListener {
    private Pelaaja pelaaja;

    public Kuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
        // Liepä turha metodi!
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pelaaja.liikuVasemmalle();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pelaaja.liikuOikealle();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pelaaja.hyppaa();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Releoitu vasuri!");
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Releoitu oikuri!");
        }
    }
}
