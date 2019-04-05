package main;

import com.jfoenix.controls.JFXButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.swing.Timer;

/**
 *
 * @author juans
 */
public class Deteccion_De_ErroresController implements Initializable {

    FileManager fm = new FileManager();

    @FXML
    private AnchorPane deteccion;
    @FXML
    private AnchorPane correccion;
    @FXML
    private AnchorPane creacion;
    @FXML
    private AnchorPane visualizacion;

    @FXML
    private AnchorPane loader;
    @FXML
    private AnchorPane loader1;
    @FXML
    private AnchorPane loader2;

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
    private JFXButton detectar;
    @FXML
    private JFXButton validar1;

    @FXML
    private Label fileList;

    @FXML
    void home(MouseEvent event) {
        home.getStyleClass().removeAll("buttons");
        home.getStyleClass().add("parent");
        detect.getStyleClass().removeAll("parent");
        detect.getStyleClass().add("buttons");
        fix.getStyleClass().removeAll("parent");
        fix.getStyleClass().add("buttons");
        create.getStyleClass().removeAll("parent");
        create.getStyleClass().add("buttons");
        files.getStyleClass().removeAll("parent");
        files.getStyleClass().add("buttons");

        deteccion.setVisible(false);
        correccion.setVisible(false);
        creacion.setVisible(false);
        visualizacion.setVisible(false);
    }

    @FXML
    void detect(MouseEvent event) {
        home.getStyleClass().removeAll("parent");
        home.getStyleClass().add("buttons");
        detect.getStyleClass().removeAll("buttons");
        detect.getStyleClass().add("parent");
        fix.getStyleClass().removeAll("parent");
        fix.getStyleClass().add("buttons");
        create.getStyleClass().removeAll("parent");
        create.getStyleClass().add("buttons");
        files.getStyleClass().removeAll("parent");
        files.getStyleClass().add("buttons");
        deteccion.setVisible(true);
        correccion.setVisible(false);
        creacion.setVisible(false);
        visualizacion.setVisible(false);

    }

    @FXML
    void fix(MouseEvent event) {
        home.getStyleClass().removeAll("parent");
        home.getStyleClass().add("buttons");
        detect.getStyleClass().removeAll("parent");
        detect.getStyleClass().add("buttons");
        fix.getStyleClass().removeAll("buttons");
        fix.getStyleClass().add("parent");
        create.getStyleClass().removeAll("parent");
        create.getStyleClass().add("buttons");
        files.getStyleClass().removeAll("parent");
        files.getStyleClass().add("buttons");

        deteccion.setVisible(false);
        correccion.setVisible(true);
        creacion.setVisible(false);
        visualizacion.setVisible(false);
    }

    @FXML
    void create(MouseEvent event) {
        home.getStyleClass().removeAll("parent");
        home.getStyleClass().add("buttons");
        detect.getStyleClass().removeAll("parent");
        detect.getStyleClass().add("buttons");
        fix.getStyleClass().removeAll("parent");
        fix.getStyleClass().add("buttons");
        create.getStyleClass().removeAll("buttons");
        create.getStyleClass().add("parent");
        files.getStyleClass().removeAll("parent");
        files.getStyleClass().add("buttons");
        deteccion.setVisible(false);
        correccion.setVisible(false);
        creacion.setVisible(true);
        visualizacion.setVisible(false);
    }

    @FXML
    void view(MouseEvent event) {
        home.getStyleClass().removeAll("parent");
        home.getStyleClass().add("buttons");
        detect.getStyleClass().removeAll("parent");
        detect.getStyleClass().add("buttons");
        fix.getStyleClass().removeAll("parent");
        fix.getStyleClass().add("buttons");
        create.getStyleClass().removeAll("parent");
        create.getStyleClass().add("buttons");
        files.getStyleClass().removeAll("buttons");
        files.getStyleClass().add("parent");
        deteccion.setVisible(false);
        correccion.setVisible(false);
        creacion.setVisible(false);
        visualizacion.setVisible(true);
    }

    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }

    animationController ac = new animationController();

    @FXML
    void validate(ActionEvent event) {
        ac.animation(loader, detectar);
    }

    @FXML
    void actualizar(ActionEvent event) throws IOException {
        ArrayList Files = fm.getFiles();
        System.out.println(Files);
        StringBuilder text = new StringBuilder();
        
        for (Object File : Files) {
            text.append("(-) ").append(File).append("\n");
        }
        
        fileList.setText(text.toString());
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
        data.add("Hola, mi nombre es karla");

        FileManager fm = new FileManager();
        try {
            fm.createFile("msj_3", ".txt", data, false);
        } catch (IOException ex) {
            System.out.println("Error en creacion de archivo de mensaje");
        }

        dataManager dm = new dataManager();
        dm.setDataWords(dm.generateDataWords(data, 2));
        dm.setCodeWords(dm.generateCodeWords(dm.getDataWords()));

        try {
            fm.createFile("msj_3", ".btp", dm.getCodeWords(), false);
        } catch (IOException ex) {
            System.out.println("Error en la creacion de el archivo de palabras de codigo");
        }
    }

}
