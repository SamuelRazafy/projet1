/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneAdmin;

import sceneUser.*;
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
import javafx.scene.chart.PieChart;
import sceneAdmin.Etudiant;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 7
 */
public class PieChart2Controller implements Initializable {

    @FXML
    private PieChart pieSecteur;

    /**
     * Initializes the controller class.
     */
    
     public void testStat(){
         Map<String, Integer> dictSecteur = new HashMap<>();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        String line;
        int id = 0;
        
        try{
            String pathToFile = "src\\sceneAdmin\\donneeProjet.txt";
            BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), StandardCharsets.UTF_8));
            System.out.println("Print File Found");
            
            ObservableList<Etudiant> data = FXCollections.observableArrayList(
            );
            int nbrLoc=0;
            while((line = br.readLine())!=null){
                Etudiant etudiant = new Etudiant();
                String[] tempArray = line.split(";");
                
                etudiant.setRentree_Universitaire(tempArray[0]);
                etudiant.setLocalisation(tempArray[1]);
                
                
                etudiant.setRgp_formation(tempArray[2]);
                
                etudiant.setSecteur(tempArray[3]);
                
                if(dictSecteur.containsKey(tempArray[3])){
                    int frequence = dictSecteur.get(tempArray[3]);
                    frequence++;
                    dictSecteur.put(tempArray[3], frequence);
                }else{
                    dictSecteur.put(tempArray[3], 1);
                }
                etudiant.setSexe(tempArray[4]);
                
                
                etudiant.setNom(tempArray[5]);
                etudiant.setPrenom(tempArray[6]);
                etudiant.setId(id);
                id++;
                data.add(etudiant);
            }
            
            
            
            for (Map.Entry<String, Integer> entry: dictSecteur.entrySet()){
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
            
            pieSecteur.getData().addAll(pieChartData);
            
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(PieChart2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PieChart2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        testStat();
    }    
    
}
