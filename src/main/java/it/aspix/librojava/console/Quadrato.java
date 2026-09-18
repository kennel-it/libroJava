package it.aspix.librojava.console;

import java.util.Scanner;

public class Quadrato {   
    public static void main(String[] args){
        Scanner tastiera = new Scanner(System.in);
        int lato;
        System.out.print("Misura del lato? ");
        lato = tastiera.nextInt();
        
        int area = lato * lato;
        System.out.println("L'are del quadrato è "+area);
        tastiera.close();
    }
}