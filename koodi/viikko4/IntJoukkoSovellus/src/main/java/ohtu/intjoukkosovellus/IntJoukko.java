
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    private int[] apuTaulu1;	// Laskutoimitusten aputaulu.
    private int[] apuTaulu2;    // Laskutoimitusten toinen aputaulu.
    private IntJoukko tulos;    // Laskutoimitusten tulos.
    //Olisin käyttänyt näitä kolmea kolmen viimeisen metodin copy-pasten poistoon, mutta
    //tuloksena oli töysin mystisesti compilation error joka kerta. :(

    public IntJoukko() {
	//Oletuskoko ja -kapasiteetti ovat 5.
        initToZero(5, 5);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
	//Oletuskapasiteetti on 5.
        initToZero(kapasiteetti, 5);

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti negatiivinen");//Korjattu
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko negatiivinen");//Korjattu
        }
        initToZero(kapasiteetti, kasvatuskoko);

    }

    private void initToZero(int kapasiteetti, int kasvatuskoko) {
	ljono = new int[kapasiteetti];
	for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {

        
        //Sisäkkäiset if, mutten varma kuinka poistaa tekemättä monimutkaisempaa.
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoOld = new int[ljono.length];
                taulukkoOld = ljono;
                kopioiTaulukko(ljono, taulukkoOld);
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, ljono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int apu;
	//Sisäkkäiset for, mutta tässä tapauksessa funktionaalista.
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = 0;
                for (int j = i; j < alkioidenLkm - 1; j++) {
                    apu = ljono[j];
                    ljono[j] = ljono[j + 1];
                    ljono[j + 1] = apu;
                }
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   
    //Näiden kolmen metodin ensimmäisistä kolmesta rivistä yritin uutta metodia,
    //mutta jostain syystä näistä tuli aina compile-error.
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdistelma = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdistelma.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdistelma.lisaa(bTaulu[i]);
        }
        return yhdistelma;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikattu = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikattu.lisaa(bTaulu[j]);
                }
            }
        }
        return leikattu;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
    //Yritin luoda aputaulukot, joita kutsuttaisiin kolmessa ylemmässä metodissa, muttei toimi.
    private void apuTaulutus( IntJoukko x, IntJoukko y) {
	apuTaulu1 = x.toIntArray();
	apuTaulu2 = y.toIntArray();
    }
        
}
