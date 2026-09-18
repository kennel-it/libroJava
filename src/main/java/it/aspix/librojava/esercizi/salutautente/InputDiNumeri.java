package it.aspix.librojava.esercizi.salutautente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * - leggere i dati dai tre slider superiori al click sul pulsante "leggi"
 * - lo slider inferiore è in aggiornamento al movimento
 */
public class InputDiNumeri extends Application {

    Slider cursoreLiscio = new Slider(0,10,5);
    Slider cursoreTacche = new Slider(0,10,5);
    Slider cursoreVincolato = new Slider(0,10,5);
    Slider cursoreAutonomo = new Slider(0,10,5);

    TextField tfLiscio = new TextField();
    TextField tfTacche = new TextField();
    TextField tfVincolato = new TextField();
    TextField tfAutonomo = new TextField();

    public void start(Stage finestra) {

        cursoreTacche.setShowTickMarks(true);
        cursoreTacche.setShowTickLabels(true);

        cursoreVincolato.setShowTickMarks(true);
        cursoreVincolato.setShowTickLabels(true);
        cursoreVincolato.setMajorTickUnit(1);
        cursoreVincolato.setMinorTickCount(0);
        cursoreVincolato.setSnapToTicks(true);

        GridPane griglia = new GridPane();
        Button pLeggi = new Button("Leggi");

        griglia.add(cursoreLiscio, 0, 0);
        griglia.add(tfLiscio, 1, 0);
        griglia.add(cursoreTacche, 0, 1);
        griglia.add(tfTacche, 1, 1);
        griglia.add(cursoreVincolato, 0, 2);
        griglia.add(tfVincolato, 1, 2);
        griglia.add(pLeggi, 1, 3);
        griglia.add(cursoreAutonomo, 0, 4);
        griglia.add(tfAutonomo, 1, 4);

        Scene scena = new Scene(griglia);

        finestra.setTitle("Sliders!");
        finestra.setScene(scena);
        finestra.show();
        
        pLeggi.setOnAction( e -> azioneLeggi() );
        cursoreAutonomo.setOnMouseDragged( e -> azioneAutonomo() );
        
    }

    private void azioneLeggi() {
        tfLiscio.setText( ""+cursoreLiscio.getValue() );
        tfTacche.setText( ""+cursoreTacche.getValue() );
        tfVincolato.setText( ""+cursoreVincolato.getValue() );
    }
    
    private void azioneAutonomo() {
        tfAutonomo.setText( ""+cursoreAutonomo.getValue() );
    }

    public static void main(String[] args) {
        launch(args);
    }
}