package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/****************************************************************************
 * Usato nel capitolo debugger
 * @author Edoardo Panfili, studio Aspix
 ***************************************************************************/
public class Debugger extends Application {
	TextField cNumero = new TextField();
	Label eRisultato = new Label("risposta");

	public void start(Stage primaryStage) {		
		Label eNumero = new Label("numero:");
		Button pDoppio = new Button("Doppio");
		
		GridPane p = new GridPane();
		p.add(eNumero, 0, 0);
		p.add(cNumero, 1, 0);
		p.add(pDoppio, 1, 1);
		p.add(eRisultato, 1, 2);
		pDoppio.setOnAction( e -> raddoppia() );
		Scene scena = new Scene(p);
		primaryStage.setTitle("Doppio");
		primaryStage.setScene(scena);
		primaryStage.show();
	}

	public void raddoppia() {
		String testo;
		String risposta;
		int numero;
		int doppio;
		
		testo = cNumero.getText();
		numero = Integer.parseInt(testo);
		doppio = numero + 2;
		risposta = "il doppio di "+numero+" è "+doppio;
		eRisultato.setText(risposta);
	}
	
	public static void main(String args[]){
		launch();
	}
}