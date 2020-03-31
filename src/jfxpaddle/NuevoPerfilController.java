package jfxpaddle;

import entrega.pkg1.*;
import DBAcess.ClubDBAccess;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class NuevoPerfilController implements Initializable {
    ClubDBAccess clubDBAcess;

    @FXML
    private Button fxCreo;
    @FXML
    private Button fxPerfil;
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> username;
    @FXML
    private TableColumn<Member, String> name;
    @FXML
    private TableColumn<Member, String> apellidos;

    /**
     * Initializes the controller class.
     */


    ObservableList<Member> observableMember;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        //inicializamos la tabla principal
        observableMember = FXCollections.observableList(clubDBAcess.getMembers());
        //que tiene cada columna
        username.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLogin()));
        name.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getName()));
        apellidos.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getSurname()));

        tableView.setItems(observableMember);
    }

    @FXML
    private void creoPerfil(ActionEvent event) throws IOException {
        final Stage currentStage = (Stage) tableView.getScene().getWindow();
        RegistrarseController pvc = RegistrarseController.init(Modality.APPLICATION_MODAL, currentStage);
        Member newPerson = pvc.showAndWait();
        if (newPerson != null) {
            observableMember.add(newPerson);
        }

        clubDBAcess.getMembers().add(new Member("Monica", "Villacorta", "963443576", "admin", "admin", "", "", null));
        clubDBAcess.saveDB();
         /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registrarse.fxml"));
            Parent root1 = fxmlLoader.load(); 
            RegistrarseController controlador = fxmlLoader.getController();
          
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
           
           
            stage.setTitle("Crear nuevo miembro del club");
            stage.setScene(scene);  
            stage.show();*/
    }

    @FXML
    private void miPerfil(ActionEvent event) throws IOException {
        final Stage currentStageVer = (Stage) tableView.getScene().getWindow();
        RegistrarseController controller = RegistrarseController.init(Modality.APPLICATION_MODAL, currentStageVer);
        Member selectedPaciente = tableView.getSelectionModel().getSelectedItem();

        controller.showAndWait(selectedPaciente);

        tableView.refresh();
    }


    @FXML
    private void eliminaCliente(ActionEvent event) {
        observableMember.removeAll(tableView.getSelectionModel().getSelectedItems());
    }

}
