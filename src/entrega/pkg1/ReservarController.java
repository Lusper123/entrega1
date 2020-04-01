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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class ReservarController implements Initializable {

    @FXML
    private MenuItem closeButton;
    @FXML
    private ImageView pista1;
    @FXML
    private Button boton1;
    @FXML
    private ImageView pista11;
    @FXML
    private ImageView pista12;
    @FXML
    private ImageView pista13;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private MenuItem closeButton1;
    @FXML
    private DatePicker dpBookingDay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boton1.setOpacity(0.0);
        boton2.setOpacity(0.0);
        boton3.setOpacity(0.0);
        boton4.setOpacity(0.0);
        
    }

    private void disponibilidad(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/VerDisponibilidad.fxml");
        Display.setTitle("Disponibilidad de las pistas");
    }

    @FXML
    private void misreservas(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/MisReservas.fxml");
        Display.setTitle("Mis reservas");
    }

    @FXML
    private void cierraApp(ActionEvent event) {
    }

    @FXML
    private void boton1(ActionEvent event) throws IOException {
        Display.showWindow(getClass(), "/vista/ficha_reserva.fxml");
       
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/jfxpaddle/appPadel.fxml");
        Display.setTitle("Mis reservas");
    }

    @FXML
    private void datePicker(ActionEvent event) {
        dpBookingDay.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });
    }

}
