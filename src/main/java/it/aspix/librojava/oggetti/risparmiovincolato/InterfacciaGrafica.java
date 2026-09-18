package it.aspix.librojava.oggetti.risparmiovincolato;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacciaGrafica extends Application {
    TextField tNome = new TextField();
    TextField tCognome = new TextField();
    TextField tCodice = new TextField();
    TextField tImporto = new TextField();
    TextField tAnno = new TextField();
    TextField tInteressiPercentuale = new TextField();
    Label lNomeCognome = new Label("Nome e cognome");
    Label lData = new Label("Data vincolo");
    Label lGuadagnoETotale = new Label("Guadagno in €");
    RisparmioVincolato risparmio;

    public void start(Stage finestra) {
       Label lNome = new Label("Nome");
       Label lCognome = new Label("Cognome");
       Label lCodice = new Label("Codice");
       Label lImporto = new Label("Importo");
       Label lDataVincolo = new Label("Anno");
       Label lInteressiPercentuale = new Label("Interessi");
       Button bAcquisisci = new Button("Acquisisci");
       Button bMostra = new Button("Mostra guadagno");
       bAcquisisci.setOnAction(e->acquisisci());
       bMostra.setOnAction(e->mostra());
       GridPane pannello = new GridPane();
       pannello.add(lNome, 0, 0);
       pannello.add(tNome, 1, 0);
       pannello.add(lCognome, 0, 1);
       pannello.add(tCognome, 1, 1);
       pannello.add(lCodice, 0, 2);
       pannello.add(tCodice, 1, 2);
       pannello.add(lImporto, 0, 3);
       pannello.add(tImporto, 1, 3);
       pannello.add(lInteressiPercentuale, 0, 4);
       pannello.add(tInteressiPercentuale, 1, 4);
       pannello.add(lDataVincolo, 0, 5);
       pannello.add(tAnno, 1, 5);
       pannello.add(bAcquisisci, 0, 6);
       pannello.add(bMostra, 1, 6);
       pannello.add(lNomeCognome, 0, 7, 2, 1);
       pannello.add(lData, 0, 8, 2, 1);
       pannello.add(lGuadagnoETotale, 0, 9, 2, 1);
       Scene scena = new Scene(pannello,500,500);
       finestra.setScene(scena);
       finestra.setTitle("prospetto d'investimento");
       finestra.show();
    }

    public void acquisisci() {
       String sNome = tNome.getText();
       String sCognome = tCognome.getText();
       int iCodice = Integer.parseInt(tCodice.getText());
       float fImporto = Float.parseFloat(tImporto.getText());
       float fInteressi = Float.parseFloat(tInteressiPercentuale.getText());
       short iAnno = (short) Integer.parseInt(tAnno.getText());
       // La new la facciamo qui piuttosto che insieme agli altri oggetti di tipo 
       // TextField perché solo qui sappiamo quali valori usare nel costruttore
       risparmio = new RisparmioVincolato(sNome, sCognome, iCodice, fImporto, iAnno, fInteressi);
       // In questo momento tutte le informazioni lette sono memorizzate nelle proprietà dell'oggetto risparmio
       tNome.setText("");
       tCognome.setText("");
       tCodice.setText("");
       tImporto.setText("");
       tInteressiPercentuale.setText("");
       tAnno.setText("");
       lNomeCognome.setText("Vincolo di " + risparmio.nome + " " + risparmio.cognome);
       lData.setText("fatto l'anno " + risparmio.anno+ "con interessi del " + risparmio.interessiPercentuale + "%");
    }

    public void mostra() {
       lGuadagnoETotale.setText("Guadagno previsto " + 
         risparmio.fornisciGuadagno() + "€; il capitale diventerà " + 
         risparmio.fornisciCapitale() + "€");
    }

    public static void main(String args[]) {
       launch();
    }

 }