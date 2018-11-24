package ba.unsa.etf.rpr.p1;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.logging.*;


public class Apoteka {
    Set<Aparat> aparati=new TreeSet<>();


    String getNazv(){
        return null;
    }

    public Aparat dodajDigitalniAparat(String digitalni, int i) {
        DigitalniAparat x=new DigitalniAparat(digitalni,i);
        aparati.add(x);
        return x;
    }

    public Map<Aparat, Integer> cijenaZaSastojak(String s1) {
        return null;
    }

  //  public Set<Aparat> dajAparate(Filter filter) {
    //    return null;
    //}
    public Set<Aparat> dajAparate(Function filter) {
        return null;
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

    public Aparat dodajAnalogniAparat(String analogni, int i) {
        AnalogniAparat x=new AnalogniAparat(analogni,i);
        aparati.add(x);
        return x;
    }
}
