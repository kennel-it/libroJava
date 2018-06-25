package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DebuggerEsercizio2 extends Application {
	TextField cTesto = new TextField("8");
	Label eResponso = new Label("responso");
	int numero;

	public void start(Stage primaryStage) {
		Button pProva = new Button("prova");
		GridPane p = new GridPane();
		p.add(cTesto, 0, 0);
		p.add(pProva, 0, 1);
		p.add(eResponso, 0, 2);
		pProva.setOnAction(e -> prova());
		numero = (int) (Math.random() * 10000);
		Scene scena = new Scene(p, 300, 100);
		/* metti qui il breakpoint */ primaryStage.setTitle("Doppio");
		primaryStage.setScene(scena);
		primaryStage.show();
	}

	public void prova() {
		int tentativo = Integer.parseInt(cTesto.getText());
		if (tentativo == numero) {
			eResponso.setText("indovinato!");
		} else {
			if (tentativo > numero) {
				eResponso.setText("il numero da indovinare è più piccolo");
			} else {
				eResponso.setText("il numero da indovinare è più grande");
			}
		}
	}

	public static void main(String args[]) {
		launch();
	}
}