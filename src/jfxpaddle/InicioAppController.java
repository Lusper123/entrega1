/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.*;
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
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class InicioAppController implements Initializable {

    @FXML
    private Button fxInicio;
    @FXML
    private Button fxRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accederPerfil(ActionEvent event) {
        
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Autenticarse.fxml"));
            Parent root1 = fxmlLoader.load();
            AutenticarseController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Mi perfil");
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeWindows());
            Stage mystage = (Stage) this.fxInicio.getScene().getWindow();  
            mystage.close();
        }catch(IOException e){
             //Logger.getLogger(AutenticarseController.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    @FXML
    private void crearCuenta(ActionEvent event) {
        
          try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoPerfil.fxml"));
            Parent root1 = fxmlLoader.load();
            NuevoPerfilController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Mi perfil");
            stage.setScene(scene);
            stage.show();
            
           
        }catch(IOException e){
            // Logger.getLogger(AutenticarseController.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
}
