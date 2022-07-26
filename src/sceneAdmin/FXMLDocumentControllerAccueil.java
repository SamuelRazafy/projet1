/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sceneAdmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static sceneUser.RechercheController.sortByValue;

/**
 * FXML Controller class
 *
 * @author thaja
 */
public class FXMLDocumentControllerAccueil implements Initializable {
    String url="src\\sceneAdmin\\donneeProjet.txt";
    
    public ChargementTxt loadTxt = new ChargementTxt(url);
    
    
    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField txtRechercher;
    @FXML
    private Button btnRechercher;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    public TableView<Etudiant> tableAccueilAdmin;
    @FXML
    private TableColumn<Etudiant,String> colNom;
    @FXML
    private TableColumn<Etudiant,String> colPrenom;
    @FXML
    private TableColumn<Etudiant,String> colGenre;
    @FXML
    private TableColumn<Etudiant,String> colLocalisation;
    @FXML
    private TableColumn<Etudiant,String> colEmail;
    @FXML
    private TableColumn<Etudiant,String> colContact;
    @FXML
    private TableColumn<Etudiant,String> colRentreeUniversitaire;
    @FXML
    private TableColumn<Etudiant,String> colFormation;
    @FXML
    private TableColumn<Etudiant,String> colSecteur;
    
    
    
    public String nomModifier;
    
    ObservableList<Etudiant> res = FXCollections.observableArrayList();
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier1;
    
    public TableView itemsTable(ObservableList<Etudiant> obs){
         colRentreeUniversitaire.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("rentree_Universitaire")
            );
            colContact.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("contact")
            );
            colEmail.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("email")
            );
            colNom.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("nom")
            );
            colPrenom.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("prenom")
            );
            
            colLocalisation.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("localisation")
            );
            colFormation.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("rgp_formation")
            );
            colSecteur.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("secteur")
            );
            colGenre.setCellValueFactory(
                    new PropertyValueFactory<Etudiant,String>("sexe")
            );
            Collections.sort(obs);
            tableAccueilAdmin.setItems(obs);  
            return tableAccueilAdmin;
    }
    
    
    private void recherche() throws IOException{
       
        ObservableList<Etudiant> tempRes = FXCollections.observableArrayList();
        HashMap<Integer, Integer> dictIDandOccurence = new HashMap<>();
        tableAccueilAdmin.getItems().clear();
        res.clear();
        loadTxt.getData().clear();
        try{
            
            //fonction charger text (url)
            loadTxt.chargerTxt();
            
           String textRecherche = txtRechercher.getText().replace(","," ");
            textRecherche = textRecherche.replace("/"," ");
            textRecherche = textRecherche.replace("ç", "c");
            textRecherche = textRecherche.replace("é","e");
            textRecherche = textRecherche.replace("ë","e");
            
            String [] tabTexteRecherche = textRecherche.toLowerCase().split(" ");
            
            for (Etudiant e: loadTxt.getData()){
                for(String a:tabTexteRecherche){
                    String eToString;
                    eToString =e.toString().replace("ë","e");
                    eToString =eToString.replace("ç","c");
                    eToString = eToString.replace("é","e");
                    if (e.toString().toLowerCase().contains(a)){
                    if(dictIDandOccurence.containsKey(e.getId())){
                        int occurence = dictIDandOccurence.get(e.getId());
                        occurence++;
                        dictIDandOccurence.put(e.getId(),occurence);
                        }else{
                            dictIDandOccurence.put(e.getId(), 1);
                        }
                        tempRes.add(e); 
                    }
                }
                
            }
            
            //Gestion de redondance;
           dictIDandOccurence = sortByValue(dictIDandOccurence);
           System.out.println("Sorted:"+dictIDandOccurence);
           for (Map.Entry<Integer, Integer> entry: dictIDandOccurence.entrySet()){
                for(Etudiant e: tempRes){
                    if(entry.getValue()>=tabTexteRecherche.length-1)
                    if(entry.getKey() == e.getId()){
                        res.add(e);
                        break;
                    }
                }              
            }
            //Trier par order alphabetique les recherches si elles sont inferieur a 1
            if(tabTexteRecherche.length<=1)Collections.sort(res);
            //Fonction pour tableView.setItems
            tableAccueilAdmin.setItems(res);
            
      
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    private void supprimer(){
         try {
            
            for(Etudiant e:loadTxt.getData()) {
                if(e.getId()==tableAccueilAdmin.getSelectionModel().getSelectedItem().getId()){
                    loadTxt.getData().remove(e);
                    break;
                }
            }
            //Supprimer Apres Recherche()
            for(Etudiant e:res) {
                if(e.getId()==tableAccueilAdmin.getSelectionModel().getSelectedItem().getId()){
                    res.remove(e);
                    loadTxt.getData().remove(e);
                    break;
                }
            }
            FileWriter writer = new FileWriter(url, false);
            for(Etudiant e:loadTxt.getData()) {
                writer.write(e.toStringCsv());
                
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    

    
    @FXML
    private void actionRechercher(ActionEvent event) throws IOException {
        
        recherche();
        mainPane.setCenter(anchorpane);
    }
    
    
    @FXML
    private void handleButtonAcceuilAction(ActionEvent event) throws IOException {
        mainPane.setCenter(anchorpane);
        
        
        
    }
    @FXML
    private void handleButtonFormulaireAction(ActionEvent event) {
        
        FxmlLoader object= new FxmlLoader();
        Pane view= object.getPage("Formulaire");
        mainPane.setCenter(view);
    }
    
    @FXML
    private void handleButtonStatistiqueAction(ActionEvent event) {
        FxmlLoader object= new FxmlLoader();
        Pane view= object.getPage("StatistiqueAdmin");
        mainPane.setCenter(view);
    }
    @FXML
    void actionSupprimer(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer etudiant");
        alert.setContentText("Etes-vous sur de vouloir supprimer l'etudiant "+tableAccueilAdmin.getSelectionModel().getSelectedItem().getNom()+" ?");
        alert.showAndWait(); 
        supprimer();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtRechercher.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    
                    try {
                        recherche();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        tableAccueilAdmin.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.DELETE)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Supprimer etudiant");
                    alert.setContentText("Etes-vous sur de vouloir supprimer l'etudiant "+tableAccueilAdmin.getSelectionModel().getSelectedItem().getNom()+" ?");
                    alert.showAndWait();
                    supprimer();
                }
            }
        });
        
        try{
            loadTxt.chargerTxt();
           //Fonction pour tableView.setItems
            System.out.println(loadTxt.getData());
            itemsTable(loadTxt.getData());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionModifier(ActionEvent event) throws IOException {
        Etudiant etudiant = tableAccueilAdmin.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("modifierFormulaire.fxml"));
        DialogPane studentDialogPane = fxmlLoader.load();
        ModifierFormulaireController studentController = fxmlLoader.getController();
        studentController.setStudent(etudiant);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(studentDialogPane);
        

        Optional<ButtonType> clickedButton;
        clickedButton = dialog.showAndWait();
        if(clickedButton.get()==ButtonType.APPLY){
            try{
            String prenomEtudiant = upperCaseFirst(studentController.getTxtPrenom().getText().toLowerCase());
            String nomEtudiant = studentController.getTxtNom().getText().toUpperCase();
            String localisationEtudiant = upperCaseFirst(studentController.getTxtLocalisation().getText());
            String sexeEtudiant = "masculin";
            String contactEtudiant = studentController.getTxtContact().getText();
            String emailEtudiant = studentController.getTxtEmail().getText();
            String formationEtudiant = studentController.getTxtEmail().getText();
            String rentreeEtudiant = studentController.getDateRentreeUniversitaire().getValue().format(DateTimeFormatter.ofPattern("yyyy-MM")).toString();
            String secteurEtudiant = "jioaji";
            for(Etudiant e:loadTxt.getData()) {
                if(e.getId()==tableAccueilAdmin.getSelectionModel().getSelectedItem().getId()){
                    loadTxt.getData().remove(e);
                    break;
                }
            }
            Etudiant etudiantModifier = new Etudiant(rentreeEtudiant,localisationEtudiant, formationEtudiant, secteurEtudiant,
                    sexeEtudiant, nomEtudiant, prenomEtudiant, contactEtudiant, emailEtudiant);
            etudiant.setId(tableAccueilAdmin.getSelectionModel().getSelectedItem().getId());
            ObservableList<Etudiant> afterModification = FXCollections.observableArrayList();
            
            loadTxt.getData().add(etudiantModifier);
            afterModification = loadTxt.getData();
            Collections.sort(afterModification);
            tableAccueilAdmin.setItems(afterModification);
            System.out.println(loadTxt.getData());
            FileWriter writer = new FileWriter(url, false);
            for(Etudiant e:loadTxt.getData()) {
                writer.write(e.toStringCsv());
            }
            writer.close();
            }catch(Exception e){
                System.out.println("e");
            }
        }
        
        
    }
     private String upperCaseFirst(String val){
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
}
