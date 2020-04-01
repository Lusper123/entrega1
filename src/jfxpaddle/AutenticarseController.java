/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.Display;
import DBAcess.ClubDBAccess;
import entrega.pkg1.Auth;
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
import javafx.scene.layout.BorderPane;
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
    
    

    @FXML
     private void entraYa(ActionEvent event) throws IOException {
         String username = fxLog.getText().trim();
         String password = fxPass.getText();

        if (username.isEmpty() && password.isEmpty()) {
            Display.error(fxPass, "El usuario o contraseña no coinciden, intentelo de nuevo");
            return;
        }
        if (password.isEmpty()) {
            Display.error(fxPass, "La contraseña no coincide , intentelo de nuevo");
            return;
        }
        if (username.isEmpty()) {
            Display.error(fxLog, "El usuario no coincide , intentelo de nuevo");
            return;
        }

        Member member = clubDBAccess.getMemberByCredentials(username, password);
        if (member == null || !member.checkLogin(username)) {
            Display.error(fxLog, "El usuario o password no coincide , intentelo de nuevo");
            return;
        }
        Auth.login(member);
        Display.setTitle("AppPadel");
        Display.setView(getClass(), "appPadel.fxml");
    }


    @FXML
    private void compruebaLog(ActionEvent event) {
    }

    @FXML
    private void compruebaPass(ActionEvent event) {
    }
    
     public void closeWindows(){
        System.exit(0);
     }
}
