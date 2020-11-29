package it.aspix.librojava.file;

import java.io.BufferedReader;
import java.io.FileReader;

public class EsempioLeggiRighe {
    public static void main(String[] args) throws Exception{
        FileReader flussoCaratteri = new FileReader("/Volumes/ramdisk/k.txt");
        BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
        String rigaLetta;
        /*do {
            rigaLetta = lettoreDiRighe.readLine();
            if(rigaLetta!=null) {
                System.out.println(rigaLetta);
            }
        }while(rigaLetta!=null);*/
        while( (rigaLetta = lettoreDiRighe.readLine())!=null ) {
            System.out.println(rigaLetta);
        }
        lettoreDiRighe.close();
        flussoCaratteri.close();
    }
}
