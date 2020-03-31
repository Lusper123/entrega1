/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.*;
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
    private MenuItem fxBooking;
    @FXML
    private MenuItem fxList;

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
    private void myBookings(ActionEvent event) {
        try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Main.fxml"));
            Parent root1 = fxmlLoader.load();
            MainController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lista de reservas del club");
            stage.setScene(scene);
            stage.setResizable(false);
            
            stage.showAndWait();
            
        }catch(IOException e){
            
        }
    }

    @FXML
    private void booking(ActionEvent event) {
    }

    @FXML
    private void muestraBookings(ActionEvent event) {
        try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reservas.fxml"));
            Parent root1 = fxmlLoader.load();
            ReservasController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lista de reservas del club");
            stage.setScene(scene);
            stage.setResizable(false);
            
            stage.showAndWait();
            
        }catch(IOException e){
            
        }
    }
    
}
