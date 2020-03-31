/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import entrega.pkg1.*;
import DBAcess.ClubDBAccess;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Booking;
import model.Club;
import model.Court;
import model.LocalTimeAdapter;
import model.Member;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import model.LocalDateAdapter;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class ReservasController implements Initializable {
    
    ClubDBAccess clubDBAcess;
    @FXML
    private Button fxCambia;
    @FXML
    private TableView<Booking> tableViewP;
    @FXML
    private TableColumn<Booking, Court > pistasDisponibles;
    @FXML
    private TableColumn<Booking, String> pistasReservadas;
    @FXML
    private TableColumn<Booking, Number> horaReserva;
    @FXML
    private TableColumn<Booking, Number> fechaReserva;
    @FXML
    private TableColumn<Booking, String> fxMember;
    
    /**
     * Initializes the controller class.
     */
    
    ObservableList<Booking> observableBookings;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        LocalDate reservaD = LocalDate.of(2020, 3,19);
        LocalTime reservaT = LocalTime.of(14,0);
       clubDBAcess.getBookings().add(new Booking(null,reservaD,reservaT,true,null,null));
       
        //inicializamos la tabla principal
        observableBookings = FXCollections.observableList(clubDBAcess.getBookings());
        tableViewP.setItems(observableBookings);
        tableViewP.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
       
       TableColumn<Booking, Court> pDisponibles = new TableColumn<>("Número de la pista");
       pDisponibles.setCellValueFactory(new PropertyValueFactory("court"));
       TableColumn<Booking, String> pReservadas = new TableColumn<>("Número de la pista reservada");
         pReservadas.setCellValueFactory(new PropertyValueFactory("court"));
       TableColumn<Booking, Number> hReserva = new TableColumn<>("Hora de la reserva");
       hReserva.setCellValueFactory(new PropertyValueFactory("fromHour"));
       TableColumn<Booking, Number> fReserva = new TableColumn<>("Fecha de la reserva");
       fReserva.setCellValueFactory(new PropertyValueFactory("bookingDate"));
       TableColumn<Booking, String> miembro = new TableColumn<>("Cliente que ha hecho la reserva");
       miembro.setCellValueFactory(new PropertyValueFactory("member"));
        //pistasDisponibles.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCourt()));
        //pistasReservadas.setCellValueFactory(e-> new SimpleStringProperty(()e.getValue().()));

        //que tiene cada columna
       
        tableViewP.getColumns().addAll(pDisponibles, pReservadas, hReserva, fReserva, miembro);
      
    }    

    
    
    @FXML
    private void cambiaReserva(ActionEvent event) {
    }
    
}
