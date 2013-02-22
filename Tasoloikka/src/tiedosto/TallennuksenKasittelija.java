/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedosto;

import java.util.ArrayList;

/**
 * Ratkoo tallennustiedostosta halutun tason numeron, ja palauttaa toStringissä
 * tallennustiedostoon tallennettavan rivin josta ilmenee pelaajan taso.
 * 
 * @author jiji
 */
public class TallennuksenKasittelija {
    
    private int tasonNumero;

    public TallennuksenKasittelija() {};
    
    public TallennuksenKasittelija(int tasonNumero) {
        this.tasonNumero = tasonNumero;
    }
    
    /**
     * Selvittää annetusta arraylistasta rivin, joka alkaa ilmauksella
     * "Taso: ", tallettaa tason numeron attribuuttiinsa
     * ja palauttaa tasonnumeron
     * 
     * @param rivit 
     * @return tasonNumero
     */
    
    public int etsiTasonNumero(ArrayList<String> rivit) {
        int tasonNumero = 0;
        for (String string : rivit) {
            if(string.startsWith("Taso: ")) {
                tasonNumero = Integer.parseInt(string.substring(6));
            }
        }
        return tasonNumero;
    }
    
    /**
     * ToString joka antaa rivin jolla taso tulee tallentaa tiedostoon.
     * @return 
     */
    
    @Override
    public String toString() {
        return "Taso: " + this.tasonNumero;
    }
}
