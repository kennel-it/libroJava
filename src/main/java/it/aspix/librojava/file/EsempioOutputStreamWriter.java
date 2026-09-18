package it.aspix.librojava.file;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class EsempioOutputStreamWriter {
    public static void main(String[] args) throws Exception{
        FileOutputStream flussoBytes = new FileOutputStream("/Volumes/ramdisk/k.txt");
        OutputStreamWriter flussoCaratteri = new OutputStreamWriter(flussoBytes, "ISO8859-1");
        flussoCaratteri.write("è");
        flussoCaratteri.close();
        flussoBytes.close();
    }
}
