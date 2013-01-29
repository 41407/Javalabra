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
    private int xNopeus;
    private int xSuunta;
    private int isoHyppy;
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
        xNopeus = 0;
        xSuunta = 0;
        isoHyppy = 0;
        putoamiskiihtyvyysPerFrame = 1;
        terminaalinopeus = 50;
        ukkelinKorkeus = 32;
        this.taso = taso;
    }

    /**
     * Jumal-metodi
     */
    public void eksistoi() {
        liikuta();

        if (yNopeus < terminaalinopeus && isoHyppy == 0) {
            yNopeus += putoamiskiihtyvyysPerFrame;
        }
        if (yNopeus < 0 || isoHyppy > 0) {
            lenna();
        } else {
            putoa();
        }
    }

    /**
     * Metodi joka kutsutaan siinä vaiheessa kun hahmo ei ole hyppäämässä
     * ylöspäin eli melkein aina.
     *
     * Siirtää hahmon nopeuden määrittämään pisteeseen. Mikäli pisteessä on
     * este, metodi etsii sitä lähinnä olevan pisteen mihin voi siirtyä.
     */
    public void putoa() {

        Piste testattavaPiste = new Piste(this.getX() - 12, this.getY());

        boolean loytyiEste = false;
        int i = 0;
        for (int j = 0; j < 2; j++) {
            while (i < yNopeus) {
                testattavaPiste.siirra(0, 1);

                if (testaaPiste(testattavaPiste)) {
                    yNopeus = 0;
                    loytyiEste = true;
                    break;
                } else {
                    i++;
                }
            }
            if(loytyiEste == true) {
                break;
            }
            testattavaPiste.siirra(24,-yNopeus);
        }
        this.siirra(0, i);
    }

    public Taso getTaso() {
        return taso;
    }

    private boolean testaaPiste(Piste piste) {
        return (taso.onkoPisteessaEste(piste));
    }

    private boolean testaaVali(Piste piste) {

        return (taso.onkoPisteessaEste(piste));
    }

    private void lenna() {
        if(isoHyppy > 0) {
            yNopeus = -10;
            isoHyppy--;
        }
        
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
     *
     * @param taso
     */
    public void liikuVasemmalle() {
        this.xSuunta = -1;
    }

    /**
     *
     * @param taso
     */
    public void liikuOikealle() {
        this.xSuunta = 1;
    }

    public void pysaytaLiike() {
        this.xSuunta = 0;
    }

    private void laskeUusiNopeus() {
        int kiihtyvyyskerroin = 2;
        if (yNopeus != 0) {
            kiihtyvyyskerroin = 1;
        }
        if (xSuunta == 1) {
            if (xNopeus < 10) {
                xNopeus += kiihtyvyyskerroin;
            }
        } else if (xSuunta == -1) {
            if (xNopeus > -10) {
                xNopeus -= kiihtyvyyskerroin;
            }
        } else if (kiihtyvyyskerroin == 2) {
            if (xNopeus > 0) {
                xNopeus--;
            } else if (xNopeus < 0) {
                xNopeus++;
            }

        }
    }

    private void liikuta() {
        laskeUusiNopeus();

        Piste testattavaPiste = new Piste(this.getX(), this.getY() - 5);
        int i = 0;
        int suunta;
        int absNopeus = xNopeus;

        if (xNopeus > 0) {
            suunta = 1;
            
            // koska kivitaulussa lukee, että hahmon leveys on 24 pixeliä
            testattavaPiste.siirra(12, 0);
        } else if (xNopeus < 0) {
            absNopeus *= -1;
            suunta = -1;
            testattavaPiste.siirra(-12, 0);
        } else {
            suunta = 0;
        }

        while (i < absNopeus) {
            testattavaPiste.siirra(suunta, 0);

            if (testaaPiste(testattavaPiste)) {
                xNopeus = 0;
                xSuunta = 0;
                break;
            } else {
                i++;
            }
        }
        this.siirra(i * suunta, 0);
    }

    public void aloitaHyppy() {
        Piste testattavaPiste = new Piste(this.getX(), this.getY() + 1);
        if (testaaPiste(testattavaPiste)) {
            isoHyppy = 9;
        }
    }

    public void pysaytaHyppy() {
        isoHyppy = 0  ;
    }
}