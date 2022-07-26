/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneUser;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sceneLogin.SeConnecterController;

/**
 * FXML Controller class
 *
 * @author MAMY
 */
public class FXMLUtilisateurController extends SeConnecterController implements Initializable {

    @FXML
    private Label LabelUser;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void bt_Recherche(ActionEvent event) {
       Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("Recherche.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.close();

                stage.show();
         } catch (IOException ex) {
             Logger.getLogger(FXMLUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
         }

                
    }
    
     @FXML
    private void bt_Statistique(ActionEvent event) {
       Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.close();

                stage.show();
         } catch (IOException ex) {
             Logger.getLogger(FXMLUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
         }

                
    }
    
    @FXML
    private void bt_Contact(ActionEvent event) {
       Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("Contact.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.close();

                stage.show();
         } catch (IOException ex) {
             Logger.getLogger(FXMLUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
         }

                
    }
    
          @FXML
    private void bt_annuaireinversé(ActionEvent event) {
       Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("Annuaireinversé.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.close();

                stage.show();
         } catch (IOException ex) {
             Logger.getLogger(FXMLUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
         }

                
    }
    
       @FXML
    private void bt_Deconnecter(MouseEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/sceneLogin/SeConnecter.fxml"));

                Scene scene = new Scene(root);
                Stage stage=new Stage();

                stage.setScene(scene);
                stage.close();

                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(use.getNomUser());
//       String user= use.getNomUser().toUpperCase(Locale.ITALY);
//        LabelUser.setText(user); 
    }    

 

    
}
