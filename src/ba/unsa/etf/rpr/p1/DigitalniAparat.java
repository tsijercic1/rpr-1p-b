package ba.unsa.etf.rpr.p1;

import java.util.Map;
import java.util.Set;

public class DigitalniAparat extends Aparat {

    public DigitalniAparat(String sifra, int serijski) {
        super(sifra,serijski);
    }

    @Override
    public int compareTo(Object o) {
        return super.compareTo(o);
    }

    @Override
    public String getNaziv() {
        return super.getNaziv();
    }

    @Override
    public int getSerijski() {
        return super.getSerijski();
    }

    @Override
    public void upali() throws WrongMachineState {
        super.upali();
    }

    @Override
    public void ugasi() throws WrongMachineState {
        super.ugasi();
    }

    @Override
    public void fabrickiReset() throws WrongMachineState {
        super.fabrickiReset();
    }

    @Override
    public void proizvedi(String s5) {
        super.proizvedi(s5);
    }

    @Override
    public void doziraj(String s1) {
        super.doziraj(s1);
    }

    @Override
    public int preostaloKredita() {
        return super.preostaloKredita();
    }

    @Override
    public Set<String> dajPodrzaneSastojke() {
        return super.dajPodrzaneSastojke();
    }

    @Override
    public Map<String, Integer> dajMogucnostDoziranja() {
        return super.dajMogucnostDoziranja();
    }
}
