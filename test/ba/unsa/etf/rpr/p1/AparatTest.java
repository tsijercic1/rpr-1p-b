package ba.unsa.etf.rpr.p1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AparatTest {
    Apoteka apoteka;

    Aparat analogni;
    Aparat digitalni;

    @BeforeEach
    void setUp() {
        apoteka = new Apoteka();

        analogni = apoteka.dodajAnalogniAparat("analogni", 521320);
        digitalni = apoteka.dodajDigitalniAparat("digitalni", 125111);

        apoteka.dodajSastojak("analogni", "s1", 10);
        apoteka.dodajSastojak("analogni", "s5", 50);

        apoteka.dodajSastojak("digitalni", "s1", 10);
        apoteka.dodajSastojak("digitalni", "s3", 30);
        apoteka.dodajSastojak("digitalni", "s5", 50);
    }

    void upaliSve() {
        assertDoesNotThrow(() -> {
            analogni.upali();
            digitalni.upali();
        });
    }

    @Test
    void testValidacijaSifreAlfabet() {
        assertThrows(IllegalArgumentException.class, () -> apoteka.dodajDigitalniAparat("12345", 123456));
    }

    @Test
    void testValidacijaSifreDuzina() {
        assertThrows(IllegalArgumentException.class, () -> apoteka.dodajDigitalniAparat("a", 123456));
    }


    @Test
    void testValidacijaSifreSerijskiDonjaGranica() {
        assertThrows(IllegalArgumentException.class, () -> apoteka.dodajDigitalniAparat("aaaa", 99999));
    }

    @Test
    void testValidacijaSifreSerijskiGornjaGranica() {
        assertThrows(IllegalArgumentException.class, () -> apoteka.dodajDigitalniAparat("baba", 1000000));
    }

    @Test
    void upali() {
        assertDoesNotThrow(analogni::upali);
    }

    @Test
    void upaliBacaIzuzetak() {
        assertDoesNotThrow(analogni::upali);
        assertThrows(WrongMachineState.class, analogni::upali);
    }

    @Test
    void ugasi() {
        assertDoesNotThrow(() -> {
            analogni.upali();
            analogni.ugasi();
        });
    }

    @Test
    void ugasiBacaIzuzetak2() {
        assertThrows(WrongMachineState.class, analogni::ugasi);
    }

    @Test
    void ugasiBacaIzuzetak() {
        assertDoesNotThrow(() -> {
            analogni.upali();
            analogni.ugasi();
        });

        assertThrows(WrongMachineState.class, analogni::ugasi);
    }

    @Test
    void resetuj() {
        assertDoesNotThrow(analogni::upali);
        assertDoesNotThrow(analogni::fabrickiReset);
        assertDoesNotThrow(analogni::ugasi);
    }


    @Test
    void resetuj2() {
        assertThrows(WrongMachineState.class, analogni::fabrickiReset);
    }


    @Test
    void cijena() {
        upaliSve();

        Map<Aparat, Integer> map = apoteka.cijenaZaSastojak("s1");

        assertEquals(2, map.size());

        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertNotNull(entry.getKey());

            assertEquals(Integer.valueOf(10), entry.getValue());
        }));
    }

    @Test
    void doziraj() {
        upaliSve();

        assertEquals(100, digitalni.preostaloKredita());

        digitalni.doziraj("s1");

        assertEquals(90, digitalni.preostaloKredita());

        digitalni.doziraj("s5");

        assertEquals(40, digitalni.preostaloKredita());
    }

    @Test
    void aparatEquals() {
        assertNotEquals(analogni, digitalni);
    }

    @Test
    void aparatEqualsAlias() {
        assertEquals(analogni, analogni);
    }

    @Test
    void aparatEquals2() {
        Aparat other = apoteka.dodajDigitalniAparat("digitalni", 125111);
        assertEquals(other, digitalni);
    }

    @Test
    void doziraj2() {
        upaliSve();

        assertEquals(100, digitalni.preostaloKredita());

        digitalni.doziraj("s5");

        assertEquals(50, digitalni.preostaloKredita());

        digitalni.doziraj("s1");

        assertEquals(40, digitalni.preostaloKredita());

        assertThrows(IllegalArgumentException.class, () -> digitalni.doziraj("s5"));
    }



    @Test
    void preostaloKredita() {
        upaliSve();

        assertEquals(100, digitalni.preostaloKredita());
    }

    @Test
    void dodajSastojak() {
        upaliSve();

        assertDoesNotThrow(() -> {
            apoteka.dodajSastojak("digitalni", "mt", 20);
        });
    }


    @Test
    void dodajSastojak2() {
        upaliSve();
        assertDoesNotThrow(analogni::ugasi);

        apoteka.dodajSastojak("digitalni", "mt", 40);
        apoteka.dodajSastojak("digitalni", "mt", 20);

        Map<Aparat, Integer> map = apoteka.cijenaZaSastojak("mt");

        assertEquals(1, map.size());

        Integer cijena = map.values().iterator().next();

        assertEquals(cijena, Integer.valueOf(40));
    }


    @Test
    void dodajSastojak3() {
        upaliSve();

        assertThrows(IllegalArgumentException.class, () -> {
            apoteka.dodajSastojak("digitalni", "mt", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            apoteka.dodajSastojak("digitalni", "mt", 110);
        });
    }

    @Test
    void dajPodrzaneSastojke() {
        upaliSve();

        Set<String> set = digitalni.dajPodrzaneSastojke();

        assertEquals(3, set.size());
    }


    @Test
    void dajPodrzaneSastojke2() {
        upaliSve();

        Set<String> set = digitalni.dajPodrzaneSastojke();

        assertEquals(3, set.size());

        digitalni.doziraj("s5");
        digitalni.doziraj("s1");

        set = digitalni.dajPodrzaneSastojke();

        assertEquals(2, set.size());
    }

    @Test
    void dajMogucnostProizvodnje() {
        upaliSve();

        Map<String, Integer> map = digitalni.dajMogucnostDoziranja();

        assertEquals(3, map.size());

        assertAll(map.entrySet().stream().map(entry -> () -> {
            switch (entry.getKey()) {
                case "s1":
                    assertEquals(Integer.valueOf(10), entry.getValue());
                    break;
                case "s3":
                    assertEquals(Integer.valueOf(3), entry.getValue());
                    break;
                case "s5":
                    assertEquals(Integer.valueOf(2), entry.getValue());
                    break;
                default:
                    fail();
                    break;
            }
        }));
    }
}