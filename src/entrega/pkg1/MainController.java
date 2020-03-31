/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;
import jfxpaddle.AppPadelController;
import jfxpaddle.*;
import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class MainController implements Initializable {

    @FXML
    private MenuButton menufxID;
    @FXML
    private MenuItem disponibilidad;
    @FXML
    private MenuItem reservar;
    @FXML
    private MenuItem ver;
    @FXML
    private ImageView user_image;
    @FXML
    private Text user_name;
    @FXML
    private Text login_name;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
    private void misreservas(ActionEvent event) throws IOException {
         Display.setView(getClass(), "/vista/MisReservas.fxml");
        Display.setTitle("Mis reservas");
    }


}

