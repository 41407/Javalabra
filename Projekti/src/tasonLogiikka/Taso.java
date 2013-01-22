/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

import java.util.ArrayList;

/**
 *
 * @author jiji
 */
public class Taso {
    private Pelaaja pelaaja;
    private ArrayList<Este> esteet;
    
    public Taso(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.esteet = new ArrayList();
    }
    
    public void lisaaEste(Este este) {
        esteet.add(este);
    }
    
    /**
     *
     * @return Palauttaa arraylistinä tasoon kuuluvat esteet.
     */
    public ArrayList<Este> getEsteet() {
        return esteet;
    }

    public Pelaaja getHahmo() {
        return pelaaja;
    }
    
    /**
     *  Testaa onko tason pisteessä piste este vai ei.
     * 
     * @param piste
     * @return true jos pisteessä on este, false jos ei.
     */
    public boolean onkoPisteessaEste(Piste piste) {
        for (Este este : esteet) {
            if(este.kuuluukoPiste(piste)) {
                return true;
            }
        }
        return false;
    }
}
