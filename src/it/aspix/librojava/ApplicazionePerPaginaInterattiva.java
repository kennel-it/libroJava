package it.aspix.librojava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ApplicazionePerPaginaInterattiva extends Application{
	
	TextField cNome = new TextField("James");
	Label eRisposta = new Label("Click su saluta");
	
	@Override
	public void start(Stage primaryStage){
		Button pSaluta = new Button("disegna");
		Label eNome = new Label("nome:");
		GridPane griglia = new GridPane();
		
		griglia.add(eNome,0,0);
		griglia.add(cNome, 1, 0);
		griglia.add(pSaluta, 0, 1, 2, 1);
		griglia.add(eRisposta, 0, 2, 2, 1);
		pSaluta.setMaxWidth(Integer.MAX_VALUE);
		Scene s = new Scene(griglia);
		primaryStage.setScene(s);
		primaryStage.setTitle("reticolato");
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		eRisposta.setId("risp");
		s.getStylesheets().add("it/aspix/librojava/stileApplicazione.css");
		
		primaryStage.show();
		
		pSaluta.setOnAction(e->saluta());
	}
	
	private void saluta(){
		String nome = cNome.getText();
		eRisposta.setText("ciao "+nome);
	}
	
	public static void main(String x[]){ launch(x); }
}
