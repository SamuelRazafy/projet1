/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sceneLogin;



import sceneAdmin.AnnuaireElectroniqu;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Inclusiv Academy 7
 */
public class FxmlLoader {
    private Pane view;
    
    public Pane getPage(String fileName) {
            try{
          URL fileUrl=AnnuaireElectroniqu.class.getResource("/sceneAdmin/"+fileName+".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view= new FXMLLoader().load(fileUrl);
            
             }catch(Exception e){
          System.out.println("no page"+fileName+"please check FxmlLoader");
      
            }
        
        return view; 
    }
    
    
}
