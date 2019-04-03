package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author juans
 */
public class Deteccion_De_ErroresController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private AnchorPane prueba;

    @FXML
    private ImageView button;

    @FXML
    private HBox home;
    @FXML
    private HBox detect;
    @FXML
    private HBox fix;
    @FXML
    private HBox create;
    @FXML
    private HBox files;
    @FXML
    private HBox exit;

    @FXML
    void test(MouseEvent event) {
        prueba.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Tooltip.install(home, (new Tooltip("Inicio")));
        Tooltip.install(detect, (new Tooltip("Detección de errores")));
        Tooltip.install(fix, (new Tooltip("Corrección de errores")));
        Tooltip.install(create, (new Tooltip("Crear archivo")));
        Tooltip.install(files, (new Tooltip("Ver archivos disponibles")));
        Tooltip.install(exit, (new Tooltip("Salir")));

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
