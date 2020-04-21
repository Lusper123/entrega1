/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import DBAcess.ClubDBAccess;
import entrega.pkg1.Auth;
import entrega.pkg1.Display;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

/**
 * FXML Controller class
 *
 * @author monica
 */
public class MisReservasController implements Initializable {
 ClubDBAccess clubDBAccess;
    @FXML
    private MenuItem miUser;
    @FXML
    private ImageView imageUser;
    @FXML
    private MenuItem closeButton;
    @FXML
    private MenuItem closeButton12;
    @FXML
    private Label status;
    @FXML
    private Text prueba;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton p1;
    @FXML
    private RadioButton p2;
    @FXML
    private RadioButton p3;
    @FXML
    private RadioButton p4;
    @FXML
    private ListView<Booking> listViewReservas;
    private ObservableList<Booking> pistasReservadas;
      private ObservableList<Booking> nuevo;
     private String pista;
    @FXML
    private RadioButton todas;
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        Display.setMinWH(550, 550);
        ToggleGroup g = new ToggleGroup();
        this.p1.setToggleGroup(g);this.p2.setToggleGroup(g);this.p3.setToggleGroup(g);this.p4.setToggleGroup(g);this.todas.setToggleGroup(g);
        prueba.setText("Horas reservadas para todas las pistas");
        imageUser.setImage(Auth.user().getImage());
        miUser.setText("Mi perfil: " + Auth.user().getName());

        
        pistasReservadas = FXCollections.observableList(clubDBAccess.getBookings());
        reverse(pistasReservadas);
        listViewReservas.setItems(pistasReservadas);
        listViewReservas.setCellFactory(c-> new ListCellBooking());
        
        
        
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
        // TODO
    }    

    @FXML
    private void miUser(ActionEvent event) throws IOException {
        Display.showWindow(getClass(), "/vista/miPerfilapp.fxml");
             Display.setMinWH(550, 400);
    }
    
    public ObservableList<Booking> reverse(ObservableList<Booking> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
    }
    @FXML
    private void volver(ActionEvent event) throws IOException {
          Display.setView(getClass(), "/jfxpaddle/appPadel.fxml");
        Display.setTitle("Mis reservas");
    }

    @FXML
    private void normativa(ActionEvent event) {
         try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/jfxpaddle/horariosyreservas.fxml"));
            Parent root1 = fxmlLoader.load();
            HorariosyreservasController controlador = fxmlLoader.getController();
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setScene(scene);
            
            stage.showAndWait();
            
        }catch(IOException e){
            
        }
    }

    @FXML
    private void cierraSesion(ActionEvent event) {
         try{
            Display.setView(getClass(),"/jfxpaddle/InicioApp.fxml"); 
         
            
        }catch(IOException e){
            
        }
    }

    @FXML
    private void cierraApp(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void misPistas(ActionEvent event) throws IOException {
         Display.setView(getClass(), "/vista/myBookList.fxml");
    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
          Display.setView(getClass(), "/vista/Reservar.fxml");
    }

    @FXML
    private void hancleFecha(ActionEvent event) {
         meterReservas();
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

    @FXML
    private void verTodas(ActionEvent event) {
        pistasReservadas = FXCollections.observableList(clubDBAccess.getBookings());
        reverse(pistasReservadas);
        listViewReservas.setItems(pistasReservadas);
        
        listViewReservas.setCellFactory(c -> new ListCellBooking());
         prueba.setText("Horas reservadas para todas las pistas");
    }
    
     class ListCellBooking extends ListCell<Booking>{
        @Override
        protected void updateItem(Booking item, boolean empty){
            super.updateItem(item,empty);
            LocalDate now = LocalDate.now();
            if (item==null || empty) {setText(null);}
            else {setText(item.getCourt().getName() + "    " +item.getMadeForDay().adjustInto(now)+  "    " + item.getFromTime() + "    " + item.getMember().getLogin());
             if(item.getMadeForDay().isBefore(LocalDate.now())) {
                        setDisable(true); setOpacity(0.40);
                    }
                   if(item.getFromTime().isBefore(LocalTime.now()) && item.getMadeForDay().isEqual(now)){
                    setDisable(true); setOpacity(0.40);
                }
            }
        }
    }

    
    @FXML
    private void reiniciarDate(ActionEvent event) {
         datePicker.setValue(null);
         
    }

    @FXML
    private void pis1(ActionEvent event) {
         meterReservas("Pista 1");
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
    private void pis4(ActionEvent event) {
         meterReservas("Pista 4");
    }
    
}
