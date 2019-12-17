package it.aspix.librojava.timer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Timer2 extends Application{
  private Label testo;
  private AnimationTimer timer;
  private long fine;
    
  @Override
  public void start(Stage primaryStage) throws Exception {
    testo = new Label("finestra timer");
    GridPane griglia = new GridPane();
    griglia.add(testo,  0,  0);
    Scene scene = new Scene(griglia,200,100);
    primaryStage.setTitle("Timer!"); 
    primaryStage.setScene(scene);
    primaryStage.show();
        
    timer = new AnimationTimer() { 
      @Override
      public void handle(long x) {
        aggiornaTimer(x);
      }
    };
    fine = System.nanoTime() + 1_000_000_000 * 100L;
    timer.start();
  }

  private void aggiornaTimer(long t){
    long n = (fine-t) / 1_000_000_000;
    testo.setText(""+n);
    if(n==0){
      timer.stop();
    }
  }
    
  public static void main(String args[]){
    launch();
  }
}
