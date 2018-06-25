package it.aspix.librojava;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ElementiPerGUI extends Application{
	
	TextField cNome = new TextField("James");
	Label eRisposta = new Label("Click su saluta");
	Window finestra;
	
	@Override
	public void start(Stage primaryStage){
		finestra = primaryStage;
		Button pAlert = new Button("attenzione!");
		Button pValue = new Button("valore?");
		Button pFileChooser = new Button("scegli file");
		Button pApriFinestra = new Button("apri finestra");
		Label eNome = new Label("nome:");
		GridPane griglia = new GridPane();
		
		griglia.add(eNome,0,0);
		griglia.add(cNome, 1, 0);
		griglia.add(pAlert, 1, 1);
		griglia.add(pValue, 1, 2);
		griglia.add(pFileChooser, 1, 3);
		griglia.add(pApriFinestra, 1, 4);
		griglia.add(eRisposta, 0, 10, 2, 1);
		Scene s = new Scene(griglia);
		primaryStage.setScene(s);
		primaryStage.setTitle("reticolato");
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		eRisposta.setId("risp");
		
		primaryStage.show();
		
		cNome.setOnAction(e->saluta());
		pAlert.setOnAction(e->alert());
		pValue.setOnAction(e->valore());
		pFileChooser.setOnAction(e->scegliFile(e));
		pApriFinestra.setOnAction(e->apriFinestra());
	}
	
	private void saluta(){
		String nome = cNome.getText();
		eRisposta.setText("ciao "+nome);
	}
	
	private void alert(){
		Alert dialogoAllerta = new Alert(AlertType.CONFIRMATION, "Sei sicuro che vuoi formattare il sistema?");
		Optional<ButtonType> risposta = dialogoAllerta.showAndWait();
		if(risposta.isPresent() && risposta.get() == ButtonType.OK) {
			eRisposta.setText("Neanche per scherzo!");
		}
	}
	
	private void valore(){
		TextInputDialog dialogoInput = new TextInputDialog();
		dialogoInput.setTitle("Tokyo");
		dialogoInput.setHeaderText("Gruppo viaggio");
		dialogoInput.setContentText("quante persone in totale?");
		Optional<String> risposta = dialogoInput.showAndWait();
		if(risposta.isPresent()){
			eRisposta.setText("persone: "+risposta.get());
		}
	}
	
	private void scegliFile(ActionEvent e){
		FileChooser selettoreFile = new FileChooser();
		String cartellaPartenza = System.getProperties().getProperty("user.home");
		selettoreFile.setInitialDirectory(new File(cartellaPartenza));
		// File file = fc.showOpenDialog( ((Button)e.getSource()).getScene().getWindow() );
		selettoreFile.getExtensionFilters().addAll(new ExtensionFilter("File html", "*.html"));
		File fileSelezionato = selettoreFile.showOpenDialog( finestra );
		eRisposta.setText(fileSelezionato.toString());
	}
	
	private void apriFinestra(){
		SecondaFinestra sf = new SecondaFinestra();
		sf.initModality(Modality.APPLICATION_MODAL);
		sf.show();
	}
	
	public static void main(String x[]){ launch(x); }
}
