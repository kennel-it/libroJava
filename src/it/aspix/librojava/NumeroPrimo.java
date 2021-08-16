package it.aspix.librojava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NumeroPrimo extends Application{
    TextField tNumero = new TextField();
    Label eRisultato = new Label("se serve qui comparirà la risposta");


    @Override
    public void start(Stage finestra) throws Exception {
        Label eInput = new Label("input uno:");
        Button pulsante = new Button("premimi");

        GridPane griglia = new GridPane();
        griglia.setHgap(5);
        griglia.setVgap(5);
        griglia.setPadding(new Insets(5,5,5,5));

        griglia.add(eInput,   0, 0);
        griglia.add(tNumero,   1, 0);
        griglia.add(pulsante, 2, 0, 1, 2);
        griglia.add(eRisultato, 0, 1, 2, 1);
        
        pulsante.setMaxHeight(Integer.MAX_VALUE);
        
        Scene scena = new Scene(griglia);
        finestra.setScene(scena);
        finestra.setTitle("Da completare");
        finestra.show();

        pulsante.setOnAction( e-> individuaDivisori());
    }

    public void individuaDivisori() {
        int numero, contatore;
        boolean divisoreTrovato;
        numero = Integer.parseInt(tNumero.getText());
        divisoreTrovato = false;
        for(contatore = 2; contatore < numero; contatore++) {
           if(numero%contatore == 0) {
               divisoreTrovato = true;
           }
        }
        if( divisoreTrovato ) {
           eRisultato.setText("Il numero non è primo");
        } else {
            eRisultato.setText("Il numero è primo!");
        }
     }

    public static void main(String[] args) {
        launch(args);
    }

}