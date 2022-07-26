/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sceneAdmin.Etudiant;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 7
 */
public class BarchartFormationController implements Initializable {

    @FXML
    private BarChart barFormation;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
        XYChart.Series series1 = new XYChart.Series<>();
        
       
        String line;
        int id = 0;
        Map<String, Integer> dictUniversity = new HashMap<>();
        try{
            String pathToFile = "src\\sceneAdmin\\donneeProjet.txt";
            BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), StandardCharsets.UTF_8)); 
            System.out.println("Print File Found");
          
            ObservableList<Etudiant> data = FXCollections.observableArrayList();
            
            while((line = br.readLine())!=null){
                Etudiant etudiant = new Etudiant();
                String[] tempArray = line.split(";");
                
                etudiant.setRentree_Universitaire(tempArray[0]);
                etudiant.setLocalisation(tempArray[1]);
              
                etudiant.setRgp_formation(tempArray[2]);
                if(dictUniversity.containsKey(tempArray[2])){
                int frequence = dictUniversity.get(tempArray[2]);
                frequence++;
                dictUniversity.put(tempArray[2], frequence);
                }else{
                    dictUniversity.put(tempArray[2], 1);
                }
                
                etudiant.setSecteur(tempArray[3]);
                etudiant.setSexe(tempArray[4]);
                etudiant.setNom(tempArray[5]);
                etudiant.setPrenom(tempArray[6]);
                etudiant.setId(id);
                id++;
                data.add(etudiant);
            }
            System.out.println(dictUniversity.toString());
             series1.setName("Regroupement de formations ou établissement");
             for (Map.Entry<String, Integer> entry: dictUniversity.entrySet()){
        series1.getData().add(new XYChart.Data(entry.getValue(),entry.getKey()));
             }
        
        barFormation.getData().addAll(series1);

        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(BarchartFormationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BarchartFormationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
}
