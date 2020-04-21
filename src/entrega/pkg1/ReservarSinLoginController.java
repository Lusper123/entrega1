package entrega.pkg1;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Booking;

/**
 * FXML Controller class
 *
 * @author Moni
 */
public class ReservarSinLoginController implements Initializable {

    @FXML
    private AnchorPane fxPistas;
    @FXML
    private Label status;
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
    @FXML
    private ListView<Booking> listViewReservas;
    private ObservableList<Booking> pistasReservadas;
    private ObservableList<Booking> nuevo;
    private String pista;

    ClubDBAccess clubDBAccess;
    @FXML
    private Text prueba;
    @FXML
    private RadioButton todas;
    @FXML
    private Button volverInicio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ToggleGroup g = new ToggleGroup();
        this.p1.setToggleGroup(g);
        this.p2.setToggleGroup(g);
        this.p3.setToggleGroup(g);
        this.p4.setToggleGroup(g);
        this.todas.setToggleGroup(g);
        Display.setMinWH(550, 550);
        pistasReservadas = FXCollections.observableList(clubDBAccess.getBookings());
        reverse(pistasReservadas);
        listViewReservas.setItems(pistasReservadas);
        listViewReservas.setCellFactory(c -> new ListCellBooking());
        
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
          prueba.setText("Horas reservadas para todas las pistas");
    }

    @FXML
    private void pis1(ActionEvent event) {
        meterReservas("Pista 1");
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/jfxpaddle/InicioApp.fxml");
        /* Stage mystage = (Stage) this.volverInicio.getScene().getWindow();  
         mystage.close();*/
    }

    

    @FXML
    private void pis4(ActionEvent event) {
        meterReservas("Pista 4");
    }

    @FXML
    private void pis2(ActionEvent event) {
        meterReservas("Pista 2");
    }

    @FXML
    private void pis3(ActionEvent event) {
        meterReservas("Pista 3");
    }

    @FXML
    private void handleFecha(ActionEvent event) {
        meterReservas();
    }

    @FXML
    private void reiniciarDate(ActionEvent event) {
        datePicker.setValue(null);
    }

    @FXML
    private void todasPistas(ActionEvent event) {
        listViewReservas.setItems(pistasReservadas);
        
        listViewReservas.setCellFactory(c -> new ListCellBooking());
        prueba.setText("Horas reservadas para todas las pistas");
    }


    class ListCellBooking extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking item, boolean empty) {
            super.updateItem(item, empty);
            LocalDate now = LocalDate.now();
            if (item == null || empty) {
                setText(null);
            } else {
                setText(item.getCourt().getName() + "  - "+ item.getMember().getLogin() + "  dia " + item.getMadeForDay().adjustInto(now) + "   de " + item.getFromTime() + " a " + item.getFromTime().plusHours(1).plusMinutes(30));
                  if(item.getMadeForDay().isBefore(LocalDate.now())) {
                        setDisable(true); setOpacity(0.40);
                    }    
                  if(item.getFromTime().isBefore(LocalTime.now()) && item.getMadeForDay().isEqual(now)){
                    setDisable(true); setOpacity(0.40);
                }
            }
        }
    }
 
    public ObservableList<Booking> reverse(ObservableList<Booking> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }
    
    private void meterReservas(String pista) {
        this.pista = pista;
        meterReservas();
    }

    private void meterReservas() {
        nuevo = FXCollections.observableArrayList();
        prueba.setText("Horas reservadas para la " + pista.toLowerCase());
        for (int i = 0; i < pistasReservadas.size(); i++) {
            if (pistasReservadas.get(i).getCourt().getName().equals(pista)) {
                if (datePicker.getValue() == null) {
                    nuevo.add(pistasReservadas.get(i));
                } else if (pistasReservadas.get(i).getMadeForDay().equals(datePicker.getValue())) {
                    nuevo.add(pistasReservadas.get(i));
                }
            }
        }
        listViewReservas.setItems(nuevo);
        listViewReservas.setCellFactory(c -> new ListCellBooking());
    }

}
