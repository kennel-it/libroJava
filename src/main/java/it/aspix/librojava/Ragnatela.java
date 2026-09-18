package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Ragnatela extends Application{

	public static void main(String x[]){
		launch(x);
	}
	
	TextField distanza = new TextField("100");
	TextField lati = new TextField("3");
	Canvas tela = new Canvas(400,400);
	GraphicsContext gc = tela.getGraphicsContext2D();
	
	@Override
	public void start(Stage primaryStage){
		GridPane griglia = new GridPane();
		griglia.add(tela,0, 0);
		
		Scene s = new Scene(griglia);
		primaryStage.setScene(s);
		primaryStage.setTitle("ragnatela");
		primaryStage.show();
		
		for(int i=0;i<400;i+=20){
			gc.strokeLine(0,i, i, 400);
		}
		
	}
}
