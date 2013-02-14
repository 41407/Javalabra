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
 *
 * @author jiji
 */
public class Tiedostonkirjoittaja {

    public Tiedostonkirjoittaja() {
    }

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
