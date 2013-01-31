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
 *
 * @author 41407
 */
public class Tiedostonlukija {

    private File tiedosto;
    private Scanner lukija;
    private ArrayList<String> rivit;

    public Tiedostonlukija(String tiedostonnimi) throws Exception {
        this.tiedosto = new File(tiedostonnimi);
        lukija = new Scanner(tiedosto);
        this.rivit = new ArrayList();
        rivitTalteen();
    }

    public void rivitTalteen() {
        while (lukija.hasNext()) {
            rivit.add(lukija.nextLine());
        }
    }

    public ArrayList<String> getRivit() {
        return rivit;
    }

    public Taso luoTaso() {
        TasonLuonti duunaasTaso = new TasonLuonti(rivit);
        return duunaasTaso.getTaso();
    }
}
