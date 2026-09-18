package it.aspix.librojava;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sommatore extends Application {
	TextField addendi[];
	Text risultato = new Text();

	public void start(Stage primaryStage) {
		addendi = new TextField[10];
		int i;
		Text totale = new Text("Totale");
		Button somma = new Button("Somma");
		Text spiegazioni[] = new Text[10];
		for (i = 0; i < 10; i++) {
			addendi[i] = new TextField();
			spiegazioni[i] = new Text("Addendo" + (i + 1));
		}
		GridPane p = new GridPane();
		p.add(somma, 0, 10);
		p.add(totale, 0, 11);
		p.add(risultato, 1, 11);
		for (i = 0; i < 10; i++) {
			p.add(addendi[i], 1, i);
			p.add(spiegazioni[i], 0, i);
		}
		somma.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addiziona();
			}
		});
		Scene scena = new Scene(p);
		primaryStage.setTitle("Aggiungi");
		primaryStage.setScene(scena);
		primaryStage.show();
	}

	public void addiziona() {
		int i, s;
		for (i = s = 0; i < 10; i++) {
			s += Integer.parseInt(addendi[i].getText());
		}
		risultato.setText("" + s);
	}
	
	public static void main(String args[]){
		launch();
	}
}