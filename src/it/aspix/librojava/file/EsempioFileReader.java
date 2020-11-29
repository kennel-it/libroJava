package it.aspix.librojava.file;

import java.io.FileReader;

public class EsempioFileReader {
    public static void main(String[] args) throws Exception{
        FileReader flussoCaratteri = new FileReader("/Volumes/ramdisk/k.txt");
        char caratteri[] = new char[1000];
        int caratteriLetti = flussoCaratteri.read(caratteri);
        String testo = new String(caratteri,0,caratteriLetti);
        System.out.println(testo);
        flussoCaratteri.close();
    }
}
