package ba.unsa.etf.rpr.p1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ApotekaTest {
    Apoteka apoteka;

    Aparat analogni;
    Aparat digitalni;

    @BeforeEach
    void setUp() {
        apoteka = new Apoteka();

        analogni = apoteka.dodajKupljenuMasinu("analogni", 521320);
        digitalni = apoteka.dodajDomacuMasinu("digitalni", 125111);

        apoteka.dodajMaterijal("analogni", "s5", 50);
        apoteka.dodajMaterijal("analogni", "s1", 10);

        apoteka.dodajMaterijal("digitalni", "s1", 10);
        apoteka.dodajMaterijal("digitalni", "s5", 50);
        apoteka.dodajMaterijal("digitalni", "s3", 30);

        assertDoesNotThrow(analogni::upali);
        assertDoesNotThrow(digitalni::upali);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void najviseSastojaka() {
        Map<Aparat, String> map = apoteka.najviseSastojaka();

        assertEquals(2, map.size());

        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertEquals("s1", entry.getValue());
        }));
    }

    @org.junit.jupiter.api.Test
    void najviseSastojakaa2() {
        assertDoesNotThrow(digitalni::ugasi);

        Map<Aparat, String> map = apoteka.najviseSastojaka();

        assertEquals(1, map.size());

        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertEquals("s1", entry.getValue());
        }));
    }


    @org.junit.jupiter.api.Test
    void najviseSastojaka3() {
        apoteka.dodajSastojak("digitalni", "s0", 10);
        apoteka.dodajSastojak("analogni", "s0", 10);

        Map<Aparat, String> map = apoteka.najviseSastojaka();

        assertEquals(2, map.size());

        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertEquals("m0", entry.getValue());
        }));
    }


    @org.junit.jupiter.api.Test
    void dodajDigitalniAparat() {
        Aparat t = apoteka.dodajDigitalniAparat("digitalni", 111111);
        assertEquals(111111, t.getSerijski());
    }


    @org.junit.jupiter.api.Test
    void dodajDigitalniAparat2() {
        // metode ne treba da pali masinu

        Aparat t = apoteka.dodajDigitalniAparat("digitalni", 111111);
        assertEquals(111111, t.getSerijski());
        assertDoesNotThrow(t::upali);
    }

    @Test
    void dajAparate() {
        Set<Aparat> aparati = apoteka.dajAparate(null);

        assertEquals(2, aparati.size());


        Iterator<Aparat> iterator = aparati.iterator();
        Aparat prva = iterator.next();
        Aparat druga = iterator.next();

        assertSame(prva, analogni);
        assertSame(druga, digitalni);
    }


    @Test
    void dajAparate2() {
        // potrosi sve sate
        for ( int i = 0; i < 10; ++ i ) {
            analogni.doziraj("s1");
        }

        Set<Aparat> aparati = apoteka.dajAparate(null);

        assertEquals(2, aparati.size());

        Iterator<Aparat> iterator = aparati.iterator();
        Aparat prva = iterator.next();
        Aparat druga = iterator.next();

        assertSame(prva, analogni);
        assertSame(druga, digitalni);
    }


    @Test
    void dajAparate3() {
        Set<Aparat> aparati = apoteka.dajAparate(m -> false);

        assertEquals(0, aparati.size());
    }

    @Test
    void dajAparate4() {
        Set<Aparat> aparati = apoteka.dajAparate(m -> true);

        assertEquals(2, aparati.size());
    }


    @Test
    void dajAparate5() {

        Set<Aparat> aparati = apoteka.dajAparate( m ->  m.getNaziv().equals("digitalni"));

       assertEquals(1, aparati.size());
    }


    @org.junit.jupiter.api.Test
    void ispisi() {
        String s = "" + apoteka;

        String expected =
                "1. Aparat digitalni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n" +
                "2. Aparat analogni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n";

        String expectedAlt =
                "1. Aparat analogni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n" +
                "2. Aparat digitalni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n";


        if ( !expected.equals(s) && !expectedAlt.equals(s) ) {
            fail();
        }
    }


    @org.junit.jupiter.api.Test
    void ispisi2() {
        assertDoesNotThrow(digitalni::ugasi);

        String s = "" + apoteka;

        String expected =
                "1. Aparat digitalni je ugasen. On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n" +
                        "2. Aparat analogni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n";

        String expectedAlt =
                "1. Aparat analogni je upaljen (preostalo 100 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n" +
                "2. Aparat digitalni je ugasen. On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n";


        if ( !expected.equals(s) && !expectedAlt.equals(s) ) {
            fail();
        }
    }

    @Test
    void testIzbacivanjePrethodnogAparata() {
        Aparat noviDigitalni = apoteka.dodajDigitalniAparat("digitalni", 125111);

        assertNotSame(digitalni, noviDigitalni);
    }


    @org.junit.jupiter.api.Test
    void ispisi3() {
        assertDoesNotThrow(digitalni::ugasi);
        assertDoesNotThrow(() -> analogni.proizvedi("s5"));

        String s = "" + apoteka;

        String expected =
                "1. Aparat digitalni je ugasen. On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n" +
                        "2. Aparat analogni je upaljen (preostalo 50 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n";

        String expectedAlt =
                "1. Aparat analogni je upaljen (preostalo 50 kredita). On moze dozirati sastojke s1 (10), s5 (50).\n" +
                "2. Aparat digitalni je ugasen. On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n";


        if ( !expected.equals(s) && !expectedAlt.equals(s) ) {
            fail();
        }
    }


    @org.junit.jupiter.api.Test
    void cijenaZaSastojak() {
        Map<Aparat, Integer> map = apoteka.cijenaZaSastojak("s1");

        assertEquals(2, map.size());
        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertEquals(Integer.valueOf(10), entry.getValue());
        }));
    }


    @org.junit.jupiter.api.Test
    void cijenaZaSastojak2() {
        Map<Aparat, Integer> map = apoteka.cijenaZaSastojak("s3");

        assertEquals(2, map.size());
        assertAll(map.entrySet().stream().map(entry -> () -> {
            if ( entry.getKey() == digitalni ) {
                assertEquals(Integer.valueOf(30), entry.getValue());
            } else {
                assertEquals(Integer.valueOf(-1), entry.getValue());
            }
        }));
    }


    @Test
    void optimalniAparatZaSastojak() {
        assertDoesNotThrow(digitalni::ugasi);

        Map<Aparat, Integer> map = apoteka.cijenaZaSastojak("s5");

        assertEquals(1, map.size());
        assertAll(map.entrySet().stream().map(entry -> () -> {
            assertEquals(Integer.valueOf(50), entry.getValue());
        }));
    }
}