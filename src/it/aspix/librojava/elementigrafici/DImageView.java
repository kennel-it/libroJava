package it.aspix.librojava.elementigrafici;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DImageView extends Application{
    
    public void start(Stage ps) throws Exception {
        // leggo una immagine che si trova nella stessa cartella di questa classe
        Image i = new Image(getClass().getResourceAsStream("santoreggia.jpg"));
        ImageView iw = new ImageView(i);
        GridPane pannello = new GridPane();
        pannello.add(iw, 0, 0);
        
        Scene scena = new Scene(pannello);
        ps.setScene(scena);
        ps.setTitle("demo");
        ps.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}