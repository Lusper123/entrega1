/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;
import DBAcess.ClubDBAccess;
import entrega.pkg1.Display;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
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
    private ListView<Member> listClientes;

    /**
     * Initializes the controller class.
     */
     ObservableList<Member> observableClientes;
    @FXML
    private Button fxSal;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        //inicializamos la tabla principal
        observableClientes = FXCollections.observableList(clubDBAcess.getMembers());
        
        
        listClientes.setCellFactory(c -> new MemberListCell()); 
        listClientes.setCellFactory(c -> new LenguajeListCell()); //para las imagenes

        listClientes.setItems(observableClientes); 
        // TODO
    }    

    @FXML
    private void salirDeAqui(ActionEvent event) throws IOException {
       
         Display.setView(getClass(), "/jfxpaddle/InicioApp.fxml");
        //cerrar la ventana de atrás
        Stage mystage = (Stage) this.fxSal.getScene().getWindow();
        mystage.close();
    }

    
    
     class MemberListCell extends ListCell<Member> {
           @Override
           protected void updateItem(Member item, boolean empty) {
               super.updateItem(item, empty);
               if (item == null || empty) setText(null);
               else {
                   setText(item.getName() + ", " + item.getSurname());
                   clubDBAcess.saveDB();
               }
           }         
           
       }
     
      //para insertar imagenes
       class LenguajeListCell extends ListCell<Member> {
            private ImageView view = new ImageView();
            @Override
            protected void updateItem(Member item, boolean empty) { 
                super.updateItem(item, empty);
                         if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                      } else {
                            view.setImage(item.getImage());
                            view.setFitHeight(50);
                            view.setFitWidth(50);
                            setGraphic(view);
                            setText("     " + item.getName() + " " + item.getSurname());
            }
        }
       }
    @FXML
    private void creoPerfil(ActionEvent event) throws IOException {
        final Stage currentStage = (Stage) listClientes.getScene().getWindow();
        RegistrarseController pvc = RegistrarseController.init(Modality.APPLICATION_MODAL, currentStage);
        Member newPerson = pvc.showAndWait();
        if (newPerson != null) {
            observableClientes.add(newPerson);
            clubDBAcess.saveDB();
            
        }
        
    }

   

  
}

