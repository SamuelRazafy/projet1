/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sceneLogin;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sceneUser.AnnuaireinverséController;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 7
 */
public class SeConnecterController implements Initializable {
    
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Label message;
     @FXML
    private Button connecter;
     
     
    public Utilisateur use= new Utilisateur(); 
 
    
    
//   private Stage stage = new Stage();
    /**
     * Initializes the controller class.
     */
    
// Map <Username,Password>
    HashMap<String,String> sceneLoginInfo = new HashMap<>();
    HashMap<String,String> sceneTypeUser = new HashMap<>();
    
//les datas sont stockés dans data.csv
    File file = new File("src\\sceneLogin\\data.csv");
//on instance la class Hashage
    Hashage hash = new Hashage();
   
    
    
   
    
    @FXML
    public void open_annuaire(ActionEvent event) throws IOException {
        
        Parent root;
        String username = user.getText();
        use.setNomUser(username);
        String pass = password.getText();
      
        try {
            updateInfo();
            if (sceneLoginInfo.containsKey(username)){
                String passwordHash = sceneLoginInfo.get(username);
                
                
                if(sceneTypeUser.get(username).matches("admin")){
                    if (hash.getHash(pass.getBytes()).matches(passwordHash)) {
                        root = FXMLLoader.load(getClass().getResource("/sceneAdmin/FXMLDocumentAccueil.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.close();
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de mot de passe");
                        alert.setContentText("Mot de Passe Erroné");
                        alert.showAndWait();
                    }
                } else{
                    if (hash.getHash(pass.getBytes()).matches(passwordHash)) {
                        root = FXMLLoader.load(getClass().getResource("/sceneUser/FXMLUtilisateur.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.close();
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de mot de passe");
                        alert.setContentText("Mot de Passe Erroné");
                        alert.showAndWait();
                    }
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Utilisateur Inxistant");
                alert.setContentText("Cet utilisateur n'existe pas");
                alert.showAndWait();
            }
            
            
            
        }
        catch (FileNotFoundException fileNotFoundException) {
            message.setText("Utilisateur non enregistré");
        }
        
    }
    
    private void updateInfo() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        sceneLoginInfo.clear();
        sceneTypeUser.clear();
        
        
        
        while(scanner.hasNext()){
            String[] usernameAndPassword = scanner.nextLine().split(",");
            if(usernameAndPassword.length >2){
                sceneLoginInfo.put(usernameAndPassword[0],usernameAndPassword[1]);
                sceneTypeUser.put(usernameAndPassword[0],usernameAndPassword[2]);
            }
        }
        
    }
    private void loadPage(String page) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void ActionKey(KeyEvent event) {
        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
              
                         Parent root;
        String username = user.getText();
        String pass = password.getText();
      
        try {
            updateInfo();
            if (sceneLoginInfo.containsKey(username)){
                String passwordHash = sceneLoginInfo.get(username);
                
                
                if(sceneTypeUser.get(username).matches("admin")){
                    if (hash.getHash(pass.getBytes()).matches(passwordHash)) {
                        root = FXMLLoader.load(getClass().getResource("/sceneAdmin/FXMLDocumentAccueil.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.close();
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de mot de passe");
                        alert.setContentText("Mot de Passe Erroné");
                        alert.showAndWait();
                    }
                } else{
                    if (hash.getHash(pass.getBytes()).matches(passwordHash)) {
                        root = FXMLLoader.load(getClass().getResource("/sceneUser/FXMLUtilisateur.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.close();
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de mot de passe");
                        alert.setContentText("Mot de Passe Erroné");
                        alert.showAndWait();
                    }
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Utilisateur Inxistant");
                alert.setContentText("Cet utilisateur n'existe pas");
                alert.showAndWait();
            }
            
            
            
        }
        catch (FileNotFoundException fileNotFoundException) {
            message.setText("Utilisateur non enregistré");
        }           catch (IOException ex) {
                        Logger.getLogger(SeConnecterController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                 
                }
            }
        });
    }
    
}
