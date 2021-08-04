package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasCerchioQuadrato extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas tela = new Canvas(200,200);
        GraphicsContext gc = tela.getGraphicsContext2D();
        
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.strokeRect(0, 0, 200, 200); 
        gc.setStroke(Color.GREEN);
        gc.strokeOval(0, 0, 200, 200);
        
        GridPane root = new GridPane();
        root.add(tela, 0, 0);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Picasso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
