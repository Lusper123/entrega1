/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Court;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class ReservarController implements Initializable {

    @FXML
    private MenuItem closeButton;
    private ImageView pista111;
    @FXML
    private MenuItem closeButton1;
    @FXML
    private DatePicker dpBookingDay;
    @FXML
    private ImageView pista1;
    @FXML
    private ImageView pista2;
    @FXML
    private ImageView pista3;
    @FXML
    private ImageView pista4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    private void boton1(String pista) throws IOException {
        Display.showWindow(getClass(), "/vista/ficha_reserva.fxml");
        ActualCourt.setActualCourt(new Court(pista));
    }

    @FXML
    private void pistaUno(MouseEvent event) throws IOException {
        boton1("1");
    }

    @FXML
    private void pistaTres(MouseEvent event) throws IOException {
        boton1("3");

    }

    @FXML
    private void pistaDos(MouseEvent event) throws IOException {
        boton1("2");

    }

    @FXML
    private void pistaCuatro(MouseEvent event) throws IOException {
        boton1("4");
    }

}
