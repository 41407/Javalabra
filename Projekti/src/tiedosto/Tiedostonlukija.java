/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedosto;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import tasonLogiikka.Este;
import tasonLogiikka.Taso;
import tasonLogiikka.TasonLuonti;

/**
 * Luokka joka valmistelee rivit tiedostosta luokkaa TasonLuonti varten.
 * 
 * @author 41407
 */
public class Tiedostonlukija {

    /**
     * Käsiteltävä tiedosto
     */
    private File tiedosto;
    
    /**
     * Tiedostoa käsittelevä lukija
     */
    private Scanner lukija;
    
    /**
     * Tiedoston rivit
     */
    private ArrayList<String> rivit;

    /**
     * Konstruktorissa Tiedostonlukija ottaa tiedoston saman tien talteen
     * oliomuuttujaansa.
     * 
     * @param tiedostonnimi
     * @throws Exception 
     */
    public Tiedostonlukija(String tiedostonnimi) throws Exception {
        this.tiedosto = new File(tiedostonnimi);
        lukija = new Scanner(tiedosto);
        this.rivit = new ArrayList();
        rivitTalteen();
    }
    
    private void rivitTalteen() {
        while (lukija.hasNext()) {
            rivit.add(lukija.nextLine());
        }
    }

    public ArrayList<String> getRivit() {
        return rivit;
    }

    /**
     * Metodi joka käskee TasonLuontia kehittämään tason this.riveistä
     * 
     * @return Palauttaa luodun tason.
     */
    public Taso luoTaso() {
        TasonLuonti tasonLuonti = new TasonLuonti(rivit);
        return tasonLuonti.getTaso();
    }
}
