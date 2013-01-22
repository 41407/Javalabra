/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

/**
 *
 * @author jiji
 */
public class Pelaaja extends Piste {

    private int yNopeus;
    private final int putoamiskiihtyvyys;  // joopa
    private final int terminaalinopeus;    // joo
    
    public Pelaaja(int x, int y) {
        super(x, y);
        yNopeus = 0;
        putoamiskiihtyvyys = 1;
        terminaalinopeus = 10;
    }

    /**
     * Testaa onko hahmon alla este. Jos ei, hahmo putoaa. Jos on, hahmo pysyy
     * paikoillaan.
     */
    public void putoa(Taso taso) {
        if (yNopeus < terminaalinopeus) {
            yNopeus += putoamiskiihtyvyys;
        }
        Piste testattavaPiste = new Piste(this.getX(), this.getY() + yNopeus);
        if (!taso.onkoPisteessaEste(testattavaPiste)) {
            this.siirra(0, yNopeus);
        } else {
            while(taso.onkoPisteessaEste(testattavaPiste)) {
                testattavaPiste.siirra(0, -1);
                yNopeus--;
            }
            this.siirra(0, yNopeus);
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