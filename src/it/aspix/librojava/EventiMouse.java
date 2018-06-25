package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EventiMouse extends Application {
	GraphicsContext gc;

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		Canvas canvas = new Canvas(300, 300);
		gc = canvas.getGraphicsContext2D();
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> gestisciTrascinamento(e));
		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> gestisciMovimento(e));
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gestisciClick(e));
		primaryStage.addEventHandler(KeyEvent.KEY_TYPED, e -> gestisciTasto(e));
		root.add(canvas, 0, 0);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Picasso");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void gestisciTrascinamento(MouseEvent e) {
		gc.setFill(Color.RED);
		gc.fillRect(e.getX() - 2, e.getY() - 2, 5, 5);
	}
	
	public void gestisciMovimento(MouseEvent e) {
		gc.setFill(Color.YELLOW);
		gc.fillRect(e.getX() - 2, e.getY() - 2, 5, 5);
	}
	
	public void gestisciClick(MouseEvent e) {
		gc.setFill(Color.BLUE);
		gc.fillRect(e.getX() - 4, e.getY() - 4, 9, 9);
	}
	public void gestisciTasto(KeyEvent e) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0, 20, 20);
	}

	public static void main(String[] args) {
		launch(args);
	}
}