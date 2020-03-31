/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;
import jfxpaddle.AppPadelController;
import jfxpaddle.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class VerDisponibilidadController implements Initializable {
 @FXML private MenuButton menufxID;
    @FXML private ImageView pista;
    @FXML private ImageView pista1;
    @FXML private MenuItem reservar;
    @FXML private MenuItem ver;
    @FXML private ImageView pista2;
    @FXML private ImageView pista3;
    @FXML private Button boton;
    @FXML private Button boton1;
    @FXML private Button boton2;
    @FXML private Button boton21;
    @FXML private Text user;
    @FXML
    private DatePicker datePicker;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        boton.setOpacity(0.0); boton1.setOpacity(0.0); boton2.setOpacity(0.0); boton21.setOpacity(0.0);
    }

    private void volver_inicio(ActionEvent event) throws IOException {
        Display.setView(getClass(), "/vista/FXMLDocument.fxml");
    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
          Display.setView(getClass(), "/vista/Reservar.fxml");
          Display.setTitle("Reservar pista");
    }

    @FXML
    private void misreservas(ActionEvent event)  throws IOException {
         Display.setView(getClass(), "/vista/MisReservas.fxml");
         Display.setTitle("Mis reservas");
    }


    @FXML
    private void boton_pulsado(ActionEvent event) throws IOException {
        Display.showWindow(getClass(), "/vista/ficha_reserva.fxml");
    }
        
    @FXML
    private void date_picker(ActionEvent event) {
      
    }
    

}
