  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneAdmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class StatistiqueAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane rootPanePie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lancerAction1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BarchartFormation.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    private void lancerAction2(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BarchartLocalisation.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void lancerAction3(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PieChart1.fxml"));
        rootPanePie.getChildren().setAll(pane);
    }

    @FXML
    private void lancerAction4(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PieChart2.fxml"));
        rootPanePie.getChildren().setAll(pane);
    }
    
}
