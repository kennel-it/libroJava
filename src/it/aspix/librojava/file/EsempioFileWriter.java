package it.aspix.librojava.file;

import java.io.FileWriter;

public class EsempioFileWriter {
    public static void main(String[] args) throws Exception{
        FileWriter flussoCaratteri = new FileWriter("/Volumes/ramdisk/k.txt");
        flussoCaratteri.write("è");
        flussoCaratteri.close();
    }
}
