/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

/**
 *
 * @author jiji
 */
public class Hahmo extends Piste {

    public Hahmo(int x, int y) {
        super(x, y);
    }

    /**
     * Testaa onko hahmon alla este. Jos ei, hahmo putoaa. Jos on, hahmo pysyy
     * paikoillaan.
     */
    public void putoa(Taso taso) {
        Piste testattavaPiste = new Piste(this.getX(), this.getY() + 1);
        if (!taso.onkoPisteessaEste(testattavaPiste)) {
            this.siirra(0, 1);
        }
    }

    public void liikuVasemmalle(Taso taso) {
        Piste testattavaPiste = new Piste(this.getX() - 1, this.getY());
        if (!taso.onkoPisteessaEste(testattavaPiste)) {
            this.siirra(-1, 0);                     // liikkuminen vasemmalle ja oikealle
        }                                           // kannattanee kyllä yhdistää yhteen metodiin
    }                                               // jossain vaiheessa

    public void liikuOikealle(Taso taso) {
        Piste testattavaPiste = new Piste(this.getX() + 1, this.getY());
        if (!taso.onkoPisteessaEste(testattavaPiste)) {
            this.siirra(1, 0);
        }
    }
}