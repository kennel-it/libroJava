package it.aspix.librojava.oggetti.risparmiovincolato;

public class RisparmioVincolato {
    String nome;
    String cognome;
    int codice;
    float importo;
    short anno;
    float interessiPercentuale;
      
    public RisparmioVincolato(String n, String c, int cod, float euro, short a, float i) {
        nome = n;
        cognome = c;
        codice = cod;
        importo = euro;
        // Ci mettiamo anche un banale controllo sulle date: l'anno dal 1970 al 2025, 
        // il mese da 1 a 12, il giorno da 1 a 31
        if(a<1970) {
           a = 1970;
        } else if(a>2020) {
           a = 2025;
        }
        anno = a;
        interessiPercentuale = i;
     }

    public double fornisciGuadagno() {
       double guadagno = importo*interessiPercentuale/100; //formula per il calcolo degli interessi
       return guadagno;
    }

    public double fornisciCapitale() {
       double guadagno = fornisciGuadagno(); //anziché rifare il calcolo invochiamo il metodo
       double capitale = guadagno + importo;
       return capitale;
    }
 }
