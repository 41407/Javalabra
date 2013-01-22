/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

import java.util.ArrayList;


/**
 *
 * @author 41407
 */

public class Este {
    
    private Piste vasenYlakulma;
    private Piste oikeaAlakulma;

    public Este(int x0, int y0, int x1, int y1) {
        this.vasenYlakulma = new Piste(x0, y0);
        this.oikeaAlakulma = new Piste(x1, y1);
    }
    
    /**
     * Testaa sijaitseeko parametrinä annettu piste esteen peittämällä alueella.
     * @param piste
     * @return True jos sijaitsee, false jos ei.
     */
    public boolean kuuluukoPiste(Piste piste) {
        if(piste.getX() >= vasenYlakulma.getX() && piste.getX() <= oikeaAlakulma.getX()
                && piste.getY() >= vasenYlakulma.getY() && piste.getY() <= oikeaAlakulma.getY()) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return Palauttaa arraylistinä esteen määrittävät pisteet.
     */
    public ArrayList<Piste> getPisteet() {
        ArrayList<Piste> pisteet = new ArrayList();
        pisteet.add(vasenYlakulma);
        pisteet.add(oikeaAlakulma);
        return pisteet;
    }
}
