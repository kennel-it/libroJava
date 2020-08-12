package it.aspix.librojava.console;

import java.util.Scanner;

public class SalutaConsole {   
    public static void main(String[] args){
        Scanner tastiera = new Scanner(System.in);
        String utente;
        System.out.print("Come ti chiami? ");
        utente = tastiera.nextLine();
        System.out.println("Signor " + utente + " Le auguro buona giornata!");
        tastiera.close();
    }
}