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

    private ArrayList<Este> esteet;
    private Piste pelaajanAlkusijainti;

    public Taso() {
        this.esteet = new ArrayList();
        this.pelaajanAlkusijainti = new Piste(0,0);
    }

    public void lisaaEste(Este este) {
        esteet.add(este);
    }

    public void asetaPelaajanAlkusijainti(Piste sijainti) {
        this.pelaajanAlkusijainti = sijainti;
    }

    public Piste getPelaajanAlkusijainti() {
        return pelaajanAlkusijainti;
    }

    /**
     *
     * @return Palauttaa arraylistinä tasoon kuuluvat esteet.
     */
    public ArrayList<Este> getEsteet() {
        return esteet;
    }

    /**
     * Testaa onko tason pisteessä piste este vai ei.
     *
     * @param piste
     * @return true jos pisteessä on este, false jos ei.
     */
    public boolean onkoPisteessaEste(Piste piste) {
        for (Este este : esteet) {
            if (este.kuuluukoPiste(piste)) {
                return true;
            }
        }
        return false;
    }
}
