/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;

import DBAcess.ClubDBAccess;
import entrega.pkg1.Auth;
import entrega.pkg1.Display;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class AutenticarseController implements Initializable {
    //protected Stage stage;
     private SimpleBooleanProperty showPassword ;
     ClubDBAccess clubDBAccess;
     Member member;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button fxCancela;
    @FXML
    private Button fxEntry;
    @FXML
    private TextField fxLog;
    @FXML
    private PasswordField fxPassword;
    
    /**
     * Initializes the controller class.
     */
    
     private final String ERROR_USUARI = "El usuario no és vàlid";
    private final String ERROR_CONTRASENA = "La contraseña no es correcta";
    private boolean password_was_good = true;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Display.setMinWH(550, 550);
         clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
         clubDBAccess.getMembers();
        // TODO
              
           fxLog.textProperty().addListener((observable,oldValue,newValue)->{
         fxLog.setText(fxLog.getText().replace(" ", "")); });
           
            fxPassword.textProperty().addListener((observable,oldValue,newValue)->{
         fxPassword.setText(fxPassword.getText().replace(" ", "")); });
    }  
      
    public void handleLoginChange(InputMethodEvent inputMethodEvent) {
        System.out.println(inputMethodEvent);
    }

    @FXML
    private void muestraContraseña(ActionEvent event) {
         if (checkBox.isSelected()){
                fxPassword.setPromptText(fxPassword.getText());
                fxPassword.setText(""); 
                fxPassword.setDisable(true);

        
            }else {
                fxPassword .setText(fxPassword.getPromptText());
                fxPassword.setPromptText("");
                fxPassword.setDisable(false);
    }
    }

    @FXML
    private void cierraVentana(ActionEvent event) throws IOException {
        
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("InicioApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        //cerrar la ventana de atrás
        Stage mystage = (Stage) this.fxCancela.getScene().getWindow();
        mystage.close();
        
    }

    @FXML
    private void entraYa(ActionEvent event)  throws IOException {
         String username = fxLog.getText().trim();
         String password = fxPassword.getText();

        if (username.isEmpty() && password.isEmpty()) {
            Display.error(fxPassword, "El usuario o contraseña no coinciden, intentelo de nuevo");
            return;
        }
        if (password.isEmpty()) {
            Display.error(fxPassword, "La contraseña no coincide , intentelo de nuevo");
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
       
         Display.setMinWH(550, 550);
        Display.setView(getClass(), "appPadel.fxml");
        Stage stage = new Stage();
       
        Stage mystage = (Stage) this.fxEntry.getScene().getWindow();  
        mystage.close();
    }
    

    
     public void closeWindows(){
        System.exit(0);
     }
     
    }

   
    

