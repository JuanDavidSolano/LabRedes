package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author juans
 */
public class Deteccion_De_ErroresController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hello world!");

        ArrayList<String> data = new ArrayList();
        data.add("Hola, mi nombre es");

        FileManager fm = new FileManager();
        try {
            fm.createFile("msj_1", ".txt", data, false);
        } catch (IOException ex) {
            System.out.println("Error en creacion de archivo de mensaje");
        }

        dataManager dm = new dataManager();
        dm.setDataWords(dm.generateDataWords(data));
        dm.setCodeWords(dm.generateCodeWords(dm.getDataWords()));

        try {
            fm.createFile("msj_1", ".btp", dm.getCodeWords(), true);
        } catch (IOException ex) {
            System.out.println("Error en la creacion de el archivo de palabras de codigo");
        }
    }

}
