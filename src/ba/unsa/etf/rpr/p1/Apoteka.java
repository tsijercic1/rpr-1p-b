package ba.unsa.etf.rpr.p1;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Apoteka {
    ArrayList<Aparat> aparati=new ArrayList<>();

    public Aparat dodajDigitalniAparat(String sifra, int serijski) {
        Aparat result = new DigitalniAparat(sifra,serijski);
        aparati.add(result);
        return result;
    }

    public Aparat dodajAnalogniAparat(String sifra, int serijski) {
        Aparat result = new DigitalniAparat(sifra,serijski);
        aparati.add(result);
        return result;

    }

    public Aparat dodajKupljenuMasinu(String analogni, int i) {
        return null;
    }

    public Aparat dodajDomacuMasinu(String digitalni, int i) {
        return null;
    }

    public void dodajMaterijal(String analogni, String s5, int i) {
    }

    public Map<Aparat, String> najviseSastojaka() {
        return null;
    }

    public void dodajSastojak(String digitalni, String s0, int i) {

    }

    public Set<Aparat> dajAparate(Function<Aparat,Boolean> function) {
        return null;
    }

    public Map<Aparat, Integer> cijenaZaSastojak(String s1) {
        return null;
    }
}
