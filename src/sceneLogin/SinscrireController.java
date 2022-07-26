/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneLogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sceneUser.AnnuaireinverséController;

/**
 * FXML Controller class
 *
 * @author inclu
 */
public class SinscrireController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField passMotDePasse;
    @FXML
    private PasswordField passConfirmationMotDePasse;
    @FXML
    private Button sInscrire;
    
    Hashage hash = new Hashage();

    /**
     * Initializes the controller class.
     */
    String pathToFile = "src\\sceneLogin\\data.csv";
    ;

    public SinscrireController() throws FileNotFoundException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionSinscrire(ActionEvent event) throws IOException {
        String pass = passMotDePasse.getText();
        String confimationMotDePasse = passConfirmationMotDePasse.getText();
   
        String utilisateur = txtUserName.getText();
        
        if(pass == null ? confimationMotDePasse == null : pass.equals(confimationMotDePasse)){
            String passWordHashed = hash.getHash(pass.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)));
            TreeMap<String, Integer> dictUserNameAndPassWord = new TreeMap<>();
            String line;
            while((line = br.readLine())!=null){
                String[] tempArray = line.split(",");
                if(tempArray.length>0){
                    dictUserNameAndPassWord.put(tempArray[0],1);
                }
            }
            if (dictUserNameAndPassWord.containsKey(utilisateur)==false){
                try{
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Utilisateur Enregister");
                    alert.setContentText("Bienvenue "+utilisateur.toUpperCase());
                    alert.showAndWait();
                    FileWriter fstream = new FileWriter(pathToFile,true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(txtUserName.getText()+ ","+passWordHashed+",user\n");
                    out.close();
                }catch (Exception e){
                    System.err.println("Error while writing to file: " +
                            e.getMessage());
                }
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Utilisateur Existant");
                alert.setContentText("Cet utilisateur existe déjà");
                alert.showAndWait();
            }
       }else{
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Erreur de Mot de Passe");
           alert.setContentText("Les deux mots de passe ne sont pas similaires");
           alert.showAndWait();
        }
    }

    @FXML
    private void KeyActionSinscrire(KeyEvent event) {
         passConfirmationMotDePasse.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                   String pass = passMotDePasse.getText();
        String confimationMotDePasse = passConfirmationMotDePasse.getText();
   
        String utilisateur = txtUserName.getText();
        
        if(pass == null ? confimationMotDePasse == null : pass.equals(confimationMotDePasse)){
            String passWordHashed = hash.getHash(pass.getBytes());
            BufferedReader br = null;
                       try {
                           br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)));
                       } catch (FileNotFoundException ex) {
                           Logger.getLogger(SinscrireController.class.getName()).log(Level.SEVERE, null, ex);
                       }
            TreeMap<String, Integer> dictUserNameAndPassWord = new TreeMap<>();
            String line;
                       try {
                           while((line = br.readLine())!=null){
                               String[] tempArray = line.split(",");
                               if(tempArray.length>0){
                                   dictUserNameAndPassWord.put(tempArray[0],1);
                               }
                           }          } catch (IOException ex) {
                           Logger.getLogger(SinscrireController.class.getName()).log(Level.SEVERE, null, ex);
                       }
            if (dictUserNameAndPassWord.containsKey(utilisateur)==false){
                try{
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Utilisateur Enregister");
                    alert.setContentText("Utilisateur Enregister"+utilisateur.toUpperCase());
                    alert.showAndWait();
                    FileWriter fstream = new FileWriter(pathToFile,true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(txtUserName.getText()+ ","+passWordHashed+",user\n");
                    out.close();
                }catch (Exception e){
                    System.err.println("Error while writing to file: " +
                            e.getMessage());
                }
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Utilisateur Existant");
                alert.setContentText("Cet utilisateur existe déjà");
                alert.showAndWait();
            }
       }else{
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Erreur de Mot de Passe");
           alert.setContentText("Les deux mots de passe ne sont pas similaires");
           alert.showAndWait();
        }
                }
            }
        });
    }
    
}
