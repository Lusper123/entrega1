/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Court;
import jfxpaddle.JFXPaddle;
/**
 * FXML Controller class
 *
 * @author choco
 */
public class MisReservasController implements Initializable {

    ClubDBAccess clubDBAccess;
    @FXML
    private MenuButton menufxID;
    @FXML
    private MenuItem disponibilidad;
    @FXML
    private MenuItem reservar;
    @FXML
    private TableView<Booking> tableView;

    private static ObservableList<Booking> data = null;

    @FXML
    private Button cancel;

    
    public static void setData(ObservableList<Booking> observableArrayList) {
         data = observableArrayList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Test.initMisReservas();
        cancel.disableProperty()
                .bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        tableView.setItems(data);
        TableColumn<Booking, Court> numero = new TableColumn<>("Número de la pista");
        numero.setCellValueFactory(new PropertyValueFactory("court"));
        TableColumn<Booking, String> hora = new TableColumn<>("Dia de la reserva ");
        hora.setCellValueFactory(new PropertyValueFactory("madeForDay"));
        TableColumn<Booking, String> dia = new TableColumn<>("Hora inicio");
        dia.setCellValueFactory(new PropertyValueFactory("bookingDate"));

        tableView.getColumns().addAll(numero, hora,dia);
    }

    @FXML
    private void disponibilidad(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/VerDisponibilidad.fxml");
        Display.setTitle("Disponibilidad de las pistas");
    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/Reservar.fxml");
        Display.setTitle("Reservar pista");
    }

    @FXML
    private void cancelar_reserva(ActionEvent event) throws IOException {
        System.out.println(this);

        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/confirmar.fxml"));
        Parent root = miCargador.load();

        ConfirmarController controlador = miCargador.<ConfirmarController>getController();
        controlador.initPersona();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Confirmar acción");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        // ¿Se ha modificado algo?
        if (controlador.getAceptar()) {
            {
                data.removeAll(tableView.getSelectionModel().getSelectedItems());
            }
            
        }
    }

}
