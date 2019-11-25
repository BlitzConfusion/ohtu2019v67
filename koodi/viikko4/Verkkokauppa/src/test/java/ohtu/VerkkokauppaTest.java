package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    @Before
    public void setUp() {
	pankki = mock(Pankki.class);
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(2, "piima", 25));
	when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "titanium", 100));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
                      

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	//MIKSI TÄMÄ EI TOIMI!?!
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void twoOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
                      

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	//MIKSI TÄMÄ EI TOIMI!?!
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 30);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void doublePaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        /// tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	//MIKSI TÄMÄ EI TOIMI!?!
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    @Test
    public void loppuPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	//MIKSI TÄMÄ EI TOIMI!?!
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
}


