package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DebuggerEsercizio extends Application {
	TextField cTesto = new TextField("ciao mondo");

	public void start(Stage primaryStage) {		
		Button pScambia = new Button("scambia parole");
		GridPane p = new GridPane();
		p.add(cTesto, 0, 0);
		p.add(pScambia, 0, 1);
		pScambia.setOnAction( e -> scambia() );
		Scene scena = new Scene(p);
		primaryStage.setTitle("Doppio");
		primaryStage.setScene(scena);
		primaryStage.show();
	}

	public void scambia() {
		String testo = cTesto.getText();
		char co[] = testo.toCharArray();
		char cd[] = new char[co.length];
		int ps=0;
		int i,d;
		for(i=0; i<co.length; i++){
			if(co[i]==' '){
				ps = i;
			}
		}
		for(i=ps+1,d=0; i<co.length; i++,d++){
			cd[d] = co[i];
		}
		cd[d]=' ';
		for(i=0;i<ps; i++,d++){
			cd[d]=co[i];
		}
		cTesto.setText( new String(cd) );
	}
	
	public static void main(String args[]){
		launch();
	}
}