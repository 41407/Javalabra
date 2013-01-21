/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

/**
 *
 * @author jiji
 */
public class Hahmo extends Piste{
    
    
    
    public Hahmo(int x, int y) {
        super(x, y);
    }
    
    /**
     * Testaa onko hahmon alla maata. Jos ei, hahmo putoaa. Jos on, hahmo pysyy
     * paikoillaan.
     */
    public void putoa(Taso taso) {
        if(!taso.onkoPisteessaEste(this)) {
            this.siirra(0, 1);
        }
    }
}