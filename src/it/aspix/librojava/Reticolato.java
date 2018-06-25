package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Reticolato extends Application{

	public static void main(String x[]){
		launch(x);
	}
	
	TextField dx = new TextField("10");
	TextField dy = new TextField("20");
	Canvas tela = new Canvas(400,400);
	
	@Override
	public void start(Stage primaryStage){
		GridPane griglia = new GridPane();
		Label e_dx = new Label("dx:");
		Label e_dy = new Label("dy:");
		Button disegna = new Button("disegna");
		griglia.add(tela,0,0,5,1);
		griglia.add(e_dx, 0, 1);
		griglia.add(dx, 1, 1);
		griglia.add(e_dy, 2, 1);
		griglia.add(dy, 3, 1);
		griglia.add(disegna, 4, 1);
		
		Scene s = new Scene(griglia);
		primaryStage.setScene(s);
		primaryStage.setTitle("reticolato");
		primaryStage.show();
		
		disegna.setOnAction(e->disegnaReticolo());
	}

	private void disegnaReticolo(){
		GraphicsContext gc = tela.getGraphicsContext2D();
		int distanzaX = Integer.parseInt(dx.getText());
		int distanzaY = Integer.parseInt(dy.getText());
		int x,y;
		
		for(x=0;x<400;x=x+distanzaX){
			gc.strokeLine(x, 0, x, 400);
		}
		for(y=0;y<400;y=y+distanzaY){
			gc.strokeLine(0, y, 400, y);
		}
	}
}
