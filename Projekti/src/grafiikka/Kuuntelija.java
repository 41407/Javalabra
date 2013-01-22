/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafiikka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tasonLogiikka.Taso;

public class Kuuntelija implements KeyListener {
    private Taso taso;

    public Kuuntelija(Taso taso) {
        this.taso = taso;
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Liepä turha metodi!");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            taso.getHahmo().liikuVasemmalle(taso);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            taso.getHahmo().liikuOikealle(taso);
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
