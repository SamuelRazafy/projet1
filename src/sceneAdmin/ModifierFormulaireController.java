/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneAdmin;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sceneAdmin.ChargementTxt;
import sceneAdmin.Etudiant;

/**
 * FXML Controller class
 *
 * @author inclu
 */
public class ModifierFormulaireController implements Initializable {

    public TextField getTxtNom() {
        return txtNom;
    }

    public void setTxtNom(TextField txtNom) {
        this.txtNom = txtNom;
    }

    public TextField getTxtPrenom() {
        return txtPrenom;
    }

    public void setTxtPrenom(TextField txtPrenom) {
        this.txtPrenom = txtPrenom;
    }

    public TextField getTxtContact() {
        return txtContact;
    }

    public void setTxtContact(TextField txtContact) {
        this.txtContact = txtContact;
    }

    public TextField getTxtFormation() {
        return txtFormation;
    }

    public void setTxtFormation(TextField txtFormation) {
        this.txtFormation = txtFormation;
    }

    public TextField getTxtLocalisation() {
        return txtLocalisation;
    }

    public void setTxtLocalisation(TextField txtLocalisation) {
        this.txtLocalisation = txtLocalisation;
    }

    public RadioButton getRadioMasculin() {
        return radioMasculin;
    }

    public void setRadioMasculin(RadioButton radioMasculin) {
        this.radioMasculin = radioMasculin;
    }

    public ToggleGroup getSexe() {
        return sexe;
    }

    public void setSexe(ToggleGroup sexe) {
        this.sexe = sexe;
    }

    public RadioButton getRadioFeminin() {
        return radioFeminin;
    }

    public void setRadioFeminin(RadioButton radioFeminin) {
        this.radioFeminin = radioFeminin;
    }

    public Button getBtnAjouter() {
        return btnAjouter;
    }

    public void setBtnAjouter(Button btnAjouter) {
        this.btnAjouter = btnAjouter;
    }

    public Button getBtnAnnuler() {
        return btnAnnuler;
    }

    public void setBtnAnnuler(Button btnAnnuler) {
        this.btnAnnuler = btnAnnuler;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public DatePicker getDateRentreeUniversitaire() {
        return dateRentreeUniversitaire;
    }

    public void setDateRentreeUniversitaire(DatePicker dateRentreeUniversitaire) {
        this.dateRentreeUniversitaire = dateRentreeUniversitaire;
    }

    public ComboBox<?> getComboSecteur() {
        return comboSecteur;
    }

    public void setComboSecteur(ComboBox<?> comboSecteur) {
        this.comboSecteur = comboSecteur;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChargementTxt getLoadTxt() {
        return loadTxt;
    }

    public void setLoadTxt(ChargementTxt loadTxt) {
        this.loadTxt = loadTxt;
    }

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
    private Button btnAjouter;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dateRentreeUniversitaire;
    @FXML
    private ComboBox<?> comboSecteur;
    @FXML
    private TextField txtId;
    
    
    
    private Etudiant student;
   
    
    

    public Etudiant getStudent() {
        return student;
    }

    public Etudiant setStudent(Etudiant student) {
        txtNom.setText(student.getNom());
        txtPrenom.setText(student.getPrenom());
        txtLocalisation.setText(student.getLocalisation());
        txtEmail.setText(student.getEmail());
        txtContact.setText(student.getContact());
        txtFormation.setText(student.getRgp_formation());
        txtId.setText(String.valueOf(student.getId()));
        String date = student.getRentree_Universitaire()+"-12";
        date = date.replace("-","/");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        dateRentreeUniversitaire.setValue(localDate);
        System.out.println(date);
       return student;
    }
   
    
    String url="src\\sceneAdmin\\donneeProjet.txt";
    public ChargementTxt loadTxt = new ChargementTxt(url);
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionAjouter(ActionEvent event)throws IOException {
         try {
             FileWriter writer = new FileWriter(url, false);
             loadTxt.chargerTxt();
            System.out.println(loadTxt.getData());
            System.out.println("Bon la ca passe");
            
            for(Etudiant e:loadTxt.getData()) {
                System.out.println(e.getId());
                if(e.getId()==Integer.parseInt(txtId.getText())){
                    loadTxt.getData().remove(e);
                    break;
                }
            }
           
            
            for(Etudiant e:loadTxt.getData()) {
                writer.write(e.toStringCsv());
            }
            writer.close();
         }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void actionAnnuler(ActionEvent event)  {
        
    }
       
    
}
