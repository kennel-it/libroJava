package it.aspix.librojava.elementigrafici;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class DPolygon extends Application{
    
    public void start(Stage ps) throws Exception {
        Polygon poligono = new Polygon();
        poligono.getPoints().addAll(new Double[]{
            0.0, 0.0,
            60.0, 30.0,
            40.0, 40.0,
            30.0, 60.0 });
        GridPane pannello = new GridPane();
        pannello.add(poligono, 0, 0);
        
        Scene scena = new Scene(pannello);
        ps.setScene(scena);
        ps.setTitle("demo");
        ps.show();
    }
    
    public static void main(String args[]) {
        launch(args);
    }
}