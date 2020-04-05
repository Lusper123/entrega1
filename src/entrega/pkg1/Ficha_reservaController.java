/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Court;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class Ficha_reservaController implements Initializable {

    @FXML
    private TableColumn<String, String> horario;
    @FXML
    private Button reservafxID;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> estado;
    @FXML
    private TableColumn<String, String> usuario;

    ClubDBAccess clubDBAccess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        Display.disableResizable();
        horario.setPrefWidth(190.0);
        horario.setResizable(false);
        estado.setPrefWidth(190.0);
        estado.setResizable(false);
        usuario.setPrefWidth(190.0);
        usuario.setResizable(false);

        Collection<String> list = new ArrayList<>();
        list.add("9:00 - 10:30");
        list.add("10:30 - 12:00");
        list.add("12:00 - 13:30");
        list.add("13:30 - 15:00");
        list.add("15:00 - 16:30");
        list.add("16:30 - 18:00");
        list.add("16:30 - 18:00");
        list.add("18:00 - 19:30");
        list.add("19:30 - 21:00");

        ObservableList<String> details = FXCollections.observableArrayList(list);
        table.setItems(details);
        table.getItems().addAll(list);
        
        horario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        ObservableList<String> estados = FXCollections.observableArrayList("Cosa simple");
        
    }

    @FXML
    private void boton_reserva(ActionEvent event) throws IOException {
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
            Booking b = new Booking();
            if ((Auth.user().checkHasCreditInfo())) {
                b.setPaid(Boolean.TRUE);
            } else {
                b.setPaid(Boolean.FALSE);
            }

            b.setCourt(ActualCourt.get());
            table.getSelectionModel().getSelectedItems();
            clubDBAccess.saveDB();
        }

    }

}
