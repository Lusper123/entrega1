package vista;

import DBAcess.ClubDBAccess;
import entrega.pkg1.Auth;
import entrega.pkg1.ConfirmarController;
import entrega.pkg1.Display;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxpaddle.CambioReservaController;
import model.Booking;

/**
 * FXML Controller class
 *
 * @author Moni
 */
public class MyBookListController implements Initializable {

    ClubDBAccess clubDBAccess;
    protected Stage stage;
    @FXML
    private MenuItem textUser;
    @FXML
    private ImageView imageUser;
    @FXML
    private MenuItem closeButton;
    @FXML
    private ListView<Booking> listmyBooks;
    private ObservableList<Booking> misPistasReservadas;
    @FXML
    private Button cancel;
    @FXML
    private RadioButton pendiendtes;
    @FXML
    private RadioButton pasadas;
    @FXML
    private RadioButton todo;
    
    private ObservableList<Booking> nuevo;
    private ObservableList<Booking> aux;
    @FXML
    private Button fxCambia;
    
         private   LocalDate now = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        // TODO
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
         cancel.disableProperty()
                .bind(listmyBooks.getSelectionModel().selectedItemProperty().isNull());
          fxCambia.disableProperty()
                .bind(listmyBooks.getSelectionModel().selectedItemProperty().isNull());

        imageUser.setImage(Auth.user().getImage());
        textUser.setText("Mi perfil:" + Auth.user().getName());

        misPistasReservadas = FXCollections.observableList(clubDBAccess.getUserBookings(Auth.user().getLogin()));
           for(int i = 0; i < Math.min(10, misPistasReservadas.size()); i++) {
                }

                reverse(misPistasReservadas);
                listmyBooks.setItems(misPistasReservadas);
                
        ToggleGroup b = new ToggleGroup();
        this.pendiendtes.setToggleGroup(b);
        this.pasadas.setToggleGroup(b);
        this.todo.setToggleGroup(b);
        listmyBooks.setCellFactory(c -> new ListCellBooking());
        }
    
    public ObservableList<Booking> reverse(ObservableList<Booking> bookings) {
        for(int i = 0, j = bookings.size() - 1; i < j; i++) {
            bookings.add(i, bookings.remove(j));
        }
        return bookings;
    }
    
    
    @FXML
    private void cambiarReserva(ActionEvent event) throws IOException {
         FXMLLoader fxml = new FXMLLoader();
        ClubDBAccess clubDBAcess;
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
        ClubDBAccess clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ArrayList<Booking> r = clubDBAccess.getBookings();

        Booking selectedReserva = (Booking) listmyBooks.getSelectionModel().getSelectedItem();

        String memberR = selectedReserva.getMember().getLogin();
        String fechaRv = selectedReserva.getMadeForDay().toString();
        String horaRv = selectedReserva.getFromTime().toString();
        String pistaRv = selectedReserva.getCourt().getName();

            if (controlador.getAceptar()) {
        for (int i = 0; i < r.size(); i++) {
            if (pistaRv.equals(r.get(i).getCourt().getName()) && fechaRv.equals(r.get(i).getMadeForDay().toString())
                    && memberR.equals(r.get(i).getMember().getLogin()) && horaRv.equals(r.get(i).getFromTime().toString())) {
                if (r.get(i).getMadeForDay().isAfter(LocalDate.now())) {
                 final Stage currentStage = (Stage) listmyBooks.getScene().getWindow();
        CambioReservaController controller = CambioReservaController.init(Modality.APPLICATION_MODAL, currentStage);
        
        controller.showAndWait(selectedReserva);
         listmyBooks.refresh();
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Alerta");
                    alert.setContentText("¡No puedes cambiar una reserva pasada!");
                    alert.showAndWait();
                }
            }
            }
        }

                ObservableList<Booking> pistas = FXCollections.observableArrayList(aux);

                listmyBooks.setItems(pistas);
                
                clubDBAccess.saveDB();
    }
    @FXML
    private void cancelarReserva(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader();
        ClubDBAccess clubDBAcess;
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
        ClubDBAccess clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ArrayList<Booking> r = clubDBAccess.getBookings();

        Booking selectedReserva = (Booking) listmyBooks.getSelectionModel().getSelectedItem();

        String memberR = selectedReserva.getMember().getLogin();
        String fechaRv = selectedReserva.getMadeForDay().toString();
        String horaRv = selectedReserva.getFromTime().toString();
        String pistaRv = selectedReserva.getCourt().getName();

            if (controlador.getAceptar()) {
        for (int i = 0; i < r.size(); i++) {
            if (pistaRv.equals(r.get(i).getCourt().getName()) && fechaRv.equals(r.get(i).getMadeForDay().toString())
                    && memberR.equals(r.get(i).getMember().getLogin()) && horaRv.equals(r.get(i).getFromTime().toString())) {
                if (r.get(i).getMadeForDay().isAfter(LocalDate.now())) {
                clubDBAccess.getBookings().remove(r.get(i));
                clubDBAccess.saveDB();
                listmyBooks.getItems().removeAll(selectedReserva);
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Alerta");
                    alert.setContentText("¡No puedes borrar una reserva pasada!");
                    alert.showAndWait();
                }
            }
            }
        }
             for(int i = 0; i < Math.min(10, misPistasReservadas.size()); i++) {
                    aux.add(misPistasReservadas.get(i));
                }

                ObservableList<Booking> pistas = FXCollections.observableArrayList(aux);

                listmyBooks.setItems(pistas);
    }

    @FXML
    private void verPendientes(ActionEvent event) {
        
          nuevo = FXCollections.observableArrayList();
        for (int i = 0; i < misPistasReservadas.size(); i++) {
             if (misPistasReservadas.get(i).getMadeForDay().isEqual(now) && misPistasReservadas.get(i).getFromTime().isAfter(LocalTime.now())) {
                   
                }
             if (misPistasReservadas.get(i).getMadeForDay().isAfter(now) ){
                  nuevo.add(misPistasReservadas.get(i));
                 
             }
             }
        listmyBooks.setItems(nuevo);
        listmyBooks.setCellFactory(c -> new ListCellBooking());
    }

    @FXML
    private void verPasadas(ActionEvent event) {
          nuevo = FXCollections.observableArrayList();
         for (int i = 0; i < misPistasReservadas.size(); i++) {
              if (!misPistasReservadas.get(i).getMadeForDay().isEqual(now) && !misPistasReservadas.get(i).getFromTime().isAfter(LocalTime.now())) {
                   
                }
             if (!misPistasReservadas.get(i).getMadeForDay().isAfter(now) ){
                  nuevo.add(misPistasReservadas.get(i));
                 
             }
             }
        listmyBooks.setItems(nuevo);
      
        listmyBooks.setCellFactory(c -> new ListCellBooking());
    }

    @FXML
    private void verTodo(ActionEvent event) {
        misPistasReservadas = FXCollections.observableList(clubDBAccess.getUserBookings(Auth.user().getLogin()));
        
        reverse(misPistasReservadas);
        listmyBooks.setItems(misPistasReservadas);
        listmyBooks.setCellFactory(c -> new ListCellBooking());
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        Display.setView(getClass(),"/jfxpaddle/InicioApp.fxml");
    } 

    @FXML
    private void miUser(ActionEvent event) throws IOException {
            Display.showWindow(getClass(), "/vista/miPerfilapp.fxml");
             Display.setMinWH(550, 400);
    }

    
    class ListCellBooking extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking item, boolean empty) {
            super.updateItem(item, empty);
            LocalDate now = LocalDate.now();
            LocalTime nowT = LocalTime.now();
            //String pagado = item.getPaid().toString();
           
        
            if (item == null || empty) {
                setText(null);
            } else {
      
                setText("Reservada " + item.getCourt().getName().toLowerCase() + " para " + item.getMadeForDay().adjustInto(now) + "  de  " + item.getFromTime().adjustInto(nowT) + 
                        " a " + item.getFromTime().plusHours(1).plusMinutes(30) + "     Pagado online: "  +  pagado(item));
                if(item.getMadeForDay().isBefore(LocalDate.now())) {
                        setDisable(true); setOpacity(0.40);
                    }    
                   if(item.getFromTime().isBefore(LocalTime.now()) && item.getMadeForDay().isEqual(now)){
                    setDisable(true); setOpacity(0.40);
                }
            }
        }
    }
    
    private String pagado(Booking item){
            String s;
            if (item.getPaid()) {s="si";}
            else {s="no";}
        return s;
        
    }
    
    @FXML
    private void volver(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/jfxpaddle/appPadel.fxml");
        Display.setTitle("Mis reservas");
    }

    @FXML
    private void pistas(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/MisReservas.fxml");

    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/Reservar.fxml");

    }

    @FXML
    private void normas(ActionEvent event) throws IOException {
        Display.showWindow(getClass(), "/jfxpaddle/horariosyreservas.fxml");

    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

}