package it.aspix.librojava.mouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PittoreComplicato extends Application{
    
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Canvas tela = new Canvas(300, 300);
        gc = tela.getGraphicsContext2D();
        tela.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gestisciClick(e));
        tela.addEventHandler(MouseEvent.MOUSE_MOVED, e -> gestisciMovimento(e));
        tela.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> gestisciTrascinamento(e));
        root.add(tela, 0, 0);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Picasso");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
                
    public void gestisciTrascinamento(MouseEvent e) {
        gc.setFill(Color.GREEN);
        gc.fillRect(e.getX() - 2, e.getY() - 2, 5, 5);
    }

    public void gestisciMovimento(MouseEvent e) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(e.getX() - 2, e.getY() - 2, 5, 5);
    }

    public void gestisciClick(MouseEvent evento){
        double posizioneX = evento.getX();
        double posizioneY = evento.getY();
        gc.setFill(Color.BLUE);
        // disegno un piccolo quadrato intorno al punto in cui
        // è stato fatto click
        gc.fillOval(posizioneX - 4, posizioneY - 4, 9, 9);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}