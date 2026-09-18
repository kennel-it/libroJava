package it.aspix.librojava;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class AllineamentoGridPane extends Application{
    

    @Override
    public void start(Stage finestra) throws Exception {
        Button pulsante1 = new Button("uno");
        Button pulsante2 = new Button("due");
        Button pulsante3 = new Button("tre");
        Button pulsante4 = new Button("quattro");
        Button pulsante5 = new Button("cinque");
        Button pulsante6 = new Button("sei");
        
        pulsante1.setPrefSize(150, 60);
        pulsante2.setPrefSize( 50, 20);
        pulsante3.setPrefSize( 50, 20);
        pulsante4.setPrefSize(150, 60);
        pulsante5.setPrefSize(200, 40);
        pulsante6.setPrefSize( 50, 20);

        GridPane griglia = new GridPane();
        griglia.setHgap(5);
        griglia.setVgap(5);
        griglia.setPadding(new Insets(5,5,5,5));

        griglia.add(pulsante1, 0, 0);
        griglia.add(pulsante2, 1, 0);
        griglia.add(pulsante3, 0, 1);
        griglia.add(pulsante4, 1, 1);
        griglia.add(pulsante5, 0, 2);
        griglia.add(pulsante6, 1, 2);
        
        ColumnConstraints vincoliPrimaColonna = new ColumnConstraints();
        vincoliPrimaColonna.setHalignment(HPos.RIGHT);
        ColumnConstraints vincoliSecondaColonna = new ColumnConstraints();
        vincoliSecondaColonna.setHalignment(HPos.CENTER);
        griglia.getColumnConstraints().addAll(vincoliPrimaColonna, vincoliSecondaColonna);

        RowConstraints vincoliRiga0 = new RowConstraints();
        vincoliRiga0.setValignment(VPos.CENTER);
        RowConstraints vincoliRiga1 = new RowConstraints();
        vincoliRiga1.setValignment(VPos.CENTER);
        RowConstraints vincoliRiga2 = new RowConstraints();
        vincoliRiga2.setValignment(VPos.CENTER);
        griglia.getRowConstraints().addAll(vincoliRiga0, vincoliRiga1, vincoliRiga2);
        
        Scene scena = new Scene(griglia);
        finestra.setScene(scena);
        finestra.setTitle("Allineamento");
        finestra.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}