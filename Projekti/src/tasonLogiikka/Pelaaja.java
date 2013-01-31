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

    public int getyNopeus() {
        return yNopeus;
    }

    public int getxNopeus() {
        return xNopeus;
    }

    public int getTerminaalinopeus() {
        return terminaalinopeus;
    }

    public Taso getTaso() {
        return taso;
    }

    /**
     * Jumal-metodi
     */
    public void eksistoi() {
        liikuta();
        lenna();
    }

    /**
     * Metodi joka kutsutaan siinä vaiheessa kun hahmo ei ole hyppäämässä
     * ylöspäin eli melkein aina.
     *
     * Siirtää hahmon nopeuden määrittämään pisteeseen. Mikäli pisteessä on
     * este, metodi etsii sitä lähinnä olevan pisteen mihin voi siirtyä.
     */
    private void lenna() {
        if (isoHyppy > 0) {
            yNopeus = -10;
            isoHyppy--;
        }

        if (yNopeus < terminaalinopeus && isoHyppy == 0) {
            yNopeus += putoamiskiihtyvyysPerFrame;
        }

        this.siirra(0, testaaPisteYAkselilla(yNopeus));
    }

    private int testaaPisteYAkselilla(int nopeus) {
        // Aloitamme testaamisen hahmon vasemmasta laidasta
        Piste testattavaPiste = new Piste(this.getX() - 12, this.getY());

        boolean loytyiEste = false;
        int yTestattava = 0;
        int suunta = 1;

        if (nopeus < 0) {
            testattavaPiste.siirra(0, -ukkelinKorkeus);
            suunta = -1;
        }

        for (int i = 0; i < 2; i++) {
            while (Math.abs(yTestattava) < Math.abs(yNopeus)) {
                // Siirretään pistettä y-akselilla
                testattavaPiste.siirra(0, suunta);

                // Jos pisteessä on este, lopetetaan testaaminen ja palautetaan
                if (testaaPiste(testattavaPiste)) {
                    yNopeus = 0;
                    loytyiEste = true;
                    break;

                    // Jos ei, jatketaan etsintöjä
                } else {
                    yTestattava += suunta;
                }
            }
            if (loytyiEste == true) {
                break;
            }
            /* Siirretään testattava piste takaisin hahmon kohdalle ja kokeillaan
             * hahmon toista laitaa
             */
            testattavaPiste.siirra(24, -yNopeus * -suunta);
        }
        return yTestattava;
    }

    private boolean testaaPiste(Piste piste) {
        return (taso.onkoPisteessaEste(piste));
    }

    private void liikuta() {
        laskeUusiNopeus();
        this.siirra(testaaPisteXAkselilla(xNopeus), 0);
    }

    /*
     * Palauttaa lähimmän ei-este pisteen X-akselilla väliltä hahmon laita ja
     * xNopeus.
     */
    private int testaaPisteXAkselilla(int nopeus) {


        int xTestattava = 0;
        int suunta;
        int absNopeus = xNopeus;
        boolean loytyikoEste = false;

        if (xNopeus > 0) {
            /* Emme käytä xSuuntaa, koska jos pelaaja haluaa liikkua oikealle
             * ja törmää vasemmalta puolelta seinään ennen kuin vauhti on nolla,
             * hahmo uppoaisi seinään. Sen sijaan kehitämme hahmon inertian 
             * suunnalle oman näppärän muuttujan.
             */
            suunta = 1;



        } else if (xNopeus < 0) {

            // Tämän takia emme käyttäneet math.absia            
            absNopeus = Math.abs(xNopeus);
            suunta = -1;
        } else {
            // Hahmon ollessa paikallaan palautamme nollan. duh!
            return 0;
        }

        /* Koska kivitaulussa lukee, että hahmon leveys on 24 pixeliä, ja
         * meitä ei kiinnosta hahmon sisällä mahdollisesti majailevat esteet,
         */
        Piste testattavaPiste = new Piste(this.getX() + 12 * suunta, this.getY());


        /*
         * Loopilla testaamme pikselit koko ukkelin korkeudelta 2px resoluutiolla.
         */
        while (testattavaPiste.getY() > this.getY() - ukkelinKorkeus) {
            xTestattava = 0;
            while (xTestattava < absNopeus) {
                testattavaPiste.siirra(suunta, 0);

                if (testaaPiste(testattavaPiste)) {

                    /* Jos este löytyy, hahmo pysähtyy kuin
                     *              ...seinään
                     */
                    xNopeus = 0;
                    xSuunta = 0;
                    loytyikoEste = true;
                    break;
                } else {
                    xTestattava++;
                }
            }
            if (loytyikoEste == true) {
                break;
            }
            testattavaPiste.setX(this.getX() + 12 * suunta);
            testattavaPiste.siirra(0, -2);
        }
        return xTestattava * suunta;
    }

    public void aloitaHyppy() {
        Piste testattavaPiste = new Piste(this.getX(), this.getY() + 1);
        if (testaaPiste(testattavaPiste)) {
            isoHyppy = 9;
        }
    }

    /*
     * Palauttaa erotuksen hahmon hotspotin ja maan väliltä
     */
    public void pysaytaHyppy() {
        isoHyppy = 0;
    }

    /**
     *
     *
     */
    public void liikuVasemmalle() {
        this.xSuunta = -1;
    }

    /**
     *
     *
     */
    public void liikuOikealle() {
        this.xSuunta = 1;
    }

    /**
     *
     */
    public void pysaytaLiike() {
        this.xSuunta = 0;
    }

    /*
     * Metodi jolla lasketaan pelaajalle uusi x-nopeus riippuen hahmon nykyisestä
     * xNopeudesta ja suunnasta.
     * 
     * Kiihtyvyyskerroin on mukana sen takia, että jos hahmo on ilmassa, sen
     * liikuttelu on hitaampaa.
     */
    private void laskeUusiNopeus() {

        // Hahmon sivuttaiskiihtyvyys pikseliä/frame kun se on maassa
        int kiihtyvyyskerroin = 5;

        // Hahmon sivuttaiskiihtyvyys kun se on ilmassa
        if (yNopeus != 0) {
            kiihtyvyyskerroin = 2;
        }

        // Liikuttaessa oikealle
        if (xSuunta == 1) {
            // Maksiminopeus 10 px/f
            if (xNopeus < 10) {
                xNopeus += kiihtyvyyskerroin;
            }

            // Liikuttaessa vasemmalle
        } else if (xSuunta == -1) {
            // Maksiminopeus 10 px/f
            if (xNopeus > -10) {
                xNopeus -= kiihtyvyyskerroin;
            }

            // Hidastuminen kun mitään nappia ei paineta
        } else if (kiihtyvyyskerroin == 5 && xSuunta == 0) {

            // Koska muutos on ±2, erikoistapauksessa kun nopeus on 1 tai -1
            if (xNopeus == 1 || xNopeus == -1) {
                xNopeus = 0;
            } else if (xNopeus > 0) {
                xNopeus -= 2;
            } else if (xNopeus < 0) {
                xNopeus += 2;
            }
        }
    }
}