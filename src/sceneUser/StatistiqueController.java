/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 7
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BorderPane PaneBorder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FxmlLoader object= new FxmlLoader();
        Pane view= object.getPage("Barchart");
        PaneBorder.setCenter(view);
    }    

    @FXML
    private void bt_Piechart(MouseEvent event) {
        FxmlLoader object= new FxmlLoader();
        Pane view= object.getPage("Piechart");
        PaneBorder.setCenter(view);
    }

    @FXML
    private void bt_Barchart(MouseEvent event) {
        FxmlLoader object= new FxmlLoader();
        Pane view= object.getPage("Barchart");
        PaneBorder.setCenter(view);
    }
    
}
