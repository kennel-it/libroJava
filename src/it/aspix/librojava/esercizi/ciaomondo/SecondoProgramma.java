package it.aspix.librojava.esercizi.ciaomondo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Cambiare il titolo e le stringhe di testo
 */
public class SecondoProgramma extends Application {

  Button pSaluto = new Button(); 

  public void start(Stage finestra) {
    pSaluto.setText("3+4=?");
    pSaluto.setOnAction(e -> esegui());

    BorderPane principale = new BorderPane();
    principale.setCenter(pSaluto);

    Scene scena = new Scene(principale, 300, 250);

    finestra.setTitle("Il secondo programma");
    finestra.setScene(scena);
    finestra.show();
  }

  private void esegui(){
    pSaluto.setText("12");
  }

  public static void main(String[] args) {
    launch(args);
  }
}