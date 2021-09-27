package it.aspix.librojava.esercizi.salutautente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Questo programma serve soltanto come modello di una interfaccia, le funzionalità non sono attive
 * 
 * ATTENZIONE: è intricato da fare senza FXML
 * 
 * TabPane, prendere come modello il libro, la prima linguetta è uguale per la seconda 
 * si può usare un GridPane al posto dell'etichetta
 *
 */
public class Linguette extends Application{
    
    public void start(Stage ps) throws Exception {
        TabPane pannelloPrincipale = new TabPane();
        
        Tab linguetta1 = new Tab("quadrato");
        Tab linguetta2 = new Tab("rettangolo");
        Label e1 = new Label ("non mi interessano i quadrati");
        linguetta1.setContent(e1);
        
        GridPane pannello2 = new GridPane();
        pannello2.add(new Label("base"), 0, 0);
        pannello2.add(new TextField(), 1, 0);
        pannello2.add(new Label("altezza"), 0, 1);
        pannello2.add(new TextField(), 1, 1);
        pannello2.add(new Button("area="), 0, 2);
        pannello2.add(new TextField(), 1, 2);
        linguetta2.setContent(pannello2);
        
        // aggiungo le linguette al loro contenitore
        pannelloPrincipale.getTabs().add(linguetta1);
        pannelloPrincipale.getTabs().add(linguetta2);
        
        Scene scena = new Scene(pannelloPrincipale);
        ps.setScene(scena);
        ps.setTitle("geometria");
        ps.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}