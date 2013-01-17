/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 41407
 */
public class Muoto {
    private List<Piste> pisteet;
    
    public Muoto() {
        this.pisteet = new ArrayList();
    }
    
    public Muoto(List<Piste> pisteet) {
        this.pisteet = pisteet;
    }
    
    public void siirra(int dx, int dy) {
        for (Piste piste : pisteet) {
            piste.siirra(dx, dy);
        }
    }
    
    
}
