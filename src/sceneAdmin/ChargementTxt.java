/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneAdmin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author thaja
 */
public class ChargementTxt {
    String url;
    ObservableList<Etudiant> data = FXCollections.observableArrayList();

    public ObservableList<Etudiant> getData() {
        return data;
    }

    public void setData(ObservableList<Etudiant> data) {
        this.data = data;
    }

    public ChargementTxt(String url) {
        this.url = url;
    }
    

    public void chargerTxt() throws FileNotFoundException, IOException{
        String line;
        int id = 0;
        String pathToFile = url;
            BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), StandardCharsets.UTF_8));
            System.out.println("Print File Found");
            
            while((line = br.readLine())!=null){
                Etudiant etudiant = new Etudiant();
                String[] tempArray = line.split(";");
                if(tempArray.length>=9){
                etudiant.setRentree_Universitaire(tempArray[0]);
                etudiant.setLocalisation(tempArray[1]);
                etudiant.setRgp_formation(tempArray[2]);
                etudiant.setSecteur(tempArray[3]);
                etudiant.setSexe(tempArray[4]);
                etudiant.setNom(tempArray[5]);
                etudiant.setPrenom(tempArray[6]);
                etudiant.setContact(tempArray[7]);
                etudiant.setEmail(tempArray[8]);
                etudiant.setId(id);
                }
                id++;
                data.add(etudiant);
                
        
        
    }
            
    }

   
    
}
