package it.aspix.librojava.console;

import java.util.Scanner;
public class Doppio {   
    public static void main(String[] args){
        Scanner tastiera = new Scanner(System.in);
        int numero,doppio;

        System.out.print("Scrivi un numero: ");
        numero = tastiera.nextInt();
        doppio = numero * 2;
        System.out.println("il doppio di "+numero+" è "+doppio);
        tastiera.close();
    }
}