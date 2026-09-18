package it.aspix.librojava.movimentocollisioni;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Collisione extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane areaDiGioco = new Pane();
        areaDiGioco.setPrefSize(500, 500);
        
        Image iBase = new Image(getClass().getResourceAsStream("linea.png"));
        ImageView linea1 = new ImageView(iBase);
        linea1.setRotate(45);
        linea1.setTranslateX(50);
        linea1.setPreserveRatio(true);
        areaDiGioco.getChildren().add(linea1);
        
        ImageView linea2 = new ImageView(iBase);
        linea2.setPreserveRatio(true);
        areaDiGioco.getChildren().add( linea2 );
        linea2.setX(120);
        linea2.setY(100);
        
        Bounds b1 = linea1.getBoundsInParent();
        plotBBOx(areaDiGioco,b1);
        Bounds b2 = linea2.getBoundsInParent();
        
        if(b1.intersects(b2)) {
            primaryStage.setTitle("collisione");
        }else {
            primaryStage.setTitle("tutto ok");
        }
                
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.show();
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
