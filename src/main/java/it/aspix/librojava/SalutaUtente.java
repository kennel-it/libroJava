package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SalutaUtente extends Application {

    Button pSaluto = new Button(); 
    Label messaggio = new Label();
    TextField nome = new TextField();

    @Override
    public void start(Stage finestra) {
        Label mettiNome = new Label();
        Button eseguiSaluto = new Button();
        GridPane griglia = new GridPane();
        
        mettiNome.setText("Nome:");
        messaggio.setText("Sono impaziente...");
        eseguiSaluto.setText("Premi qui");
        
        griglia.add(mettiNome, 0, 0);
        griglia.add(nome, 1, 0);
        griglia.add(eseguiSaluto, 1, 1);
        griglia.add(messaggio, 1, 2);
        
        pSaluto.setText("Saluta!");
        eseguiSaluto.setOnAction(e -> esegui());

        BorderPane principale = new BorderPane();
        principale.setCenter(pSaluto);

        Scene scena = new Scene(griglia, 300, 250);

        finestra.setTitle("Hello World!");
        finestra.setScene(scena);
        finestra.show();
    }

    private void esegui(){
        pSaluto.setText("Ciao Mondo!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}