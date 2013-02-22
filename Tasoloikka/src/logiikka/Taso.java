/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;

/**
 * Taso on alue jolla pelaajaolio liikkuu. Se sisältää esteitä ja se määrittää
 * pelaajalle aloitussijainnin, ja se vastaa pelaajaolion kyselyihin siitä, onko
 * pisteessä este.
 *
 * @author jiji
 */
public class Taso {

    /**
     * Lista tason esteistä
     */
    private ArrayList<Este> esteet;
    /**
     * Piste johon pelaaja siirretään tason alussa
     */
    private Piste pelaajanAlkusijainti;

    /**
     * Konstruktori jossa ei oteta kantaa pelaajan alkusijaintiin sijoittaa sen
     * pisteeseen (0,0)
     */
    public Taso() {
        this.esteet = new ArrayList();
        this.pelaajanAlkusijainti = new Piste(0, 0);
    }

    /**
     * Konstruktori jolle annetaan kokonaislukuina pelaajan alkusijainti.
     *
     * @param x
     * @param y
     */
    public Taso(int x, int y) {
        this.esteet = new ArrayList();
        this.pelaajanAlkusijainti = new Piste(x, y);
    }

    /**
     * Lisää Esteen tasoon.
     *
     * @param este lisättävä este
     */
    public void lisaaEste(Este este) {
        esteet.add(este);
    }

    /**
     * Säätää parametrina annetun pisteen pelaajan alkusijainniksi tasolla.
     *
     * @param sijainti
     */
    public void asetaPelaajanAlkusijainti(Piste sijainti) {
        this.pelaajanAlkusijainti = sijainti;
    }

    public Piste getPelaajanAlkusijainti() {
        return pelaajanAlkusijainti;
    }

    /**
     * @return Tasoon kuuluvat esteet arraylistinä.
     */
    public ArrayList<Este> getEsteet() {
        return esteet;
    }

    /**
     * Kysyy tason esteiltä kuuluuko parametrinä annettu piste joihinkin niistä.
     *
     * @param piste testattava piste
     * @return true jos pisteessä on este, false jos ei.
     */
    public EsteenTyyppi onkoPisteessaEste(Piste piste) {
        for (Este este : esteet) {
            if (este.kuuluukoPiste(piste) != null) {
                return este.kuuluukoPiste(piste);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.pelaajanAlkusijainti + "\n";
        for (Este este : esteet) {
            s += este.toString() + "\n";
        }
        return s;
    }
    
    
}
