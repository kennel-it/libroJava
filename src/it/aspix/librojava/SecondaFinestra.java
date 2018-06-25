package it.aspix.librojava;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SecondaFinestra extends Stage{
	public SecondaFinestra(){
		Label testo = new Label("finestra aperta");
        Scene scene = new Scene(new BorderPane(testo),200,100);
        this.setTitle("Seconda finestra!"); 
        this.setScene(scene); 
	}
}
