package it.aspix.librojava.oggetti.persone;

public class Test {
    public static void main(String[] args) {
        Persona anna = new Persona("Anna", "Verdi", 2000, "+398877665544");
        System.out.println(anna.miPresento());

        Studente mario = new Studente("Mario", "Rossi", 2010, "3K", true);
        System.out.println(mario.miPresento());
        System.out.println(mario.getClasse());

        Persona anonimo = mario;
        System.out.println(anonimo.miPresento()); // usa quello di Studente
        // System.out.println(anonimo.getClasse()); questo errore
    }
}
