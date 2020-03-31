/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.Display;
import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class AutenticarseController implements Initializable {
    ClubDBAccess clubDBAccess;
    Member member;
    @FXML
    private Button fxCancela;
    @FXML
    private Button fxEntry;
    @FXML
    private TextField fxLog;
    @FXML
    private TextField fxPass;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
         clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
         clubDBAccess.getMembers();
        // TODO
    }    

    @FXML
    private void cierraVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void entraYa(ActionEvent event) throws IOException {
        String username = fxLog.getText().trim();
        String password = fxPass.getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //Pattern pattern = Pattern.compile("([a-z0-9_\\-\\.])+\\@([a-z0-9_\\-\\.])+\\.([a-z]{2,4})$");
        
        if (username.isEmpty() && password.isEmpty()) {
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El usuario o contraseña no coinciden, intentelo de nuevo");
            alert.showAndWait();  
            fxLog.requestFocus();

        } else if (password.isEmpty()) {
             alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("la contraseña no coincide , intentelo de nuevo");
            alert.showAndWait();  
            fxPass.requestFocus();
        } else if (username.isEmpty()) {
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("el usuario no coincide , intentelo de nuevo");
            alert.showAndWait();
            fxLog.requestFocus();
      
        } else if(clubDBAccess.getMemberByCredentials(username, password).checkLogin(username)){
              
            //Si todo va bien cargaria la nueva ventana 
             Display.setTitle("AppPadel");
        Display.setView(getClass(), "AppPadelController.fxml");
          
        }

    
    }

    @FXML
    private void compruebaLog(ActionEvent event) {
    }

    @FXML
    private void compruebaPass(ActionEvent event) {
    }
    
     public void closeWindows(){
     try{
           
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioApp.fxml"));
            Parent root1 = fxmlLoader.load();
            InicioAppController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
           
            stage.setScene(scene);
            stage.setTitle("Club padel");
            stage.setResizable(false);
            stage.show();
           Stage mystage = (Stage) this.fxEntry.getScene().getWindow();  
            mystage.close();        
        }catch(IOException e){
            Logger.getLogger(InicioAppController.class.getName()).log(Level.SEVERE,null,e);
        }
    }
}
