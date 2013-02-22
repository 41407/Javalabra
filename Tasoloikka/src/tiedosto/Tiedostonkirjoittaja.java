/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedosto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka jolla kirjoitetaan tallennustiedostoon.
 * 
 * @author jiji
 */
public class Tiedostonkirjoittaja {

    public Tiedostonkirjoittaja() {
    }

    /**
     * Metodi joka käyttää FileWriteriä kirjoittaakseen annettuun 
     * tiedostoon annetun rivin.
     * 
     * @param tiedosto Tiedosto johon halutaan kirjoittaa
     * @param rivi Rivi joka halutaan kirjoittaa
     * @param ylikirjoitus Boolean joka määrittää halutaanko määritelty tiedosto
     * korvata uudella. Tällä hetkellä metodi ei tee mitään jos tämä on false
     */
    public void kirjoitaTiedosto(
            String tiedosto,
            String rivi,
            boolean ylikirjoitus) {

        if (ylikirjoitus) {
            try {
                FileWriter kirjoittaja = new FileWriter(tiedosto);
                kirjoittaja.write(rivi + "\n");
                kirjoittaja.close();
            } catch (IOException ex) {
                Logger.getLogger(
                        Tiedostonkirjoittaja.class.getName()).log(Level.SEVERE,
                        null,
                        ex);
            }
        }
    }
}
