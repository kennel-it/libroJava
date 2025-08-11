package it.aspix.librojava;

public class ProvaOverride {
    static class Animale {
        public String verso() {
            return "Verso generico";
        }
    }

    static class Cane extends Animale {
        @Override
        public String verso() {
            return "Bau!";
        }
    }

    public static void main(String[] args) {
        Animale animale = new Animale();
        Cane cane1 = new Cane();
        Animale cane2 = new Cane();

        System.out.println(animale.verso());  // Output: Verso generico
        System.out.println(cane1.verso());    // Output: Bau!
        System.out.println(cane2.verso());    // Output: Bau!
    }
}
