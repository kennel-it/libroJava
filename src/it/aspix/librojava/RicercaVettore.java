package it.aspix.librojava;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RicercaVettore extends Application{
	TextField c_dimensione = new TextField();
	TextField c_valore = new TextField();
	TextField c_cercare = new TextField();
	Label risposta = new Label();
	int v[];
	int pos;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label e_dimensione = new Label("dimensione:");
		Label e_valore = new Label("valore da inserire:");
		Label e_cercare = new Label("valore da cercare:");
		Button crea = new Button("crea");
		Button inserisci = new Button("inserisci");
		Button cerca = new Button("cerca");
		Label e_risposta = new Label("trovato?");
		
		GridPane pg = new GridPane();
		pg.add(e_dimensione, 0, 0);
		pg.add(c_dimensione, 1, 0);
		pg.add(crea,         2, 0);
		pg.add(e_valore,     0, 1);
		pg.add(c_valore,     1, 1);
		pg.add(inserisci,    2, 1);
		pg.add(e_cercare,    0, 3);
		pg.add(c_cercare,    1, 3);
		pg.add(cerca,        2, 3);
		pg.add(e_risposta,   0, 4);
		pg.add(risposta,     1, 4);
		
		Scene scena = new Scene(pg);
		
		primaryStage.setScene(scena);
		primaryStage.setTitle("ricerca di un valore");
		primaryStage.show();
		
		crea.setOnAction(e->azioneCrea());
		inserisci.setOnAction(e->azioneInserisci());
		// cerca.setOnAction(e->azioneCerca());
		cerca.addEventHandler(ActionEvent.ACTION, e->azioneCerca());
		
		
	}
	
	public void azioneCrea(){
		int grandezza = Integer.parseInt(c_dimensione.getText());
		v = new int[grandezza];
		pos=0;
	}
	
	public void azioneInserisci(){
		int n = Integer.parseInt(c_valore.getText());
		if(pos<v.length){
			v[pos] = n;
			risposta.setText("inserito "+n+" alla posizione "+pos);
			pos++;
		}else{
			risposta.setText("vettore pieno");
		}
	}
	
	public void azioneCerca(){ 
		int indice, numeroCercato; 
		numeroCercato = Integer.parseInt(c_cercare.getText()); 
		boolean trovato = false; 
		
		for(indice = 0; indice < v.length; indice++) { 
			if(v[indice] == numeroCercato) { 
				trovato = true; 
			}
		} 
		if(trovato) { 
			risposta.setText(numeroCercato + " è presente"); 
		} else { 
			risposta.setText(numeroCercato + " NON è presente");
		}
	}
	
	public static void main(String args[]){
		launch(args);
	}
}
