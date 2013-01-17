/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 *
 * @author jiji
 */
public class Hahmo {
    private Piste sijainti;
    
    public Hahmo(int x, int y) {
        this.sijainti = new Piste(x, y);
    }
    
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
    
    public Piste getSijainti(){
        return sijainti;
    }
}