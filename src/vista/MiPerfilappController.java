/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import DBAcess.ClubDBAccess;
import entrega.pkg1.Auth;
import entrega.pkg1.Display;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Moni
 */
public class MiPerfilappController implements Initializable {
     protected Stage stage;
    Member member;
    ClubDBAccess clubDBAcess;
     private FileChooser fileChooser;
    private File filePath;
    private Image image;
    @FXML
    private TextField fxName;
    @FXML
    private TextField fxApellid;
    @FXML
    private TextField fxTelf;
    @FXML
    private TextField fxLogin;
    @FXML
    private TextField fxPass;
    @FXML
    private TextField fxTarjCredit;
    @FXML
    private ImageView fximag;
    @FXML
    private TextField fxSVC;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button fxSal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
         
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();

       fxName.setText(Auth.user().getName());
        fxApellid.setText(Auth.user().getSurname());
        fxTelf.setText(Auth.user().getTelephone());
        fxLogin.setText(Auth.user().getLogin());
        fxPass.setText(Auth.user().getPassword());
        fxTarjCredit.setText(Auth.user().getCreditCard());
        fximag.setImage(Auth.user().getImage());
        fxSVC.setText(Auth.user().getSvc());
        
    }  
    

   

    @FXML
    private void addNewPhoto(ActionEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tots els tipus", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*png")
        );
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) {
            userDirectory = new File("C:/");


        }
        fileChooser.setInitialDirectory(userDirectory);
        this.filePath = fileChooser.showOpenDialog(stage);
        //tratar de cargar la imagen
        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            fximag.setImage(image);//refresh

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void deleteMyPhoto(ActionEvent event) {
         Image image = new Image("/imag/default.png", true);
        member.setImage(image);
        fximag.setImage(Auth.user().getImage());
    }

    @FXML
    private void exitPerfil(ActionEvent event) throws IOException {
       
    
       
      Stage mystage = (Stage) this.fxSal.getScene().getWindow();  
      mystage.close();
    }

    @FXML
    private void muestraContraseña(ActionEvent event) {
          if (checkBox.isSelected()){
                fxPass.setPromptText(fxPass.getText());
                fxPass.setText(""); 
                fxPass.setDisable(true);

        
            }else {
                fxPass .setText(fxPass.getPromptText());
                fxPass.setPromptText("");
                fxPass.setDisable(false);
    }
    }
    
}
