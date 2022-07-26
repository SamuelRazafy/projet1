/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneAdmin;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Inclusiv Academy 7
 */
public class AnnuaireElectroniqu extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentAccueil.fxml"));
        File f = new File("src\\sceneAdmin\\MicrosoftTeams-image.png");
        Image icon = new Image(f.toURI().toString());
        stage.getIcons().add(icon);
        stage.setTitle("Studictionary");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
