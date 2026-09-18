package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Quadratini extends Application{

	public static void main(String x[]){
		launch(x);
	}
	
	Canvas tela = new Canvas(200,200);
	
	@Override
	public void start(Stage primaryStage){
		GridPane griglia = new GridPane();
		Button disegna = new Button("disegna");
		griglia.add(tela,0,0,5,1);
		griglia.add(disegna, 4, 1);
		
		Scene s = new Scene(griglia);
		primaryStage.setScene(s);
		primaryStage.setTitle("quadrati");
		primaryStage.show();
		
		disegna.setOnAction(e->disegnaReticolo());
	}

	private void disegnaReticolo(){
		GraphicsContext gc = tela.getGraphicsContext2D();
		int x,y;
		double caso;
		
		for(x=0;x<20;x++){
			for(y=0;y<20;y++){
				caso = Math.random();
				if(caso<0.5){
					gc.setFill(Color.BLUE);
				}else{
					gc.setFill(Color.YELLOW);
				}
				gc.fillRect(x*10, y*10, 9, 9);
			}
		}
	}
}
