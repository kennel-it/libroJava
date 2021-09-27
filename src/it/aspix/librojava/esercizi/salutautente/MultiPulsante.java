package it.aspix.librojava.esercizi.salutautente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * diversi pulsanti che fanno azioni diverse
 * - fare il programma funzionante
 * - avere le variabili con il nome giusto
 * - mettere i commenti nelle parti rilevanti
 * - utilizzare la visibilità più ridotta possibile
 */
public class MultiPulsante extends Application {

    TextField cNome = new TextField();
    TextField cCognome = new TextField(); 
    Label eRisposta = new Label("??");

    public void start(Stage finestra) {

        Label eNome = new Label("nome:");
        Label eCognome = new Label("cognome:");
        
        Button pSalutoInformale = new Button("saluto informale");
        Button pSalutoFormale = new Button("saluto formale");
        
        GridPane principale = new GridPane();
        principale.add(eNome, 0, 0);
        principale.add(cNome, 1, 0);
        
        principale.add(eCognome, 0, 1);
        principale.add(cCognome, 1, 1);
        
        principale.add(pSalutoFormale, 0, 2);
        principale.add(pSalutoInformale, 1, 2);
        
        principale.add(eRisposta, 1, 3);

        Scene scena = new Scene(principale);

        finestra.setTitle("diversi saluti");
        finestra.setScene(scena);
        finestra.show();
        
        pSalutoInformale.setOnAction( e -> azioneSalutoInformale());
        pSalutoFormale.setOnAction( e -> azioneSalutoFormale());
    }

    private void azioneSalutoFormale() {
        String nome = cNome.getText();
        String cognome = cCognome.getText();
        eRisposta.setText("buona sera "+nome+" "+cognome);
    }

    private void azioneSalutoInformale() {
        String nome = cNome.getText();
        eRisposta.setText("ciao "+nome);
    }

    public static void main(String[] args) {
        launch(args);
    }
}