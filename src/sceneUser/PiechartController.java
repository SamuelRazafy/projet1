/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 7
 */
public class PiechartController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("PieChart1.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(PiechartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void lancerAction1(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("PieChart1.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void lancerAction2(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PieChart2.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
