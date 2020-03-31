/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;
import jfxpaddle.AppPadelController;
import jfxpaddle.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class ReservarController implements Initializable {

    @FXML
    private MenuButton menufxID;
    @FXML
    private MenuItem disponibilidad;
    @FXML
    private MenuItem ver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void disponibilidad(ActionEvent event) throws IOException {
          Display.setView(getClass(), "/vista/VerDisponibilidad.fxml");
          Display.setTitle("Disponibilidad de las pistas");
    }


    @FXML
    private void misreservas(ActionEvent event) throws IOException {
         Display.setView(getClass(), "/vista/MisReservas.fxml");
         Display.setTitle("Mis reservas");
    }


}
