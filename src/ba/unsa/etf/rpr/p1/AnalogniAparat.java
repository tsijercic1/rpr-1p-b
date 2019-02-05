package ba.unsa.etf.rpr.p1;

import java.util.Map;
import java.util.Set;

public class AnalogniAparat extends Aparat {
    @Override
    public Map<String, Integer> getSastojci() {
        return super.getSastojci();
    }

    @Override
    public Boolean getUpaljen() {
        return super.getUpaljen();
    }

    @Override
    public void setUpaljen(Boolean upaljen) {
        super.setUpaljen(upaljen);
    }

    public AnalogniAparat() {
        super();
    }

    @Override
    public void setSerijski(int serijski) {
        super.setSerijski(serijski);
    }

    @Override
    public String getSifra() {
        return super.getSifra();
    }

    @Override
    public void setSifra(String sifra) {
        super.setSifra(sifra);
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
    public int getSerijski() {
        return super.getSerijski();
    }

    @Override
    public int doziraj(String s1) {
        return super.doziraj(s1);
    }

    @Override
    public int proizvedi(String s5) {
        return super.proizvedi(s5);

    }

    @Override
    public String getNaziv() {
        return super.getNaziv();
    }

    @Override
    public void fabrickiReset() {
        super.fabrickiReset();
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

    @Override
    void registrujSastojak(String naziv, int cijena) {
        super.registrujSastojak(naziv, cijena);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
