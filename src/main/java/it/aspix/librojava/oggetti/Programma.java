package it.aspix.librojava.oggetti;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Programma extends Application{
    
    Autoveicolo automobili[];
    int autoRegistrate = 0;
    
    TextField inTarga = new TextField();
    TextField inModello = new TextField();
    TextField inLitri = new TextField();
    TextField calcTarga = new TextField();
    TextField calcKm = new TextField();
    Label risposta = new Label();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button aggiungi = new Button("aggiungi auto");
        Button calcolaSpesa = new Button("calcola spesa");
        GridPane gp = new GridPane();
        gp.add(new Label("targa:"),0,0);
        gp.add(inTarga,1,0);
        gp.add(new Label("modello:"),0,1);
        gp.add(inModello,1,1);
        gp.add(new Label("consumo:"),0,2);
        gp.add(inLitri,1,2);
        gp.add(aggiungi,1,3);
        gp.add(new Label("targa:"),0,4);
        gp.add(calcTarga,1,4);
        gp.add(new Label("km/l:"),0,5);
        gp.add(calcKm,1,5);
        gp.add(calcolaSpesa,1,6);
        gp.add(new Label("spesa:"),0,7);
        gp.add(risposta,1,7);
        
        Scene scena = new Scene(gp);
        primaryStage.setScene(scena);
        primaryStage.setTitle("automobili");
        primaryStage.show();
        
        aggiungi.setOnAction(e->aggiungiAuto());
        calcolaSpesa.setOnAction(e->azioneSpesa());
        // ci prendiamo una licenza: non ci saranno mai più di 100 automobili
        
        automobili = new Autoveicolo[100];
    }
    
    public static void main(String args[]){
        launch(args);
    }
    
    private void aggiungiAuto(){
        String t = inTarga.getText();
        String m = inModello.getText();
        double km = Double.parseDouble(inLitri.getText());
        
        automobili[autoRegistrate] = new Autoveicolo(t, m, km);
        autoRegistrate++;
        risposta.setText(t+" registrata");
    }
    
    private void azioneSpesa(){
        String t = calcTarga.getText();
        double kilometri = Double.parseDouble(calcKm.getText());
        Autoveicolo trovata = null;
        double spesa;
        
        for(int i=0;i<autoRegistrate;i++){
            if(automobili[i].targa.equals(t)){
                trovata = automobili[i];
            }
        }
        
        if(trovata!=null) {
            spesa = trovata.costoPercorso(kilometri);
            risposta.setText("l'automobile "+trovata.modello+" spende "+spesa );
        }else{
            risposta.setText("non trovo "+t);
        }
    }
}