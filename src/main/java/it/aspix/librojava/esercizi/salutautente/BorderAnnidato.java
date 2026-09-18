package it.aspix.librojava.esercizi.salutautente;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Per quelli che sanno programmare già i vincoli vanno specificati molto bene
 * L'interfaccia è brutta ma è solo un rompicapo le faremo più belle
 * 
 * 1: Creare una interfaccia che usa soltanto BorderPane eTextfield e Button
 *     (puoi farglielo vedere subito in movimento (resize) o rimandare a dopo che lo ha fatto)
 * 2: quando si pigia il pulsante scrive il nome del pulsante sotto
 */
public class BorderAnnidato extends Application {

    TextField casellaAlto = new TextField("");
    TextField casellaBasso = new TextField("");

    public void start(Stage finestra) {

        BorderPane pannelloItaliano = new BorderPane();
        Button uno = new Button("uno");
        Button due = new Button("due");
        Button tre = new Button("tre");
        BorderPane pannelloInglese = new BorderPane();
        Button one = new Button("one");
        Button two = new Button("two");
        Button three = new Button("threee");
        
        pannelloItaliano.setTop(uno);
        pannelloItaliano.setCenter(due);
        pannelloItaliano.setBottom(tre);
        
        pannelloInglese.setTop(one);
        pannelloInglese.setCenter(two);
        pannelloInglese.setBottom(three);

        BorderPane principale = new BorderPane();
        principale.setTop(casellaAlto);
        principale.setBottom(casellaBasso);
        principale.setLeft( pannelloItaliano );
        principale.setRight( pannelloInglese );

        Scene scena = new Scene(principale);

        finestra.setTitle("tanti pulsanti");
        finestra.setScene(scena);
        finestra.show();
        
        uno.setOnAction( a -> it("uno"));
        due.setOnAction( a -> it("due"));
        tre.setOnAction( a -> it("tre"));
        
        one.setOnAction( a -> en("one"));
        two.setOnAction( a -> en("two"));
        three.setOnAction( a -> en("three"));
    }

    private void it(String x){
        casellaAlto.setText(x);
    }
    
    private void en(String x){
        casellaBasso.setText(x);
    }

    public static void main(String[] args) {
        launch(args);
    }
}