package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Doppio extends Application{

    TextField cNumero = new TextField();
    
    public void start(Stage palcoscenico) throws Exception {
        GridPane pannello = new GridPane();
        Label eNumero = new Label("numero:");
        Button pDoppio = new Button("calcola doppio");
        
        pannello.add(eNumero, 0, 0);
        pannello.add(cNumero, 1, 0);
        pannello.add(pDoppio, 0, 1, 2, 1);
        
        pDoppio.setMaxWidth(1000);
        pDoppio.setOnAction( e-> azioneDoppio());
        
        Scene scena = new Scene(pannello);
        palcoscenico.setScene(scena);
        palcoscenico.setTitle("Doppio");
        palcoscenico.show();
    }
    
    private void azioneDoppio() {
        String testoNellaCasella = cNumero.getText();
        int numero = Integer.parseInt( testoNellaCasella );
        int doppio;
        
        doppio = numero * 2;
        
        cNumero.setText( "" + doppio );
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
