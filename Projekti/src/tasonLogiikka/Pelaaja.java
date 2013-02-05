/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

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
     * Suunta johon pelaaja haluaa liikkua, eli mihin suuntaan pelaaja
     * on painanut nappia
     */
    private int xSuunta;
    
    /**
     * Muuttuja joka antaa pelaajan vaikuttaa hypyn korkeuteen
     */
    private int isoHyppy;
    
    /**
     * Kuinka monta pikseliä/frame pelaajan hahmo putoaa y-akselilla
     */
    private final int putoamiskiihtyvyysPerFrame;   // joopa
    
    /**
     * Pelaajan putoamisen maksiminopeus, jonka saavutettuaan putoaminen
     * ei enää kiihdy
     */
    private final int terminaalinopeus;             // joo

    /**
     * Pelaajan hahmon korkeus pikseleinä
     */
    private final int ukkelinKorkeus;               // heh
    
    /**
     * Taso jolla pelaaja liikkuu
     */
    private Taso taso;
    
    /**
     * Mihin esteeseen pelaaja on osunut viimeksi
     */
    private EsteenTyyppi osuikoJohonkin = null;     // dsfasd

    /**
     * Konstruoidaan pelaaja ja laitetaan se parametrina annettuun tasoon.
     * 
     * @param taso
     */
    public Pelaaja(Taso taso) {
        super(taso.getPelaajanAlkusijainti().getX(),
                taso.getPelaajanAlkusijainti().getY());
        this.taso = taso;
        yNopeus = 0;
        xNopeus = 0;
        xSuunta = 0;
        isoHyppy = 0;
        putoamiskiihtyvyysPerFrame = 1;
        terminaalinopeus = 50;
        ukkelinKorkeus = 32;
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
         * hyppynappia pohjassa
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

        // Aloitamme testaamisen hahmon vasemmasta laidasta
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
            testattavaPiste.siirra(0, -ukkelinKorkeus);
            suunta = -1;
        }

        /* Testataan sekä hahmon vasen että oikea laita. So far ei liene
         * mielekästä testata tämän suuremmalla resoluutiolla
         */
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

    /**
     * Metodi joka testaa onko pisteessä este. Jos on, metodi säätää myös
     * pelaajan attribuutin osuikoJohonkin vastaamaan esteen tyyppiä.
     */
    private boolean testaaPiste(Piste piste) {
        if (taso.onkoPisteessaEste(piste) == EsteenTyyppi.ESTE) {
            return true;
        } else if (taso.onkoPisteessaEste(piste) == EsteenTyyppi.MAALI) {
            this.osuikoJohonkin = EsteenTyyppi.MAALI;
            return true;
        } else if (taso.onkoPisteessaEste(piste) == EsteenTyyppi.KUOLO) {
            this.osuikoJohonkin = EsteenTyyppi.KUOLO;
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
        int suunta;

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
        Piste p0 = new Piste(this.getX() + 12 * suunta,
                this.getY());
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
        return (xTestattava-1) * suunta;
    }

    /*
     * Palauttaa esteen korkeuden
     */
    private int testaaPystyrivi(Piste p0) {
        Piste p1 = new Piste(p0.getX(), p0.getY() - ukkelinKorkeus);
        for (int i = 0; i < ukkelinKorkeus; i++) {
            if (testaaPiste(p1)) {
                break;
            }
            p1.siirra(0, 1);
        }
        return p0.getY()-p1.getY();
    }

    /**
     * Metodi joka kutsutaan kun pelaaja painaa hyppynappia.
     */
    public void aloitaHyppy() {
        /* Testataan onko hahmon alla maata.
         * 
         */
        Piste p0 = new Piste(this.getX() - 12, this.getY() + 1);
        Piste p1 = new Piste(this.getX() + 12, this.getY() + 1);
        if (testaaPiste(p0) || testaaPiste(p1)) {
            /* 
             * isoHyppy mahdollistaa hypyn korkeuden säätelyn siten, että
             * pidempi painallus = korkeampi hyppy
             */
            isoHyppy = 9;
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
}