package it.aspix.librojava.esercizi.salutautente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * legge e scrive nello stesso pulsante
 */
public class Aumenta extends Application {

    Button pCancelletti = new Button();

    public void start(Stage finestra) {
        BorderPane principale = new BorderPane();
        principale.setCenter(pCancelletti);

        Scene scena = new Scene(principale,300,200);

        finestra.setTitle("cancelletti");
        finestra.setScene(scena);
        finestra.show();
        
        pCancelletti.setOnAction( e -> azioneCancelletti());
    }

    private void azioneCancelletti() {
        String testo = pCancelletti.getText();
        pCancelletti.setText(testo+"#");
    }

    public static void main(String[] args) {
        launch(args);
    }
}