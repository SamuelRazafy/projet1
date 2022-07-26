/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneUser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import sceneAdmin.ChargementTxt;
import sceneAdmin.Etudiant;
import sceneAdmin.FXMLDocumentControllerAccueil;

/**
 * FXML Controller class
 *
 * @author inclu
 */
public class RechercheController implements Initializable {
String url="src\\sceneAdmin\\donneeProjet.txt";
    ChargementTxt loadTxt = new ChargementTxt(url);
    
    @FXML
    private TextField txtRechercher;
    
    @FXML
    private TableView<Etudiant> tableAccueilAdmin;
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
    
    ObservableList<Etudiant> res = FXCollections.observableArrayList();
    
    public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm){
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList< >(hm.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        
          // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    @FXML
    private Button btnRechercher;
    
    private void itemsTable(ObservableList<Etudiant> obs){
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
           
            tableAccueilAdmin.setItems(obs);
        
        
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
                    if(entry.getKey() == e.getId()){
                        res.add(e);
                        break;
                    }
                }              
            }
            

            //Fonction pour tableView.setItems
            itemsTable(res);
            
      
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
                        Logger.getLogger(AnnuaireinverséController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        // TODO
        Collections.sort(loadTxt.getData());
        try {
            loadTxt.chargerTxt();
        } catch (IOException ex) {
            Logger.getLogger(RechercheController.class.getName()).log(Level.SEVERE, null, ex);
        }

           //Fonction pour tableView.setItems
            itemsTable(loadTxt.getData());
    }    

  

    @FXML
    private void actionRechercher(ActionEvent event) throws IOException {
        recherche();
    }

    

  
    
}
