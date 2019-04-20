/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javax.swing.Timer;

/**
 *
 * @author juans
 */
public class animationController {

    int k = 0;
    Timer t;

    void animation(Deteccion_De_ErroresController dc, AnchorPane loader) {
        loader.setVisible(true);
        k = 0;
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                k++;
                if (k == 5) {
                    try {
                        loader.setVisible(false);
                        t.stop();
                        dc.prueba();
                    } catch (IOException ex) {
                        Logger.getLogger(animationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t.start();
    }

}
