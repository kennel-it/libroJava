package it.aspix.librojava.movimentocollisioni;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Oggetti extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane areaDiGioco = new Pane();
        areaDiGioco.setPrefSize(300, 300);
        
        Rectangle rettangolo = new Rectangle(50,100);
        rettangolo.setX(200);
        rettangolo.setY(10);
        rettangolo.setFill(Color.RED);
        areaDiGioco.getChildren().add(rettangolo);
        
        Circle cerchio = new Circle(20);
        cerchio.setCenterX(100);
        cerchio.setCenterY(200);
        cerchio.setFill( Color.GREEN );
        areaDiGioco.getChildren().add(cerchio);
        
        Polygon poligono = new Polygon(100,100,50,50,50,100);
        poligono.setFill(Color.YELLOW);
        areaDiGioco.getChildren().add(poligono);
        
        Image iBase = new Image(getClass().getResourceAsStream("asteroide.gif"));
        ImageView asteroide = new ImageView(iBase);
        asteroide.setPreserveRatio(true);
        asteroide.setFitWidth(40);
        areaDiGioco.getChildren().add(asteroide);
                
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("L1");
        primaryStage.show();
    }
}
