package it.aspix.librojava;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CommaSeparatedValues extends Application{
    TextField cIngressi = new TextField();
    TextField cUscite = new TextField();
    Label eErrori = new Label("-");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label eIngressi = new Label("ingressi:");
        Label eUscite = new Label("uscite:");
        GridPane pannello = new GridPane();
        Button contabilizza = new Button("contabilizza");
        
        pannello.add(eIngressi, 0, 0);
        pannello.add(cIngressi, 1, 0);
        pannello.add(eUscite, 0, 1);
        pannello.add(cUscite, 1, 1);
        pannello.add(contabilizza, 0, 3);
        pannello.add(eErrori, 1, 3);
        
        Scene scena = new Scene(pannello);
        primaryStage.setScene(scena);
        primaryStage.setTitle("contabilità");
        primaryStage.show();
        
        contabilizza.setOnAction(e->calcolaSomme());
    }
    
    public static void main(String args[]){
        launch();
    }
    
    public void calcolaSomme() {
        String riga;
        String parti[];
        int ingresso = 0;
        int uscita = 0;
        try(
            FileInputStream fis = new FileInputStream("/Users/edoardo/Desktop/spese.txt");
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader input = new BufferedReader(isr);
        ){
            while( (riga=input.readLine()) != null ) {
                parti = riga.split(",");
                ingresso += Integer.parseInt(parti[2]);
                uscita += Integer.parseInt(parti[3]);
            }
            cIngressi.setText(""+ingresso);
            cUscite.setText(""+uscita);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
