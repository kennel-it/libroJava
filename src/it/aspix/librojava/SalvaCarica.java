package it.aspix.librojava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SalvaCarica extends Application{
    TextField cNome1 = new TextField("");
    TextField cNome2 = new TextField("");
    TextField cTempo1 = new TextField("");
    TextField cTempo2 = new TextField("");
    TextField cTotale = new TextField("");
    Label eErrore = new Label();
    
    @Override
    public void start(Stage primaryStage){
        Label eNome = new Label("nome");
        Label eTempo = new Label("tempo");
        Label e1 = new Label("frazionista 1:");
        Label e2 = new Label("frazionista 2:");
        Button pCalcola = new Button("calcola");
        Button pSalva = new Button("salva");
        Button pCarica = new Button("carica");
        GridPane griglia = new GridPane();
        
        griglia.add(eNome,   1, 0);
        griglia.add(eTempo,  2, 0);
        griglia.add(e1,      0, 1);
        griglia.add(cNome1,  1, 1);
        griglia.add(cTempo1, 2, 1);
        griglia.add(e2,      0, 2);
        griglia.add(cNome2,  1, 2);
        griglia.add(cTempo2, 2, 2);
        griglia.add(pCalcola, 1, 5);
        griglia.add(cTotale, 2, 5);
        griglia.add(pSalva, 1, 6);
        griglia.add(pCarica, 1, 7);
        griglia.add(eErrore, 0, 8, 3, 1);
        
        Scene s = new Scene(griglia);
        primaryStage.setScene(s);
        primaryStage.setTitle("reticolato");
        primaryStage.show();
        
        pSalva.setOnAction(e->salva());
        pCarica.setOnAction(e->carica());
    }
    
    private void salva() {
        String nome;
        double tempo;
        try {
            FileOutputStream uscitaFile = new FileOutputStream("/Volumes/ramdisk/o.file");
            ObjectOutputStream oos = new ObjectOutputStream(uscitaFile);
            nome = cNome1.getText();
            tempo = Double.parseDouble(cTempo1.getText());
            oos.writeObject(nome);
            oos.writeDouble(tempo);
            oos.close();
            uscitaFile.close();
        } catch (FileNotFoundException e) {
            eErrore.setText("Non trovo il file ("+e.getMessage()+")");
        } catch (IOException e) {
            eErrore.setText("Problemi di input output ("+e.getMessage()+")");
        }
    }
    
    private void carica() {
        String nome;
        double tempo;
        try { 
            FileInputStream ingressoFile = new FileInputStream("/Volumes/ramdisk/o.file");
            ObjectInputStream ingresso = new ObjectInputStream(ingressoFile);
            nome = (String) ingresso.readObject();
            tempo = ingresso.readDouble();
            cNome1.setText(nome);
            cTempo1.setText(""+tempo);
            ingresso.close();
            ingressoFile.close();
        } catch (FileNotFoundException e) {
            eErrore.setText("Non trovo il file ("+e.getMessage()+")");
        } catch (IOException e) {
            eErrore.setText("Problemi di input output ("+e.getMessage()+")");
        } catch (ClassNotFoundException e) {
            eErrore.setText("Classe non trovata ("+e.getMessage()+")");
        }
    }

    public static void main(String x[]){ launch(x); }
}
