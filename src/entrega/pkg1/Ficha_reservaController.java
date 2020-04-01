/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrega.pkg1;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Display.disableResizable();
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
        horario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        table.setItems(details);
        table.getColumns().addAll(details);
    }    

    @FXML
    private void boton_reserva(ActionEvent event) {
    }
    
}
