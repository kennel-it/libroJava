package it.aspix.librojava.oggetti.persone;

public class Persona {
    public String nome;
    public String cognome;
    public int annoNascita;
    public String telefono;

    public Persona() {
       nome = "<indefinito>";
       cognome = "<indefinito>";
       annoNascita = -1;
       telefono = "<indefinito>";
    }

    public Persona(String nome, String cognome, int annoNascita, String telefono) {
       this.nome = nome;
       this.cognome = cognome;
       this.annoNascita = annoNascita;
       this.telefono = telefono;
    }

    public String miPresento() {
        return "Sono " + nome + " " + cognome + ", nato nel " + annoNascita + " [tel: " + telefono + "]";
     }

 }