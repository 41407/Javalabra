/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 * Luokka pitää sisällään kaiken logiikan pelaajan liikkumiseen liittyen, ja
 * siihen kuuluu myös Taso.
 *
 * @author jiji
 */
public class Pelaaja extends Piste {

    /**
     * Hahmon nopeus y-akselilla eli pystysuunnassa
     */
    private int yNopeus;
    /**
     * Hahmon nopeus x-akselilla
     */
    private int xNopeus;
    /**
     * Suunta johon pelaaja haluaa liikkua, eli mihin suuntaan pelaaja on
     * painanut nappia
     */
    private int xSuunta;
    /**
     * Muuttuja joka antaa pelaajan vaikuttaa hypyn korkeuteen
     */
    private int isoHyppy;
    /**
     * Kuinka monta pikseliä/frame pelaajan hahmo putoaa y-akselilla
     */
    private final int putoamiskiihtyvyysPerFrame;
    /**
     * Pelaajan putoamisen maksiminopeus, jonka saavutettuaan putoaminen ei enää
     * kiihdy
     */
    private final int terminaalinopeus;
    /**
     * Pelaajan hahmon korkeus pikseleinä
     */
    private final int hahmonKorkeus;
    /**
     * Taso jolla pelaaja liikkuu
     */
    private Taso taso;
    /**
     * Mihin esteeseen pelaaja on osunut viimeksi
     */
    private EsteenTyyppi osuikoJohonkin = null;

    /**
     * Konstruoidaan pelaaja
     *
     */
    public Pelaaja() {
        super(0, 0);
        xNopeus = 0;
        xSuunta = 0;
        isoHyppy = 0;
        putoamiskiihtyvyysPerFrame = 1;
        terminaalinopeus = 50;
        /**
         * Nopeudeksi määritetään terminaalinopeus, koska jos pelaaja alottaa
         * ilmasta, haluamme sen putoavan täysiiiiiii
         */
        yNopeus = terminaalinopeus;
        hahmonKorkeus = 32;
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
     * Asettaa tason pelaajalle, ja siirtää pelaajan tason määrittämään
     * aloituspaikkaan. Myös resetoi osuikoJohonkinnin just in case.
     *
     * @param taso
     */
    public void setTaso(Taso taso) {
        this.taso = taso;
        this.setXY(taso.getPelaajanAlkusijainti());
        this.osuikoJohonkin = null;
    }

    /**
     * Metodi jolla käynnistetään kaikki pelaajan logiikkaan liittyvät metodit.
     *
     * @return osuikoJohonkin, josta metodia kutsuva pelilogiikka voi päätellä
     * kuinka jatketaan
     */
    public EsteenTyyppi eksistoi() {
        liikuta();
        lenna();
        return this.osuikoJohonkin;
    }

    /**
     * Metodi joka säätelee pelaajan liikettä Y-akselilla.
     */
    private void lenna() {
        /*
         * Ehtolause joka käsittelee tilanteen jossa pelaaja pitää
         * hyppynappia pohjassa hypätäkseen korkeammalle
         */
        if (isoHyppy > 0) {
            yNopeus = -10;
            isoHyppy--;
        }
        /*
         * Normaali tilanne
         */
        if (yNopeus < terminaalinopeus && isoHyppy == 0) {
            yNopeus += putoamiskiihtyvyysPerFrame;
        }
        /* 
         * Testataan osuuko pelaaja maahan ja siirrellään sen mukaan
         */
        this.siirra(0, testaaPisteYAkselilla(yNopeus));
    }

    /**
     * Metodi joka testaa onko hahmon reitillä Y-akselilla esteitä.
     *
     * @param nopeus hahmosta laskettuna etäisin testattava piste y-akselilla
     * @return Y-koordinaatti johon hahmo voi siirtyä ilman että se osuu
     * esteeseen (<= nopeus)
     */
    private int testaaPisteYAkselilla(int nopeus) {

        // Testattavan vaakarivin vasen laita on hahmon vasen laita
        Piste testattavaPiste = new Piste(this.getX() - 12, this.getY());

        // Sisäkkäisten looppien keskeyttämistä varten
        boolean loytyiEste = false;

        // Iteraattori
        int yTestattava = 0;

        // Koodia selkeyttävä apumuuttuja
        int suunta = 1;

        /* Jos hahmo liikkuu ylöspäin, hahmon korkeus on huomioitava koska
         * nollapiste on hahmon jaloissa
         */
        if (nopeus < 0) {
            testattavaPiste.siirra(0, -hahmonKorkeus);
            suunta = -1;
        }

        /**
         * Looppi joka käy läpi välin hahmon sijainti - hahmon nopeuden saneleva
         * sijainti. Iteraattori yTestattava saa arvon joka on lähinnä pelaajaa
         * oleva paikka johon on mahdollista siirtyä, joka on väliltä 0 ...
         * yNopeus
         */
        while (yTestattava <= Math.abs(this.yNopeus)) {
            if (testaaVaakarivi(testattavaPiste)) {
                this.yNopeus = 0;
                break;
            }
            yTestattava++;
            testattavaPiste.siirra(0, suunta);
        }
        return yTestattava * suunta;
    }

    /**
     * Testaa onko pisteen määrittämällä 24 pikselin levyisellä vaakarivillä
     * este.
     *
     * @param p0 Testattavan rivin vasen laita
     * @return true jos pisteessä on este, false jos ei.
     */
    private boolean testaaVaakarivi(Piste p0) {
        Piste p1 = new Piste(p0.getX(), p0.getY());
        for (int i = 0; i < 24; i++) {
            if (testaaPiste(p1)) {
                return true;
            }
            p1.siirra(1, 0);
        }
        return false;
    }

    /**
     * Metodi joka testaa onko pisteessä este. Jos on, metodi säätää myös
     * pelaajan attribuutin osuikoJohonkin vastaamaan esteen tyyppiä.
     * 
     * Myös escin lähmäisy on käsitelty tässä :/
     */
    private boolean testaaPiste(Piste piste) {
        if(this.osuikoJohonkin == EsteenTyyppi.QUIT) {
            return false;
        }
        EsteenTyyppi este = taso.onkoPisteessaEste(piste);
        if (este != null) {
            this.osuikoJohonkin = este;
            return true;
        }
        return false;
    }

    public EsteenTyyppi getOsuikoJohonkin() {
        return osuikoJohonkin;
    }

    /**
     * Metodi joka käynnistää prosessin jonka seurauksena pelaajahahmo liikkuu
     * oikeaan paikkaan X-akselilla.
     */
    private void liikuta() {
        laskeUusiNopeus();
        this.siirra(testaaPisteXAkselilla(xNopeus), 0);
    }

    /**
     * Palauttaa lähimmän ei-este pisteen X-akselilla väliltä hahmon laita ja
     * xNopeus. Toimii aikalailla kuten testaaPisteYAkselilla().
     *
     */
    private int testaaPisteXAkselilla(int nopeus) {

        // Iteraattori
        int xTestattava = 0;
        
        // Suunnan apumuuttuja
        int suunta;
        
        // Koodia selkeyttävä apumuuttuja
        int absNopeus = xNopeus;

        if (xNopeus > 0) {
            /* Emme käytä xSuuntaa, koska jos pelaaja haluaa liikkua oikealle
             * ja törmää vasemmalta puolelta seinään ennen kuin vauhti on nolla,
             * hahmo uppoaisi seinään. Sen sijaan kehitämme hahmon inertian 
             * suunnalle oman näppärän apumuuttujan.
             */
            suunta = 1;
        } else if (xNopeus < 0) {
            // Suunnan määrittämisen takia emme käyttäneet math.absia            
            absNopeus = Math.abs(xNopeus);
            suunta = -1;
        } else {
            // Hahmon ollessa paikallaan palautamme nollan. duh!
            return 0;
        }

        /* Koska kivitaulussa lukee, että hahmon leveys on 24 pixeliä, ja
         * meitä ei kiinnosta hahmon sisällä mahdollisesti majailevat esteet,
         */
        Piste p0 = new Piste(this.getX() + 12 * suunta, this.getY());

        int esteenKorkeus = 0;

        while (xTestattava < absNopeus) {
            esteenKorkeus = testaaPystyrivi(p0);
            if (esteenKorkeus > 0) {
                break;
            }
            p0.siirra(suunta, 0);
            xTestattava++;
        }
        p0.setX(this.getX() + 12 * suunta);
        if (esteenKorkeus > 0) {
            /* Erikoistapaus jossa hahmon kohtaama este on 2px korkea.
             * Eli pelaaja pääsee yli matalista esteistä hyppäämättä.
             */
            if (esteenKorkeus < 3) {
                this.yNopeus = -2;
            } else {
                /* Jos este löytyy ja on korkea, hahmo pysähtyy kuin
                 *              ...seinään
                 */

                xNopeus = 0;
            }
        }
        return (xTestattava - 1) * suunta;
    }

    /**
     * Ottaa testattavan pystyrivin ylälaidan. Palauttaa esteen korkeuden.
     * Testaa koko hahmon korkeudelta
     */
    private int testaaPystyrivi(Piste p0) {
        Piste p1 = new Piste(p0.getX(), p0.getY() - hahmonKorkeus);
        for (int i = 0; i < hahmonKorkeus; i++) {
            if (testaaPiste(p1)) {
                break;
            }
            p1.siirra(0, 1);
        }
        return p0.getY() - p1.getY();
    }

    /**
     * Metodi joka kutsutaan kun pelaaja painaa hyppynappia.
     */
    public void aloitaHyppy() {
        /**
         * Testataan onko hahmon alla maata. Aloitetaan hahmon vasemmasta
         * laidasta
         */
        Piste p0 = new Piste(this.getX() - 12, this.getY() + 1);

        if (testaaVaakarivi(p0)) {
            /**
             * isoHyppy mahdollistaa hypyn korkeuden säätelyn siten, että
             * pidempi painallus = korkeampi hyppy
             */
            isoHyppy = 9;
            /**
             * Valmistellaan testattavat pisteet seinähyppyä varten
             */
        } else {
            p0.siirra(-1, 0);
            Piste p1 = new Piste(p0.getX() + 26, p0.getY());
            /**
             * Testataan onnistuuko seinähyppy
             *
             * Kommentoidut osiot olivat kokeilu joista en tykännyt. Ne nyt on
             * siellä jostain syystä vielä tallella :)
             */
            if (testaaPystyrivi(p0) > 0 /*&& this.xSuunta == -1*/) {
                isoHyppy = 9;
                xNopeus = 15;
            } else if (testaaPystyrivi(p1) > 0 /*&& this.xSuunta == 1*/) {
                isoHyppy = 9;
                xNopeus = -15;
            }
        }
    }

    /**
     * Metodi joka kutsutaan kun pelaaja vapauttaa hyppynapin.
     */
    public void pysaytaHyppy() {
        isoHyppy = 0;
    }

    /**
     * Metodi joka käskee pelaajahahmoa aloittamaan liikkumisen vasemmalle.
     */
    public void liikuVasemmalle() {
        this.xSuunta = -1;
    }

    /**
     * Metodi joka käskee pelaajahahmoa aloittamaan liikkumisen oikealle.
     */
    public void liikuOikealle() {
        this.xSuunta = 1;
    }

    /**
     * Metodi joka käskee pelaajahahmoa pysäyttämään x-suuntaisen liikkeen.
     */
    public void pysaytaLiike() {
        this.xSuunta = 0;
    }

    /**
     * Metodi jolla lasketaan pelaajalle uusi x-nopeus riippuen hahmon
     * nykyisestä xNopeudesta ja suunnasta.
     *
     * Kiihtyvyyskerroin on mukana sen takia, että jos hahmo on ilmassa, sen
     * liikuttelun tulee olla hitaampaa.
     */
    private void laskeUusiNopeus() {
        int kiihtyvyyskerroin = 1;

        // Hahmon sivuttaiskiihtyvyys pikseliä/frame kun se on maassa
        if (yNopeus == 0) {
            kiihtyvyyskerroin = 5;
            /**
             * Nopeuden rajoitus
             */
            if (xNopeus > 10) {
                xNopeus = 10;
            } else if (xNopeus < -10) {
                xNopeus = -10;
            }
        } else // Hahmon sivuttaiskiihtyvyys kun se on ilmassa
        {
            kiihtyvyyskerroin = 1;
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

            /**
             * Erikoistapauksessa kun nopeus on 1 tai -1, nopeudeksi tulee 0
             * jotta hahmo ei jäisi tärisemään paikoilleen : )
             */
            if (xNopeus == 1 || xNopeus == -1) {
                xNopeus = 0;
                /*
                 * Ja muussa tapauksessa nopeuden itseisarvoa vähennetään 3:lla.
                 */
            } else if (xNopeus > 0) {
                xNopeus -= 3;
            } else if (xNopeus < 0) {
                xNopeus += 3;
            }
        }

    }

    public void lopetaTaso() {
        this.osuikoJohonkin = EsteenTyyppi.QUIT;
    }
}

/**
 * Se oli siinä!
 */