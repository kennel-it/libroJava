package it.aspix.librojava.movimentocollisioni;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RimbalzoOrizzontale extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    Pane areaDiGioco = new Pane();
    Circle pallina = new Circle(20);
    double pallinaX;
    double incremento = 1;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        areaDiGioco.setPrefSize(200, 80);
        
        pallina.setFill(Color.RED);
        pallinaX = 20;
        pallina.setCenterX(pallinaX);
        pallina.setCenterY(40);
        areaDiGioco.getChildren().add(pallina);
                
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
        
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(0.025), // ogni quanto va chiamata la funzione
                x -> spostaPallina()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void spostaPallina() {
        pallinaX = pallinaX + incremento;
        // 20 è il raggio della pallina
        if (pallinaX >= 200-20) {
            incremento = -1;
        }
        if (pallinaX <= 20) {
            incremento = 1;
        }
        pallina.setCenterX(pallinaX);
    }
}
