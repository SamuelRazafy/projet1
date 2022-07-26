package sceneAdmin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FormulaireController implements Initializable {

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFormation;

    @FXML
    private TextField txtLocalisation;

    @FXML
    private RadioButton radioMasculin;
    
    

    @FXML
    private ToggleGroup sexe;

    @FXML
    private RadioButton radioFeminin;


   

    
    @FXML
    private DatePicker dateRentreeUniversitaire;


    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnAnnuler;
    
    @FXML
    private ComboBox<String> comboSecteur;
    
   
    
    
    
    
    String pathToFile = "src\\sceneAdmin\\donneeProjet.txt";
   
   

    @FXML
    void actionAjouter(ActionEvent event) {
        
        if(txtNom.getText()!=null && txtPrenom.getText()!=null && txtLocalisation.getText()!=null &&
           txtEmail.getText()!=null && txtContact.getText()!=null && txtFormation.getText()!=null && 
                dateRentreeUniversitaire.getValue()!=null &&(radioFeminin.isSelected()|| radioMasculin.isSelected())){
            try{
                String nom, prenom, localisation, email, contact, formation, secteur, rentreeUniversitaire,sexe;
                nom = txtNom.getText().toUpperCase();
                prenom = upperCaseFirst(txtPrenom.getText().toLowerCase());
                localisation = txtLocalisation.getText();
                email = txtEmail.getText();
                contact = txtContact.getText();
                formation = txtFormation.getText();
                secteur = comboSecteur.getValue().toString();
                rentreeUniversitaire = dateRentreeUniversitaire.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM")).toString();
                if(radioFeminin.isSelected())sexe="Feminin";
                else sexe="Masculin";
                
            FileWriter fstream = new FileWriter(pathToFile,true);
            try (BufferedWriter out = new BufferedWriter(fstream)) {
                out.write(rentreeUniversitaire+";"+localisation+";"+formation+";"+secteur+";"+sexe+";"+nom+";"+prenom+";"+contact+";"+email+"\n");
            }
        }catch (Exception e){
	 System.err.println("Error while writing to file: " +
         e.getMessage());
        }
        System.out.println("mety");
    }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
                if(txtNom.getText().length()==0)txtFormation.setStyle("-fx-border-color: red;");
                if(txtEmail.getText().length()==0)txtEmail.setStyle("-fx-border-color: red;");
                if(txtLocalisation.getText().length()==0)txtLocalisation.setStyle("-fx-border-color: red;");
                if(txtNom.getText().length()==0)txtNom.setStyle("-fx-border-color: red;");
                if(txtPrenom.getText().length()==0)txtPrenom.setStyle("-fx-border-color: red;");
                if(txtContact.getText().length()==0)txtContact.setStyle("-fx-border-color: red;");
                if(txtEmail.getText().length()==0)txtEmail.setStyle("-fx-border-color: red;");
                if(txtFormation.getText().length()==0)txtFormation.setStyle("-fx-border-color: red;");
                if(comboSecteur.getValue() == null)comboSecteur.setStyle("-fx-border-color: red;");
        
        }
        
        
        
        
        }
        
    
    
    @FXML
    void actionAnnuler(ActionEvent event) {
        annuler();
    }
    private void annuler(){
        txtNom.setText("");
        txtPrenom.setText("");
        txtLocalisation.setText("");
        txtEmail.setText("");
        txtContact.setText(null);
        txtFormation.setText(null);
    }
    private String upperCaseFirst(String val){
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboSecteur.setItems(FXCollections.observableArrayList("Établissements privés","Établissements publics"));
        radioMasculin.setSelected(true);
        
    }

}
