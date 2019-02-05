package ba.unsa.etf.rpr.p1;

import java.util.*;

public class Aparat implements Comparable {
    private int serijski;
    private Boolean upaljen;
    private String sifra;
    private final int maxKredit=100;
    private int kredit;

    private Map<String,Integer> sastojci;

    public Map<String, Integer> getSastojci() {
        return sastojci;
    }

    public Boolean getUpaljen() {
        return upaljen;
    }

    public void setUpaljen(Boolean upaljen) {
        this.upaljen = upaljen;
    }

    public Aparat(){
        sastojci = new HashMap<>();
        upaljen = false;
    }

    public void setSerijski(int serijski) {
        this.serijski = serijski;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void upali() throws WrongMachineState {
        if(upaljen){
            throw new WrongMachineState();
        }
        kredit = maxKredit;
        upaljen = true;
    }

    public void ugasi() throws WrongMachineState {
        if(!upaljen){
            throw new WrongMachineState();
        }
        upaljen=false;
    }

    public int getSerijski() {
        return serijski;
    }

    public int doziraj(String s1) {
        int x = 0;
        if(sastojci.containsKey(s1))x = sastojci.get(s1);
        else{
            throw new IllegalArgumentException();
        }
        kredit-=x;
        if(kredit < 0 ){
            kredit+=x;
            throw new IllegalArgumentException();
        }
        return x;
    }

    public int proizvedi(String s5) {
        return doziraj(s5);
    }

    public String getNaziv() {
        return sifra;
    }

    public void fabrickiReset() {
        ugasi();
        upali();

    }

    public int preostaloKredita() {
        if(!upaljen){
            return 0;
        }
        return kredit;
    }

    public Set<String> dajPodrzaneSastojke() {
        Set<String> set = dajMogucnostDoziranja().keySet();
        return set;
    }

    public Map<String, Integer> dajMogucnostDoziranja() {
        Map<String,Integer> mapa = new HashMap<>();
        for(Map.Entry<String,Integer> element : sastojci.entrySet()){
            if(kredit/element.getValue()!=0)
            mapa.put(element.getKey(),kredit/element.getValue());
        }
        return mapa;
    }

    void registrujSastojak(String naziv, int cijena){
        if(!sastojci.keySet().contains(naziv)){
            sastojci.put(naziv,cijena);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aparat)) return false;

        Aparat aparat = (Aparat) o;
       return this.sifra.equals(aparat.sifra);
    }

    @Override
    public int hashCode() {
        int result = getSerijski();
        result = 31 * result + (upaljen != null ? upaljen.hashCode() : 0);
        result = 31 * result + (getSifra() != null ? getSifra().hashCode() : 0);
        result = 31 * result + maxKredit;
        result = 31 * result + kredit;
        result = 31 * result + (sastojci != null ? sastojci.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Aparat t = (Aparat)o;
        if(this.dajMogucnostDoziranja().size()==t.dajMogucnostDoziranja().size()){
            return this.getSifra().compareTo(t.getSifra());
        }
        if(this.dajMogucnostDoziranja().size()>t.dajMogucnostDoziranja().size())return 1;

        return -1;
    }

    @Override
    public String toString() {
        String result =  "Aparat ";
        result+= this.getSifra();
        result+=" je "+ ((this.getUpaljen())?"upaljen (preostalo "+this.kredit+" kredita). ":"ugasen. ");
        //1. Aparat digitalni je ugasen. On moze dozirati sastojke s1 (10), s3 (30), s5 (50).\n"
        result += "On moze dozirati sastojke";
        int i=0;

        ArrayList<Map.Entry<String, Integer>> x = new ArrayList<>(this.getSastojci().entrySet());
        x.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getKey().compareTo(o2.getKey());

            }
        });



        for(Map.Entry<String,Integer> element: x){
            if(i==0){
                result +=" "+element.getKey()+" ("+element.getValue()+")";
            }else{
                result+= ", "+element.getKey()+" ("+element.getValue()+")";
            }
            i++;
        }
        result+=".";
        return result;
    }
}
