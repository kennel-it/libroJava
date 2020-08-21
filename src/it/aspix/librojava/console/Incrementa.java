package it.aspix.librojava.console;

public class Incrementa {
    static int incrementa(int x){ 
        x++;
        return x;
    }
    public static void main(String[] args) {
        int a = 10;
        int b = incrementa(a);
        System.out.println(a+", "+b);
    }
}
