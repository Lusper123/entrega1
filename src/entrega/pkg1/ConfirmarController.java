/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;

import jfxpaddle.AppPadelController;
import jfxpaddle.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Booking;

/**
 * FXML Controller class
 *
 * @author choco
 */
public class ConfirmarController implements Initializable {

    @FXML
    private Button acc;
    @FXML
    private Button can;
    private boolean aceptar;

    private TableView<Booking> tableView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar_pressed(ActionEvent event) { 
        aceptar = true;
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    @FXML
    private void cancelar_pressed(ActionEvent event)  {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }
    public  boolean getAceptar() {
        return aceptar;
    }
    
     public void initPersona()
    {
        
    }
}
