package it.aspix.librojava.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timer1 extends Application{    
  Label testo;
  int n=100;
    
  @Override
  public void start(Stage primaryStage) throws Exception {
    testo = new Label("finestra timer");
    GridPane griglia = new GridPane();
    griglia.add(testo,  0,  0);
    Scene scene = new Scene(griglia,200,100);
    primaryStage.setTitle("Timer!"); 
    primaryStage.setScene(scene);
    primaryStage.show();
    Timeline timeline = new Timeline(new KeyFrame(
      Duration.seconds(1), // ogni quanto va chiamata la funzione
      x -> aggiornaTimer()));
    timeline.setCycleCount(100);
    timeline.play();
  }

  private void aggiornaTimer(){
    testo.setText(""+ (n--));
  }
    
  public static void main(String args[]){
    launch();
  }
}