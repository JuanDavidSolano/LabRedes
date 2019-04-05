/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXButton;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.swing.Timer;

/**
 *
 * @author juans
 */
public class animationController {

    int k = 0;
    Timer t;

    void animation(AnchorPane loader) {
        k = 0;
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                k++;
                if (k==5) {
                    t.stop();
                    loader.setVisible(false);
                }
            }
        });
        t.start();
    }

}
