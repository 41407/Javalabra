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
    private final int putoamiskiihtyvyysPerFrame;   // joopa
    private final int terminaalinopeus;             // joo
    private final int ukkelinKorkeus;               // heh
    private Taso taso;

    /**
     * @param x
     * @param y
     * @param taso
     */
    public Pelaaja(int x, int y, Taso taso) {
        super(x, y);
        yNopeus = 0;
        putoamiskiihtyvyysPerFrame = 1;
        terminaalinopeus = 10;
        ukkelinKorkeus = 32;
        this.taso = taso;
    }

    /**
     * Jumal-metodi
     */
    public void eksistoi() {
        if (yNopeus < terminaalinopeus) {
            yNopeus += putoamiskiihtyvyysPerFrame;
        }
        if (yNopeus < 0) {
            lenna();
        } else {
            putoa();
        }
    }
    
    public void putoa(){
        Piste testattavaPiste = new Piste(this.getX(), this.getY() + yNopeus);
        if (!testaaPiste(testattavaPiste)) {
            this.siirra(0, yNopeus);
        } else {
            while (testaaPiste(testattavaPiste)) {
                testattavaPiste.siirra(0, -1);
                yNopeus--;
            }
            this.siirra(0, yNopeus);
        }
    }

    private boolean testaaPiste(Piste piste) {
        return (taso.onkoPisteessaEste(piste));
    }

    private void lenna() {
        Piste piste = new Piste(this.getX(), this.getY()
                - this.ukkelinKorkeus + this.yNopeus);

        if (taso.onkoPisteessaEste(piste)) {
            while (testaaPiste(piste)) {
                piste.siirra(0, 1);
                yNopeus++;
            }
        }
        this.siirra(0, yNopeus);
    }

    /**
     * Käskee pelaajaoliota suorittamaan metodin joka käynnistää liikkumisen
     * vasemmalle.
     *
     * @param taso
     */
    public void liikuVasemmalle() {
        liikuta(-1);
    }

    /**
     * Käskee pelaajaoliota suorittamaan metodin joka käynnistää liikkumisen
     * oikealle.
     *
     * @param taso
     */
    public void liikuOikealle() {
        liikuta(1);
    }

    /**
     * Metodi joka käynnistää hahmon liikkumisen int suunnan ilmoittamaan
     * suuntaan, jossa -1 on vasemmalle ja 1 oikealle
     *
     * Tämä siksi että nyt on helppoa laajentaa hahmon x-liikuttelua
     *
     * @param taso
     */
    private void liikuta(int suunta) {
        Piste testattavaPiste = new Piste(this.getX() + suunta, this.getY());
        if (!taso.onkoPisteessaEste(testattavaPiste)) {
            this.siirra(suunta, 0);
        }
    }

    public void hyppaa() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}