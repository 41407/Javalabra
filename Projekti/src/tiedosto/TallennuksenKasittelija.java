/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedosto;

import java.util.ArrayList;

/**
 *
 * @author jiji
 */
public class TallennuksenKasittelija {
    private int tasonNumero;

    public TallennuksenKasittelija() {};
    
    public TallennuksenKasittelija(int tasonNumero) {
        this.tasonNumero = tasonNumero;
    }
    
    public int etsiTasonNumero(ArrayList<String> rivit) {
        int tasonNumero = 0;
        for (String string : rivit) {
            if(string.startsWith("Taso: ")) {
                tasonNumero = Integer.parseInt(string.substring(6));
            }
        }
        return tasonNumero;
    }
    
    @Override
    public String toString() {
        return "Taso: " + this.tasonNumero;
    }
}
