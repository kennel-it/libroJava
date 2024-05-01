package it.aspix.librojava.oggetti.persone;

public class Studente extends Persona {
    public String classe;
    public boolean religione; // false in caso si avvalga dell'attività alternativa

    public Studente() {
       super();  // Per la spiegazione di super() guarda il paragrafo che segue
       classe = "<non assegnata>";
       religione = true;
    }

    public Studente(String nome, String cognome, int annoNascita, String classe, boolean religione) {
       super(nome, cognome, annoNascita, "");
       this.classe = classe;
       this.religione = religione;
    }

    @Override
    public String miPresento() {
       return "Sono " + nome + " " + cognome + ", frequento la classe "+classe;
    }

    public String getClasse() {
        return classe;
    }

 }
