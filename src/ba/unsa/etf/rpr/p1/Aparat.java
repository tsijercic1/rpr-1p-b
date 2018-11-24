package ba.unsa.etf.rpr.p1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class Aparat {
    int serijski;
    String sifra;
    short krediti;
    Map<String, Integer> sastojci=new HashMap<>();

    public Aparat(String sifra, int serijski){
        this.sifra=sifra;
        this.serijski=serijski;
        krediti=0;
    }


    public void ugasi() {
        if(krediti==0)throw new WrongDeviceState("Već ugašen!");
    }

    public void proizvedi(String s5) {
    }

    public void doziraj(String sastojak) {

    }

    public void upali() {
        if(krediti==100)throw new WrongDeviceState("Već upaljen!");
        krediti=100;
    }

    public int getSerijski() {
        return serijski;
    }

    public void setSerijski(int serijski) {
        this.serijski = serijski;
    }

    public void fabrickiReset() {
        this.ugasi();
        this.upali();
    }

    public int preostaloKredita() {
        return 0;
    }

    public Set<String> dajPodrzaneSastojke() {
        Set<String> podrzaniSastojci = new TreeSet<>();
        for(Map.Entry<String,Integer> element: sastojci.entrySet()){
            podrzaniSastojci.add(element.getKey());
        }
        return podrzaniSastojci;
    }

    public Map<String, Integer> dajMogucnostDoziranja() {
        return null;
    }
    void registrujSastojak(String naziv, int cijena){
        sastojci.putIfAbsent(naziv,cijena);
    }

    @Override
    public String toString() {
        String display;
        if(krediti==0){
            display= "Aparat " + sifra + " je ugasen";
        }
        else{
            display=  "Aparat " + sifra + " je upaljen (preostalo "+krediti+" kredita)";
        }

      return display;
    }

    public int compareTo(Aparat a){
        return  this.sifra.compareTo(a.sifra);
    }
}
