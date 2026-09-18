package it.aspix.librojava;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EsempioFXML extends Application{
    @FXML 
    Label output;
    @FXML
    TextField input;
    
    @FXML
    private void copia() {
        String dato = input.getText();
        output.setText(dato);
    }
    
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage finestra) throws Exception {
        Scene scena = new Scene( FXMLLoader.load(EsempioFXML.class.getResource("EsempioFXML.fxml")) );
        finestra.setScene(scena);
        finestra.setTitle("ciao");
        finestra.show();
    }
}
