package ba.unsa.etf.rpr.p1;


import java.util.*;
import java.util.function.Function;

public class Apoteka {

    ArrayList<Aparat> aparati;
    public Apoteka() {

        this.aparati = new ArrayList<>();
    }

    //analogni aparat
    public Aparat dodajKupljenuMasinu(String x, int y) {
        if(x.length()<4 || x.matches("[^a-zA-Z]+") || y<100000 || y>999999){
            throw new IllegalArgumentException();
        }
        Aparat analogniAparat = new AnalogniAparat();
        analogniAparat.setSerijski(y);
        analogniAparat.setSifra(x);
        if(!aparati.contains(analogniAparat)){
            aparati.add(analogniAparat);
        }
        aparati.get(aparati.indexOf(analogniAparat)).setSerijski(y);

        return analogniAparat;
    }

    //digitalni aparat
    public Aparat dodajDomacuMasinu(String x, int y) {
        if(x.length()<4 || x.matches("[^a-zA-Z]+") || y <100000 || y >999999){
            throw new IllegalArgumentException();
        }
        Aparat digitalniAparat = new DigitalniAparat();
        digitalniAparat.setSerijski(y);
        digitalniAparat.setSifra(x);
        if(!aparati.contains(digitalniAparat)){
            aparati.add(digitalniAparat);
        }
        aparati.get(aparati.indexOf(digitalniAparat)).setSerijski(y);

        return digitalniAparat;
    }

    public void dodajMaterijal(String s, String n, int c) {
        dodajSastojak(s,n,c);
    }

    public Map<Aparat, String> najviseSastojaka() {
        Map<Aparat, String> result = new HashMap<>();
        ArrayList<Map<String, Integer>> mape = new ArrayList<>();
        for(int i=0;i<aparati.size();i++){
            mape.add(aparati.get(i).dajMogucnostDoziranja());
            ArrayList<Map.Entry<String, Integer>> x = new ArrayList<>(mape.get(i).entrySet());
            x.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if(o1.getValue().equals(o2.getValue())){
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            if(x.size()!=0){
                if(aparati.get(i).getUpaljen())result.put(aparati.get(i),x.get(0).getKey());
            }

        }

        return result;

    }

    public void dodajSastojak(String s, String n, int c) {
        if(c >80 || c<1)throw new IllegalArgumentException();
        boolean naso = false;
        for(int i=0;i<aparati.size();i++){
            if(s.equals(aparati.get(i).getSifra())){
                naso = true;
                aparati.get(i).registrujSastojak(n,c);
            }
        }
        if(!naso){
            throw new IllegalArgumentException();
        }
    }

    public Aparat dodajDigitalniAparat(String x, int serijski) {
        return dodajDomacuMasinu(x,serijski);
    }

    public Set<Aparat> dajAparate(Function<Aparat,Boolean> function) {
        Set<Aparat> set = new TreeSet<>();
        Boolean jest ;
        for(int i=0;i<aparati.size();i++){
            jest = true;
            if(function!=null){
                jest = function.apply(aparati.get(i));
            }
            if(jest){
                set.add(aparati.get(i));
            }
        }
        return set;
    }

    public Map<Aparat, Integer> cijenaZaSastojak(String s1) {
        Map<Aparat,Integer> mapa = new HashMap<>();
        for(int i=0;i<aparati.size();i++){
            if(aparati.get(i).getUpaljen()){
                if(aparati.get(i).dajMogucnostDoziranja().containsKey(s1)){
                    mapa.put(aparati.get(i),aparati.get(i).getSastojci().get(s1));
                }else {
                    mapa.put(aparati.get(i),-1);
                }
            }
        }

        return mapa;
    }

    public Aparat dodajAnalogniAparat(String x, int serijski) {
        return dodajKupljenuMasinu(x,serijski);
    }

    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<aparati.size();i++){
            res+=i+1+". "+aparati.get(i)+"\n";
        }
        return res;
    }
}
