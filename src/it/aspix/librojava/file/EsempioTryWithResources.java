package it.aspix.librojava.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EsempioTryWithResources {
    public static void main(String[] args) {
        try(
            FileReader flussoCaratteri = new FileReader("/Volumes/ramdisk/k.txt");
            BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
        ){
            String testo = lettoreDiRighe.readLine();
            System.out.println(testo);
        } catch (FileNotFoundException e) {
            System.out.println("Non trovo il file");
        } catch (IOException e) {
            System.out.println("Problemi durante la lettura");
        }
    }
}
