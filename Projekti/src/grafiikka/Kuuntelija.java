/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafiikka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tasonLogiikka.Hahmo;

public class Kuuntelija implements KeyListener {
    private Hahmo pelaaja;

    public Kuuntelija(Hahmo pelaaja) {
        this.pelaaja = pelaaja;
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Liepä turha metodi!");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Painettu vasuri!");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Painettu oikuri!");
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
