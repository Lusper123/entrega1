/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.Display;
import entrega.pkg1.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class AppPadelController implements Initializable {

    @FXML
    private MenuItem fxMiBook;
    @FXML
    private MenuItem fxList;
    @FXML
    private MenuItem closeButton;
    @FXML
    private MenuItem closeButton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cierraApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void myBookings(ActionEvent event) throws IOException {
         Display.setView(getClass(), "/vista/MisReservas.fxml");
        Display.setTitle("Mis reservas");
    }


    @FXML
    private void muestraBookings(ActionEvent event)throws IOException {
          Display.setView(getClass(), "/vista/Reservar.fxml");
          Display.setTitle("Reservar pista");
    }
    
}
