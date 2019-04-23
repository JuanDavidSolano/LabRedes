package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputMethodEvent;
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
    dataManager dm = new dataManager();

    @FXML
    private AnchorPane deteccion;
    @FXML
    private AnchorPane correccion;
    @FXML
    private AnchorPane creacion;
    @FXML
    private AnchorPane visualizacion;

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
    private JFXButton createF;

    @FXML
    private Label fileList;
    @FXML
    private Label mensaje1;
    @FXML
    private Label resultado;
    @FXML
    private Label mensajeC;

    @FXML
    private JFXTextField Archivo1;
    @FXML
    private JFXTextField fileNameC;

    @FXML
    private JFXTextArea FileContent;

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

    int k = 0;
    Timer t;

    @FXML
    void validate(ActionEvent event) throws IOException {
        ArrayList Files = fm.getFiles();
        System.out.println(Files);
        if (Files.contains(Archivo1.getText() + ".btp")) {
            mensaje1.setText("Archivo existente");
            mensaje1.setStyle("-fx-text-fill: #2BFF00");
            detectar.setDisable(false);
        } else {
            mensaje1.setText("Este arhivo no existe, porfavor creelo o corrijalo.");
            mensaje1.setStyle("-fx-text-fill:#B22222");
        }

        /*k = 0;
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                k++;
                if (k == 5) {
                    loader.setVisible(false);
                    t.stop();
                    mensaje1.setText("Hola");
                }
            }
        });
        t.start();
         */
    }

    public void crearFile() {
        try {
            String name = fileNameC.getText();
            ArrayList<String> contenido = new ArrayList();
            contenido.add(FileContent.getText());
            fm.createFile(name, ".txt", contenido, false);
            mensajeC.setText("Archivo creado con exito");
            mensajeC.setStyle("-fx-text-fill: #2BFF00");
        } catch (IOException ex) {
            mensajeC.setText("Error al crear el archivo");
            mensajeC.setStyle("-fx-text-fill:#B22222");
        }
    }

    public void detectar() throws IOException {
        String name = Archivo1.getText();
        boolean verif = dm.verificar(fm.readFile(name, ".btp"));
        if (verif) {
            mensaje1.setText("No se detecto ningun error");
            ArrayList<String> data = fm.readFile(name, ".txt");
            StringBuilder string = new StringBuilder();
            for (String string1 : data) {
                string.append(string1);
            }
            resultado.setText(string.toString());
        } else {
            mensaje1.setText("Se detecto almenos 1 error");
            mensaje1.setStyle("-fx-text-fill:#B22222");
        }
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

        Archivo1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mensaje1.setText("Porfavor escriba el nombre del archivo que desea usar.");
                detectar.setDisable(true);
                mensaje1.setStyle("-fx-text-fill:#ffffff");
                detectar.setDisable(true);
                resultado.setText("");
            }
        });

        ArrayList<String> data = new ArrayList();
        data.add("Hola, mi nombre es karla");

        FileManager fm = new FileManager();
        try {
            fm.createFile("msj_3", ".txt", data, false);
        } catch (IOException ex) {
            System.out.println("Error en creacion de archivo de mensaje");
        }

        dm.setDataWords(dm.generateDataWords(data, 2));
        dm.setCodeWords(dm.generateCodeWords(dm.getDataWords()));

        try {
            fm.createFile("msj_3", ".btp", dm.getCodeWords(), false);
        } catch (IOException ex) {
            System.out.println("Error en la creacion de el archivo de palabras de codigo");
        }
    }

}
