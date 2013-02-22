/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;

/**
 * Tason suorakaiteen muotoinen perusrakennepalikka.
 *
 * @author 41407
 */
public class Este {

    /**
     * Esteen vasen yläkulma?
     */
    private Piste vasenYlakulma;
    /**
     * Esteen oikea alakulma!
     */
    private Piste oikeaAlakulma;
    /**
     * Esteen tyyppi, kuten määritelty enumeraattorissa EsteenTyyppi
     */
    private EsteenTyyppi tyyppi = EsteenTyyppi.ESTE;

    public EsteenTyyppi getTyyppi() {
        return tyyppi;
    }

    /**
     * Tehdään este antamalla 4 inttiä
     *
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     */
    public Este(int x0, int y0, int x1, int y1) {
        this.vasenYlakulma = new Piste(x0, y0);
        this.oikeaAlakulma = new Piste(x1, y1);
        tarkistaPisteet();
    }

    /**
     * Tehdään este antamalla 2 Pistettä
     *
     * @param xy0
     * @param xy1
     */
    public Este(Piste xy0, Piste xy1) {
        this.vasenYlakulma = xy0;
        this.oikeaAlakulma = xy1;
        tarkistaPisteet();
    }

    /**
     * Tehdään este antamalla pisteet arraylistissä
     *
     * @param pisteet
     */
    public Este(ArrayList<Piste> pisteet) {
        this.vasenYlakulma = pisteet.get(0);
        this.oikeaAlakulma = pisteet.get(1);
        tarkistaPisteet();
    }

    /**
     * Asettaa esteen tyypin vastaamaan parametriä
     *
     * @param tyyppi
     */
    public void setTyyppi(EsteenTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    /**
     * Testaa sijaitseeko parametrinä annettu piste esteen peittämällä alueella.
     *
     * @param piste
     * @return Jos sijaitsee, palauttaa EsteenTyypin. Jos ei, null
     */
    public EsteenTyyppi kuuluukoPiste(Piste piste) {
        if (piste.getX() >= vasenYlakulma.getX() && piste.getX() <= oikeaAlakulma.getX()
                && piste.getY() >= vasenYlakulma.getY() && piste.getY() <= oikeaAlakulma.getY()) {
            return tyyppi;
        }
        return null;
    }

    /**
     * @return Palauttaa arraylistinä esteen määrittävät pisteet.
     */
    public ArrayList<Piste> getPisteet() {
        ArrayList<Piste> pisteet = new ArrayList();
        pisteet.add(vasenYlakulma);
        pisteet.add(oikeaAlakulma);
        return pisteet;
    }

    @Override
    public String toString() {
        String tyyppi = "E ";
        if (this.tyyppi == EsteenTyyppi.ESTE) {
            tyyppi = "E ";
        } else if (this.tyyppi == EsteenTyyppi.KUOLO) {
            tyyppi = "K ";
        } else if (this.tyyppi == EsteenTyyppi.MAALI) {
            tyyppi = "M ";
        }
        return tyyppi
                + vasenYlakulma.getX() + " "
                + vasenYlakulma.getY() + " "
                + oikeaAlakulma.getX() + " "
                + oikeaAlakulma.getY() + "\n";
    }

    /**
     * Metodi jonka tehtävänä on tarkistaa että vasen yläkulma on todellakin
     * vasen yläkulma ja jne, jotta piirtäjä onnistuu piirtämisessä.
     */
    private void tarkistaPisteet() {
        /**
         * t niinku temporaalinen muuttuja
         */
        int x0, x1, y0, y1, t;
        x0 = vasenYlakulma.getX();
        y0 = vasenYlakulma.getY();
        x1 = oikeaAlakulma.getX();
        y1 = oikeaAlakulma.getY();

        if (x1 < x0) {
            t = x0;
            x0 = x1;
            x1 = t;
        }
        if (y1 < y0) {
            t = y0;
            y0 = y1;
            y1 = t;
        }
        
        this.vasenYlakulma = new Piste(x0, y0);
        this.oikeaAlakulma = new Piste(x1, y1);
    }
}
