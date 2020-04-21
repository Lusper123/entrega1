package jfxpaddle;

import DBAcess.ClubDBAccess;
import entrega.pkg1.ActualCourt;
import entrega.pkg1.ActualTime;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Court;

/**
 * FXML Controller class
 *
 * @author Moni
 */
public class CambioReservaController implements Initializable {
    private Stage primaryStage;
    ClubDBAccess clubDBAccess;
    protected Stage stage;
    private Booking book;
    
   
    
    private String pista;

    private ObservableList<Booking> nuevo;
   
    @FXML
    private Button fxSave;
    @FXML
    private Button fxCancela;
    
   
    @FXML
    private RadioButton nueveH;
    @FXML
    private RadioButton diezMediaH;
    @FXML
    private RadioButton doceH;
    @FXML
    private RadioButton unaymediaH;
    @FXML
    private RadioButton tresH;
    @FXML
    private RadioButton cuatroymediaH;
    @FXML
    private RadioButton seisH;
    @FXML
    private RadioButton sieteymediaH;
    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    
     Map<LocalTime, RadioButton> hours = new HashMap<>();
    @FXML
    private RadioButton nueveNH;
    @FXML
    private RadioButton pista1;
    @FXML
    private RadioButton pista2;
    @FXML
    private RadioButton pista3;
    @FXML
    private RadioButton pista4;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
      stage = new Stage();
     
         disableHours();
         ToggleGroup g = new ToggleGroup();
        ToggleGroup b = new ToggleGroup();

        hours.put(LocalTime.of(9, 00), nueveH);
        hours.put(LocalTime.of(10, 30), diezMediaH);
        hours.put(LocalTime.of(12, 00), doceH);
        hours.put(LocalTime.of(13, 30), unaymediaH);
        hours.put(LocalTime.of(15, 00), tresH);
        hours.put(LocalTime.of(16, 30), cuatroymediaH);
        hours.put(LocalTime.of(18, 0), seisH);
        hours.put(LocalTime.of(19, 30), sieteymediaH);
        hours.put(LocalTime.of(21, 0), nueveNH);

        this.nueveH.setToggleGroup(b);
        this.diezMediaH.setToggleGroup(b);
        this.doceH.setToggleGroup(b);
        this.unaymediaH.setToggleGroup(b);
        this.tresH.setToggleGroup(b);
        this.cuatroymediaH.setToggleGroup(b);
        this.seisH.setToggleGroup(b);
        this.sieteymediaH.setToggleGroup(b);
        this.nueveNH.setToggleGroup(b);
        
         this.pista1.setToggleGroup(g);
        this.pista2.setToggleGroup(g);
        this.pista3.setToggleGroup(g);
        this.pista4.setToggleGroup(g);
        
     final BooleanBinding missingData
                = pista1.selectedProperty().not().and(pista2.selectedProperty().not().and(pista3.selectedProperty().not().and(pista4.selectedProperty().not()))).or(datePicker.valueProperty().isNull())
                        .or(nueveH.selectedProperty().not().and(diezMediaH.selectedProperty().not().and(doceH.selectedProperty().not().and(unaymediaH.selectedProperty().not()
                        .and(tresH.selectedProperty().not().and(cuatroymediaH.selectedProperty().not().and(seisH.selectedProperty().not().and(sieteymediaH.selectedProperty().not().and(nueveNH.selectedProperty().not())))))))));
    
         fxSave.disableProperty().bind(missingData);
       
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
     private void disableHours(){
        if (LocalDate.now().equals(datePicker.getValue())){
        if (LocalTime.now().isAfter(LocalTime.of(9, 00))){
            nueveH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(10, 30))){
            diezMediaH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(12, 00))){
            doceH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(13, 30))){
            unaymediaH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(15, 00))){
            tresH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(16, 30))){
            cuatroymediaH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(18, 00))){
            seisH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(19, 30))){
            sieteymediaH.setDisable(true);
        }
        if (LocalTime.now().isAfter(LocalTime.of(21, 00))){
            nueveNH.setDisable(true);
        }
        }
    }
    
    private void enableBookedHours() {
        for (Map.Entry<LocalTime, RadioButton> hour : hours.entrySet()) {
            RadioButton button = hour.getValue();
            button.setDisable(false);
        }
    }
 private void disableBookedHour(Booking booking) {
        RadioButton hourButton = hours.get(booking.getFromTime());
       
        hourButton.setDisable(true);
    }
 
 
    @FXML
    private void guardarCambio(ActionEvent event) {
         disableHours();
       
        if (book != null) {
           
            book.setBookingDate(LocalDateTime.now());
            book.setMadeForDay(datePicker.getValue());
            book.setCourt(new Court(pista));
            book.setFromTime(ActualTime.get());
            book.setBookingDate(book.getBookingDate());
           // meterReservas(pista);
        } 
         stage.close();    
        clubDBAccess.saveDB();                         
      
    }
    

    @FXML
    private void cancelarCambio(ActionEvent event) {
     
       stage.close();
    }
       public static CambioReservaController init(Modality modality, Stage owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CambioReservaController.class.getResource("/jfxpaddle/cambioReserva.fxml"));
        Parent parent = fxmlLoader.load();
        CambioReservaController controller = fxmlLoader.getController();
        controller.stage.setScene(new Scene(parent));
        controller.stage.initModality(modality);
        controller.stage.initOwner(owner);
        return controller;
    }
      
        public Booking showAndWait(Booking book) {
        this.book = book;
        LocalDate now = LocalDate.now();
       
        stage.showAndWait();
        return this.book;
    }

    public Booking showAndWait() {
        this.book = null;
        stage.showAndWait();
        return book;
    }

    @FXML
    private void p1(ActionEvent event) {
         meterReservas("Pista 1");
        ActualCourt.setActualCourt(new Court("Pista 1"));
    }

    @FXML
    private void p2(ActionEvent event) {
         meterReservas("Pista 2");
        ActualCourt.setActualCourt(new Court("Pista 2"));
    }

    @FXML
    private void p3(ActionEvent event) {
         meterReservas("Pista 3");
        ActualCourt.setActualCourt(new Court("Pista 3"));
    }

    @FXML
    private void p4(ActionEvent event) {
         meterReservas("Pista 4");
        ActualCourt.setActualCourt(new Court("Pista 4"));
    }

    @FXML
    private void manejoFecha(ActionEvent event) {
         meterReservas();
    }
    
     private void meterReservas(String pista) {
        this.pista = pista;
        meterReservas();
    }
    private void meterReservas() {
        enableBookedHours();
        ActualTime.setActualTime(null);
        ClubDBAccess clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ArrayList<Booking> r = clubDBAccess.getBookings();
        nuevo = FXCollections.observableArrayList();
       
        for (int i = 0; i < r.size(); i++) {
            Booking booking = r.get(i);
            if (booking.getCourt().getName().equals(pista)
                    && booking.getMadeForDay().equals(datePicker.getValue())) {
                nuevo.add(r.get(i));
                disableBookedHour(booking);
            }
        }
       disableHours();
    }

    @FXML
    private void nueveM(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(9, 00));
    }

    @FXML
    private void diezymediaM(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(10, 30));
    }

    @FXML
    private void doceM(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(12, 00));
    }

    @FXML
    private void unaymediaM(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(13, 30));
    }

    @FXML
    private void tresM(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(15, 00));
    }

    @FXML
    private void cuatroymediaT(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(16, 30));
    }

    @FXML
    private void seisT(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(18, 00));
    }

    @FXML
    private void sieteymediaT(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(19, 30));
    }

    @FXML
    private void nueveN(ActionEvent event) {
         ActualTime.setActualTime(LocalTime.of(21, 00));
    }

   
   }