package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EccezioniEsecizio1 extends Application {
	TextField cDato = new TextField("numero");
	TextField cRisposta = new TextField("");

	public void start(Stage primaryStage) {		
		Button pDoppio = new Button("doppio");
		GridPane p = new GridPane();
		p.add(cDato, 0, 0);
		p.add(pDoppio, 0, 1);
		p.add(cRisposta, 0, 2);
		pDoppio.setOnAction( e -> scambia() );
		Scene scena = new Scene(p);
		primaryStage.setTitle("Doppio");
		primaryStage.setScene(scena);
		primaryStage.show();
	}

	public void scambia() {
		String testo;
		int numero,doppio;
		testo = cDato.getText();
		numero = Integer.parseInt(testo);
		doppio = numero / 0;
		cRisposta.setText(""+doppio);

	}
	
	public static void main(String args[]){
		launch();
	}
}