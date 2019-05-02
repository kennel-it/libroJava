package it.aspix.librojava.movimentocollisioni;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Tasti extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    Pane areaDiGioco = new Pane();
    Label testo = new Label("premi un tasto");
    double pallinaX;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        areaDiGioco.setPrefSize(200, 80);
        
        areaDiGioco.getChildren().add(testo);
                
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
        
        scena.setOnKeyPressed(e->azioneTasto(e));
    }

    private void azioneTasto(KeyEvent e) {
        System.out.println(e.getText());
        if(e.getCode()==KeyCode.ENTER) {
            testo.setText("hai premuto invio");
        }
        if(e.getCode()==KeyCode.UP) {
            testo.setText("hai premuto freccia su");
        }
        if(e.getText().equals("d")) {
            testo.setText("hai premuto d");
        }
    }

}
