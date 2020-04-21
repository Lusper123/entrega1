/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxpaddle.HorariosyreservasController;
import model.Booking;
import model.Court;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class ReservarController implements Initializable {

   

    @FXML
    private MenuItem closeButton;
    @FXML
    private MenuItem closeButton2;
    @FXML
    private MenuItem closeButton3;
    @FXML
    private MenuItem closeButton1;
    @FXML
    private Button res_boton;

    @FXML
    private ImageView imageUser;
    @FXML
    private MenuItem miPerfil;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton p1;
    @FXML
    private RadioButton p4;
    @FXML
    private RadioButton p2;
    @FXML
    private RadioButton p3;
    ClubDBAccess clubDBAccess;

    private ObservableList<Booking> bookings;
    private String pista;

    private ObservableList<Booking> nuevo;
    private ObservableList<String> miau;

    LocalTime localTime2 = LocalTime.of(10, 30);
    @FXML
    private ListView<Booking> lista;
    @FXML
    private Text prueba;
    @FXML
    private RadioButton nueve1;
    @FXML
    private RadioButton diez_media1;
    @FXML
    private RadioButton doce1;
    @FXML
    private RadioButton una_media1;
    @FXML
    private RadioButton tres1;
    @FXML
    private RadioButton cuatro_media1;
    @FXML
    private RadioButton seis1;
    @FXML
    private RadioButton siete_media1;
    @FXML
    private RadioButton nueve_noche1;

    Map<LocalTime, RadioButton> hours = new HashMap<>();
    @FXML
    private ListView<String> lista2;
    @FXML
    private Text disp;

    private void iniciar() {
        ActualTime.setActualTime(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        imageUser.setImage(Auth.user().getImage());
        prueba.setText("");
        disp.setText("");
        miPerfil.setText("Mi perfil: " + Auth.user().getName());
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        
        
        hours.put(LocalTime.of(9, 00), nueve1);
        hours.put(LocalTime.of(10, 30), diez_media1);
        hours.put(LocalTime.of(12, 00), doce1);
        hours.put(LocalTime.of(13, 30), una_media1);
        hours.put(LocalTime.of(15, 00), tres1);
        hours.put(LocalTime.of(16, 30), cuatro_media1);
        hours.put(LocalTime.of(18, 0), seis1);
        hours.put(LocalTime.of(19, 30), siete_media1);
        hours.put(LocalTime.of(21, 0), nueve_noche1);
       ToggleGroup b = new ToggleGroup();
        this.nueve1.setToggleGroup(b);
        this.diez_media1.setToggleGroup(b);
        this.doce1.setToggleGroup(b);
        this.una_media1.setToggleGroup(b);
        this.tres1.setToggleGroup(b);
        this.cuatro_media1.setToggleGroup(b);
        this.seis1.setToggleGroup(b);
        this.siete_media1.setToggleGroup(b);
        this.nueve_noche1.setToggleGroup(b);
        ToggleGroup g = new ToggleGroup();
        this.p1.setToggleGroup(g);
        this.p2.setToggleGroup(g);
        this.p3.setToggleGroup(g);
        this.p4.setToggleGroup(g);
        bookings = FXCollections.observableList(clubDBAccess.getBookings());
        iniciar();
        final BooleanBinding missingData
                = p1.selectedProperty().not().and(p2.selectedProperty().not().and(p3.selectedProperty().not().and(p4.selectedProperty().not()))).or(datePicker.valueProperty().isNull())
                        .or(nueve1.selectedProperty().not().and(diez_media1.selectedProperty().not().and(doce1.selectedProperty().not().and(una_media1.selectedProperty().not()
                        .and(tres1.selectedProperty().not().and(cuatro_media1.selectedProperty().not().and(seis1.selectedProperty().not().and(siete_media1.selectedProperty().not().and(nueve_noche1.selectedProperty().not())))))))));
    
         res_boton.disableProperty().bind(missingData);
        datePicker.setDayCellFactory((DatePicker picker) -> {
                return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
            setDisable(empty || date.compareTo(today) < 0 );
            if(date.getDayOfWeek() == DayOfWeek.SUNDAY ){ setDisable(true); setStyle("-fx-background-color: #ffc0cb");}
                    }
                };
                });
    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/confirmar.fxml"));
        Parent root = miCargador.load();

        ConfirmarController controlador = miCargador.<ConfirmarController>getController();
        controlador.initPersona();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Confirmar acción");
        disableHours();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if (ActualTime.get().equals(null)) {
            Display.error("Introduzca una hora");
        } else {
            if (controlador.getAceptar()) {
                Booking b = new Booking();
                if ((Auth.user().checkHasCreditInfo())) {
                    b.setPaid(Boolean.TRUE);
                } else {
                    b.setPaid(Boolean.FALSE);
                }
                
                clubDBAccess.getBookings().add(b);
                b.setBookingDate(LocalDateTime.now());
                b.setMadeForDay(datePicker.getValue());
                b.setCourt(new Court(pista));
                b.setMember(Auth.user());
                b.setFromTime(ActualTime.get());
                b.setBookingDate(b.getBookingDate());
                meterReservas(pista);
            }
            clubDBAccess.saveDB();
        }
    }

   

    @FXML
    private void handleFecha(ActionEvent event) {
        meterReservas();
    }

    @FXML
    private void pis1(ActionEvent event) {
        meterReservas("Pista 1");
        ActualCourt.setActualCourt(new Court("Pista 1"));

    }

    @FXML
    private void pis2(ActionEvent event) {
        meterReservas("Pista 2");
        ActualCourt.setActualCourt(new Court("Pista 2"));
    }

    @FXML
    private void pis3(ActionEvent event) {
        meterReservas("Pista 3");
        ActualCourt.setActualCourt(new Court("Pista 3"));
    }

    @FXML
    private void pis4(ActionEvent event) {
        meterReservas("Pista 4");
        ActualCourt.setActualCourt(new Court("Pista 4"));
    }

    private void meterReservas(String pista) {
        this.pista = pista;
        meterReservas();
    }
    
    private void disableHours(){
        if (LocalDate.now().equals(datePicker.getValue())){
        if (LocalTime.now().isAfter(LocalTime.of(9, 00))){
            nueve1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(10, 30))){
            diez_media1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(12, 00))){
            doce1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(13, 30))){
            una_media1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(15, 00))){
            tres1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(16, 30))){
            cuatro_media1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(18, 00))){
            seis1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(19, 30))){
            siete_media1.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(21, 00))){
            nueve_noche1.setDisable(true);
        }
        }
    }
    
    private void meterReservas() {
        enableBookedHours();
        ActualTime.setActualTime(null);
        nuevo = FXCollections.observableArrayList();
        prueba.setText("Horas reservadas para la " + pista.toLowerCase());
        disp.setText("Disponibles");
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.getCourt().getName().equals(pista)
                    && booking.getMadeForDay().equals(datePicker.getValue())) {
                nuevo.add(bookings.get(i));
                disableBookedHour(booking);
                
            }
        }
        disponible();
        lista.setItems(nuevo);
        lista.setCellFactory(c -> new ListCellBooking());
        disableHours();
    }

    private void enableBookedHours() {
     
        for (Map.Entry<LocalTime, RadioButton> hour : hours.entrySet()) {
            RadioButton button = hour.getValue();
            button.setDisable(false);
               
    }
    }
    
    private void disponible(){
         ToggleGroup g = new ToggleGroup();
        this.p1.setToggleGroup(g);
        this.p2.setToggleGroup(g);
        this.p3.setToggleGroup(g);
        this.p4.setToggleGroup(g);
        String s="";
        miau = FXCollections.observableArrayList();
        if (datePicker.getValue() != null && g.getSelectedToggle() != null){
             if (!nueve1.isDisabled()){
                     s = ("Disponible   " + nueve1.getText());
                     miau.add(s);
                    }
             if (!diez_media1.isDisabled()){
                     s = ("Disponible   " + diez_media1.getText());
                     miau.add(s);
                    } 
             if (!doce1.isDisabled()){
                     s = ("Disponible   " + doce1.getText());
                     miau.add(s);
                    } 
             if (!una_media1.isDisabled()){
                     s = ("Disponible   " + una_media1.getText());
                     miau.add(s);
                    }
             if (!tres1.isDisabled()){
                     s = ("Disponible   " + tres1.getText());
                     miau.add(s);
                    }   
             if (!cuatro_media1.isDisabled()){
                     s = ("Disponible   " + cuatro_media1.getText());
                     miau.add(s);
                    }   
             if (!seis1.isDisabled()){
                     s = ("Disponible   " + seis1.getText());
                     miau.add(s);
                    }   
             if (!siete_media1.isDisabled()){
                     s = ("Disponible   " + siete_media1.getText());
                     miau.add(s);
                    }  
             if (!nueve_noche1.isDisabled()){
                     s = ("Disponible   " + nueve_noche1.getText());
                     miau.add(s);
                    }  
            }
        lista2.setItems(miau);
        }
        
    

    private void disableBookedHour(Booking booking) {
        RadioButton hourButton = hours.get(booking.getFromTime());
        hourButton.setDisable(true);
        
    }

    @FXML
    private void nueve(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(9, 00));
    }

    @FXML
    private void diez_media(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(10, 30));
    }

    @FXML
    private void doce(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(12, 00));
    }

    @FXML
    private void una_media(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(13, 30));
    }

    @FXML
    private void tres(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(15, 00));
    }

    @FXML
    private void cuatro_media(ActionEvent event) {

        ActualTime.setActualTime(LocalTime.of(16, 30));
    }

    @FXML
    private void seis(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(18, 00));
    }

    @FXML
    private void siete_media(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(19, 30));
    }

    @FXML
    private void nueve_noche(ActionEvent event) {
        ActualTime.setActualTime(LocalTime.of(21, 00));
    }

    @FXML
    private void miUser(ActionEvent event)  throws IOException {
            Display.showWindow(getClass(), "/vista/miPerfilapp.fxml");
             Display.setMinWH(550, 400);
    }

    class ListCellBooking extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking item, boolean empty) {
            super.updateItem(item, empty);
            LocalDate now = LocalDate.now();
            if (item == null || empty) {
                setText(null);
            } else {
            setText("Reservada   " + item.getCourt().getName() + "    " + item.getFromTime() + "    " + item.getMember().getLogin());
            }
        }

    }
    

    private void disponibilidad(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/VerDisponibilidad.fxml");
       
    }

    @FXML
    private void misreservas(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/MisReservas.fxml");
        
    }

    @FXML
    private void cierraApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/jfxpaddle/appPadel.fxml");
       
    }

    @FXML
    private void normasReserva(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/jfxpaddle/horariosyreservas.fxml"));
            Parent root1 = fxmlLoader.load();
            HorariosyreservasController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);

            stage.showAndWait();

        } catch (IOException e) {

        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {

        Display.setView(getClass(), "/jfxpaddle/InicioApp.fxml");

    }

    @FXML
    private void misPistas(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/myBookList.fxml");
       
    }

}
