/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
        data.add("Hola, mi nombre es Juan David Solano");
        data.add("esto es una prueba.");
        FileManager fm = new FileManager();
        try {
            fm.createFile("msj_1", ".txt", data, false);
        } catch (IOException ex) {
            System.out.println("Error en creacion de archivo");
        }

        dataManager dm = new dataManager();
        System.out.println(dm.string2bin(","));

    }

}
