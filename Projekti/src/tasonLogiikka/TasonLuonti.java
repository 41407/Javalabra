/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

import java.util.ArrayList;

/**
 * Luokka jolla muutetaan stringeistä koostuva arraylist tasoksi.
 * 
 * @author 41407
 */
public class TasonLuonti {

    /**
     * Käsiteltävät rivit
     */
    private ArrayList<String> rivit;

    /**
     * Taso jonka kanssa työskennellään ja joka lopuksi palautetaan
     */
    private Taso taso;

    public TasonLuonti(ArrayList<String> rivit) {
        this.rivit = rivit;
        this.taso = new Taso();
        teeTaso();
    }

    private void teeTaso() {
        for (String string : rivit) {
            char etumerkki = string.toUpperCase().charAt(0);
            if (etumerkki == 'E') {
                esteRivi(string, EsteenTyyppi.ESTE);
            } else if (etumerkki == 'P') {
                pelaajaRivi(string);
            } else if (etumerkki == 'M') {
                esteRivi(string, EsteenTyyppi.MAALI);
            } else if (etumerkki == 'K') {
                esteRivi(string, EsteenTyyppi.KUOLO);
            }
        }
    }

    public Taso getTaso() {
        System.out.println(taso.getEsteet());
        return taso;
    }

    /*
     * Välivaiheet eroteltu selkeyden vuoksi.
     */
    private void esteRivi(String string, EsteenTyyppi tyyppi) {
        ArrayList<Integer> koordinaatit = stringKoordinaateiksi(string);
        ArrayList<Piste> pisteet = muutaPisteiksi(koordinaatit);
        Este e = new Este(pisteet);
        e.setTyyppi(tyyppi);
        taso.lisaaEste(e);
    }

    private void pelaajaRivi(String string) {
        ArrayList<Integer> koordinaatit = stringKoordinaateiksi(string);
        ArrayList<Piste> pisteet = muutaPisteiksi(koordinaatit);
        taso.asetaPelaajanAlkusijainti(pisteet.get(0));
    }

    private ArrayList<Integer> stringKoordinaateiksi(String string) {
        /* 
         * Alotetaan string[2]:sta koska ekat 2 merkkiä on E ja spacebar
         */
        ArrayList<Integer> koordinaatit = new ArrayList();
        int luku;
        int j = 2;
        for (int i = j; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                luku = Integer.parseInt(string.substring(j, i));
                j = i + 1;
                koordinaatit.add(luku);
            } else if (i == string.length() - 1) {
                luku = Integer.parseInt(string.substring(j));
                koordinaatit.add(luku);
            }
        }
        return koordinaatit;
    }

    private ArrayList<Piste> muutaPisteiksi(ArrayList<Integer> koordinaatit) {
        ArrayList<Piste> pisteet = new ArrayList();

        int j = 0;
        for (int i = 0; i < koordinaatit.size(); i++) {
            if (i % 2 == 0) {
                j = koordinaatit.get(i);
            } else {
                pisteet.add(new Piste(j, koordinaatit.get(i)));
            }
        }
        return pisteet;
    }
}