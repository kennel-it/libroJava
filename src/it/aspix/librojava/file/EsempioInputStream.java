package it.aspix.librojava.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EsempioInputStream {
    public static void main(String[] args) throws IOException {
        File mioFile = new File("/Volumes/ramdisk/o.txt");
        FileOutputStream flussoBytes = new FileOutputStream(mioFile);
        flussoBytes.write(45);
        flussoBytes.close();
    }
}
