package it.aspix.librojava;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScrivereTesti extends Application{
    TextField cTesto = new TextField();
    Label eErrori = new Label("-");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label eIngressi = new Label("testo:");
        GridPane pannello = new GridPane();
        Button salva = new Button("accoda al file");
        
        pannello.add(eIngressi, 0, 0);
        pannello.add(cTesto, 1, 0);
        pannello.add(salva, 0, 1);
        pannello.add(eErrori, 1, 1);
        
        Scene scena = new Scene(pannello);
        primaryStage.setScene(scena);
        primaryStage.setTitle("contabilità");
        primaryStage.show();
        
        salva.setOnAction(e->calcolaSomme());
    }
    
    public static void main(String args[]){
        launch();
    }
    
    public void calcolaSomme() {
        String riga;
        try(
            FileOutputStream fos = new FileOutputStream("/Users/edoardo/Desktop/testi.txt",true);
            OutputStreamWriter uscita = new OutputStreamWriter(fos,"UTF-8");
        ){
            riga = cTesto.getText();
            uscita.write(riga);
            uscita.write("\n");
        } catch (Exception e) {
            eErrori.setText(e.getMessage());
        }
    }
}
