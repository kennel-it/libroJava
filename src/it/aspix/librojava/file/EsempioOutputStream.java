package it.aspix.librojava.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EsempioOutputStream {
    public static void main(String[] args) throws IOException {
        File mioFile = new File("/Volumes/ramdisk/o.txt");
        FileInputStream flussoBytes = new FileInputStream(mioFile);
        int n = flussoBytes.read();
        System.out.println(n);
        flussoBytes.close();
    }
}
