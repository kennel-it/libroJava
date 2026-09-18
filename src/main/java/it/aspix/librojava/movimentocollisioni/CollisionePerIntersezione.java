package it.aspix.librojava.movimentocollisioni;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class CollisionePerIntersezione extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane areaDiGioco = new Pane();
        areaDiGioco.setPrefSize(200, 300);
        
        Rectangle linea1 = new Rectangle(30,200);
        linea1.setRotate(60);
        linea1.setX(50);
        areaDiGioco.getChildren().add(linea1);
        
        Rectangle linea2 = new Rectangle(30,200);
        areaDiGioco.getChildren().add( linea2 );
        linea2.setX(120);
        linea2.setY(75);
        
        plotBBOx(areaDiGioco, linea1.getBoundsInParent());
                
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.show();
        
        Shape intersect = Shape.intersect(linea1, linea2);
        
        if (intersect.getBoundsInLocal().getWidth() != -1){
            primaryStage.setTitle("collisione");
        }else {
            primaryStage.setTitle("nessuna collisione");
        }
    }
    
    private void plotBBOx(Pane p, Bounds b) {
        Rectangle r = new Rectangle(b.getWidth(), b.getHeight());
        r.setFill(null);
        r.setStroke(Color.RED);
        r.setTranslateX(b.getMinX());
        r.setTranslateY(b.getMinY());
        p.getChildren().add(r);
    }
}
