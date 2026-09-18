package it.aspix.librojava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Nuotatori extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField cNumeroTempi = new TextField();
        TextField cTempo = new TextField();
        Button pCreaVettore = new Button("inserisci numero tempi");
        int tempi[];
        Button pInserisci = new Button("inserisci tempo");
        int posizione;
        TextField cCoach = new TextField();
        Button pLenti = new Button("conta lenti");

        GridPane principale = new GridPane();
        principale.setPadding(new Insets(10, 10, 10, 10));
        principale.setHgap(10);
        principale.setVgap(10);

        principale.add(new Label("numero tempi:"), 0, 0);
        principale.add(cNumeroTempi,               1, 0);
        principale.add(pCreaVettore,               2, 0);
        principale.add(new Label("tempo:"),        0, 1);
        principale.add(cTempo,                     1, 1);
        principale.add(pInserisci,                 2, 1);

        principale.add(new Label("tempo previsto:"), 0, 2);
        principale.add(cCoach,                       1, 2);
        principale.add(pLenti,                       2, 2);

        Scene scena = new Scene(principale);
        primaryStage.setScene(scena);
        primaryStage.setTitle("Piscina");
        primaryStage.show();
    }
}
