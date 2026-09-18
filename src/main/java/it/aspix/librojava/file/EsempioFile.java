package it.aspix.librojava.file;

import java.io.File;

public class EsempioFile {

    public static void main(String[] args) {
        File mioFile = new File("c:/Users/pluto/esempio.txt");
        System.out.println(mioFile.exists());
    }

}
