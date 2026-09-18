package it.aspix.librojava.oggetti;

public class Autoveicolo {
    String targa;
    String modello;
    double kmAlLitro;
    
    public Autoveicolo(String t, String m, double l){
        targa = t;
        modello = m;
        kmAlLitro = l;
    }
    
    public double costoPercorso(double numeroKilometri) {
        return numeroKilometri/kmAlLitro*1.5;
    }
}