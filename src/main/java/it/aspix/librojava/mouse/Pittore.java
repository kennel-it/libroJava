package it.aspix.librojava.mouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Pittore extends Application{
    
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Canvas canvas = new Canvas(300, 300);
        gc = canvas.getGraphicsContext2D();
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gestisciClick(e));
        root.add(canvas, 0, 0);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Picasso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
                
    public void gestisciClick(MouseEvent evento){
        double posizioneX = evento.getX();
        double posizioneY = evento.getY();
        gc.setFill(Color.RED);
        // disegno un piccolo quadrato intorno al punto in cui
        // è stato fatto click
        gc.fillRect(posizioneX - 2, posizioneY - 2, 5, 5);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}