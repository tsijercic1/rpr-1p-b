package ba.unsa.etf.rpr.p1;

import java.util.Map;
import java.util.Set;

public abstract class Aparat implements Comparable {
    Boolean upaljen=false;
    int serijski;
    int krediti;
    String naziv;

    public Aparat(String naziv, int serijski) {
        if(naziv.length()<4  || !naziv.matches("[a-z|A-Z]+")|| serijski<100000 || serijski > 999999){
            System.out.println(naziv);
            throw new IllegalArgumentException();
        }
        this.serijski = serijski;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getSerijski() {
        return serijski;
    }

    public void upali() throws WrongMachineState {
        if(upaljen){
            throw new WrongMachineState();
        }
        krediti=100;
        upaljen=true;
    }

    public void ugasi() throws WrongMachineState {
        if(!upaljen){
            throw new WrongMachineState();
        }
        krediti = 0;
    }

    public void fabrickiReset() throws WrongMachineState {
        if(!upaljen){
            throw new WrongMachineState();
        }
        krediti=100;
    }

    public void proizvedi(String s5) {

    }

    public void doziraj(String s1) {

    }

    public int preostaloKredita() {
        return krediti;
    }

    public Set<String> dajPodrzaneSastojke() {
        return null;
    }

    public Map<String, Integer> dajMogucnostDoziranja() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
