package it.aspix.librojava.file;

import java.io.BufferedReader;
import java.io.FileReader;

public class EsempioBufferedReader {
    public static void main(String[] args) throws Exception{
        FileReader flussoCaratteri = new FileReader("/Volumes/ramdisk/k.txt");
        BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
        String testo = lettoreDiRighe.readLine();
        System.out.println(testo);
        lettoreDiRighe.close();
        flussoCaratteri.close();
    }
}
