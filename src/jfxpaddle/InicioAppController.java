/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;

import entrega.pkg1.Display;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class InicioAppController implements Initializable {

    @FXML
    private Button fxInicio;
    private Button fxAccede;
    @FXML
    private Button fxRegistro;
    @FXML
    private Button fxVerReservas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        
        // TODO
    }    

    @FXML
    private void accederPerfil(ActionEvent event) throws IOException {
        
   
       Display.setView(getClass(), "/jfxpaddle/Autenticarse.fxml");
        
        //cerrar la ventana de atrás
        Stage mystage = (Stage) this.fxInicio.getScene().getWindow();
        mystage.close();
       
    }

    

    @FXML
    private void crearCuenta(ActionEvent event) throws IOException {
        
        
       Display.setView(getClass(), "/jfxpaddle/nuevoPerfil.fxml");
        //cerrar la ventana de atrás
        Stage mystage = (Stage) this.fxRegistro.getScene().getWindow();
        mystage.close();
        
    
    }
    @FXML
    private void accederReserva(ActionEvent event) throws IOException  {
           Display.setView(getClass(), "/vista/ReservarSinLogin.fxml");
           Stage mystage = (Stage) this.fxVerReservas.getScene().getWindow();  
         mystage.close();
    }


}
