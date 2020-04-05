/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import DBAcess.ClubDBAccess;
import jfxpaddle.AutenticarseController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class MisReservasController implements Initializable {

    ClubDBAccess clubDBAccess;
    @FXML
    private TableView<Booking> tableView;

    private static ObservableList<Booking> data = null;

    public static void setData(ObservableList<Booking> observableArrayList) {
        data = observableArrayList;
    }
    @FXML
    private Button cancelar;
    @FXML
    private MenuItem closeButton;
    @FXML
    private MenuItem closeButton1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        cancelar.disableProperty()
                .bind(tableView.getSelectionModel().selectedItemProperty().isNull());

        List<Booking> bookings = clubDBAccess.getUserBookings(Auth.user().getLogin());

        TableColumn<Booking, Court> colPista = new TableColumn<>("Número de la pista");
        TableColumn<Booking, String> hora = new TableColumn<>("Dia de la reserva ");
        hora.setCellValueFactory(new PropertyValueFactory("madeForDay"));
        TableColumn<Booking, String> dia = new TableColumn<>("Hora inicio");
        dia.setCellValueFactory(new PropertyValueFactory("bookingDate"));
        colPista.setCellValueFactory(new PropertyValueFactory<Booking, Court>("court"));
        colPista.setCellFactory((TableColumn<Booking, Court> cell) -> new TableCell<Booking, Court>() {
            @Override
            protected void updateItem(Court item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        colPista.setPrefWidth(130.0);
        colPista.setResizable(false);
        hora.setPrefWidth(150.0);
        hora.setResizable(false);
        dia.setPrefWidth(130.0);
        dia.setResizable(false);

        tableView.getColumns().addAll(colPista, hora, dia);
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

        if (controlador.getAceptar()) {
            {
                data.removeAll(tableView.getSelectionModel().getSelectedItems());
                clubDBAccess.saveDB();
            }
        }
    }

    @FXML
    private void cierraApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/jfxpaddle/appPadel.fxml");
        Display.setTitle("Mis reservas");
    }

}
