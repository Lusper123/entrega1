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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class AppPadelController implements Initializable {
     protected Stage stage;
    @FXML
    private Label status;
    @FXML
    private ImageView imageUser;
    ClubDBAccess clubDBAcces;
    @FXML
    private MenuItem miPerf;
    @FXML
    private MenuItem fxCierraSesion;
    @FXML
    private MenuItem fxHelp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        imageUser.setImage(Auth.user().getImage());
        miPerf.setText("Mi perfil: " + Auth.user().getName());
        
    }    
    
    
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException{
      
    
        Display.setView(getClass(), "/jfxpaddle/InicioApp.fxml");
         
    }

    @FXML
    private void cierraApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void normasReserva(ActionEvent event) throws IOException {
        
             Display.setMinWH(550, 400);
            Display.showWindow(getClass(), "horariosyreservas.fxml");
           
       
    }

    @FXML
    private void myBookings(ActionEvent event) throws IOException {
         Display.setMinWH(550, 550);
         Display.setView(getClass(), "/vista/MisReservas.fxml");
      
    }

    @FXML
    private void ayuda(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("The application about paddel club");
        alert.setHeaderText("About this programm");
        alert.showAndWait();
    }
    
    public void closeWindows(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Vas a salir de la aplicación");
        alert.setContentText("¿Seguro que quieres salir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            status.getScene().getWindow().hide();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
     }


    @FXML
    private void reservar(ActionEvent event) throws IOException {
         Display.setMinWH(550, 550);
        Display.setView(getClass(), "/vista/Reservar.fxml");
        
    
    }

    @FXML
    private void myListBooks(ActionEvent event) throws IOException {
         Display.setMinWH(550, 550);
        Display.setView(getClass(), "/vista/myBookList.fxml");
       
    }

    @FXML
    private void verMiPerfil(ActionEvent event) throws IOException {
               
 Display.setMinWH(550, 550);           
 Display.showWindow(getClass(), "/vista/miPerfilapp.fxml");
            
          
    }

   
}
