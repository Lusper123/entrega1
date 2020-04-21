/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;

import DBAcess.ClubDBAccess;
import entrega.pkg1.Display;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class RegistrarseController implements Initializable {
    
    
     protected Stage stage;
    ClubDBAccess clubDBAcess;
    private Stage primaryStage;
    private Member member;
    private FileChooser fileChooser;
    private File filePath;
    private Image image;
    @FXML
    private Button fxCancel;
    @FXML
    private Button fxSave;
    @FXML
    private TextField fxNameMember;
    @FXML
    private TextField fxApellidoMember;
    @FXML
    private TextField fxTel;
    @FXML
    private TextField fxLog;
    @FXML
    private TextField fxPass;
    @FXML
    private TextField fxTarjC;
    @FXML
    private Label warningCliente;
    @FXML
    private Button añadirFoto;
    @FXML
    private Button eliminarFotoClien;
    @FXML
    private ImageView fxImagen;
    @FXML
    private TextField fxCode;
    @FXML
    private Text text_errorContrsena;
    @FXML
    private Text text_usuari_existent;

    
    private boolean isNom, isCognom, isUsuari, isTelefon, isContrasena, isConfirmacio,
            isTargeta, isSvc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Display.setMinWH(550, 550);
        // TODO
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        stage = new Stage();
        
        isNom = isCognom = isUsuari = isTelefon = isContrasena = isConfirmacio = false;
        isTargeta = isSvc = true;
        final BooleanBinding missingData
                = fxNameMember.textProperty().isEmpty()
                .or(fxApellidoMember.textProperty().isEmpty())
                .or(fxTel.textProperty().isEmpty().and(fxLog.textProperty().isEmpty())
                        .or(fxPass.textProperty().isEmpty()));
        fxSave.disableProperty().bind(missingData);

         fxLog.textProperty().addListener((observable,oldValue,newValue)->{
         fxLog.setText(fxLog.getText().replace(" ", "")); });
           
            fxPass.textProperty().addListener((observable,oldValue,newValue)->{
         fxPass.setText(fxPass.getText().replace(" ", "")); });
       
        // TODO
        // TODO
    }    

    @FXML
    private void pulsaSal(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Cambio de reserva");
        alert.setHeaderText("Saliendo");
        alert.setContentText("¿Seguro que quieres salir ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Node minodo = (Node) event.getSource();
            minodo.getScene().getWindow().hide();
            System.out.println("Cerrando ventana");
        }
    }

    @FXML
    private void entraYa(ActionEvent event) {

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (member != null) {
            member.setName(fxNameMember.getText());
            member.setSurname(fxApellidoMember.getText());
            member.setTelephone(fxTel.getText());
            member.setLogin(fxLog.getText());
            member.setPassword(fxPass.getText());
            member.setSvc(fxCode.getText());
            member.setCreditCard(fxTarjC.getText());
            
        } else {
            member = new Member(fxNameMember.getText(), fxApellidoMember.getText(), fxTel.getText(), fxLog.getText(), fxPass.getText(), "", "", null);
        }
        
       
        alert.setHeaderText(null);
        alert.setTitle("Gracias por unirte");
        alert.setContentText("Bienvenido " + fxNameMember.getText() + ", gracias por unirte");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        clubDBAcess.saveDB();
        stage.close();
    }

    private void handleLoginChange(InputMethodEvent event) {
         System.out.println(event);
    }
    
    
    
     public static RegistrarseController init(Modality modality, Stage owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrarseController.class.getResource("Registrarse.fxml"));
        Parent parent = fxmlLoader.load();
        RegistrarseController controller = fxmlLoader.getController();
        controller.stage.setScene(new Scene(parent));
        controller.stage.initModality(modality);
        controller.stage.initOwner(owner);
        return controller;
    }

    public Member showAndWait(Member member) {
        this.member = member;
        fxNameMember.setText(member.getName());
        fxApellidoMember.setText(member.getSurname());
        fxTel.setText(member.getTelephone());
        fxLog.setText(member.getLogin());
        fxPass.setText(member.getPassword());

        //para que no se pueda editar
        fxNameMember.setDisable(true);
        fxApellidoMember.setDisable(true);
        fxTel.setDisable(true);
        fxLog.setDisable(true);
        fxPass.setDisable(true);

        // CAMBIAR O ELIMINAR foto o GUARDAR no permitido
        fxSave.setVisible(false);
        añadirFoto.setVisible(false);
        eliminarFotoClien.setVisible(false);

        stage.showAndWait();
        return this.member;
    }

    public Member showAndWait() {
        this.member = null;
        stage.showAndWait();
        return member;
    }
    @FXML
    private void TextFieldTelf(KeyEvent event) {
        char key = event.getCharacter().charAt(0);
        if (!Character.isDigit(key)) {
            event.consume();

        }
        //PREGUNTAR PER LLARGARIA DE TELEFON
        isTelefon = this.fxTel.getText().length() > 7; //FER PRINT DE L'ERROR
        //System.out.println(isTelefon);
    }


    @FXML
    private void TextFieldPass(KeyEvent event) {
        isContrasena = this.fxPass.getText().length() > 5;
        if (isContrasena) {
           this.text_errorContrsena.setStyle("-fx-fill:#FAFAFA;-fx-opacity:0.85");
        } else {
            this.text_errorContrsena.setStyle("-fx-fill:#FF5722;-fx-opacity:1");
        }
    }

    @FXML
    private void TextFieldTarjeta(KeyEvent event) {
         String lastTyped = event.getCharacter();
        int numOf = this.fxTarjC.getText().length();
        if (!Character.isDigit(lastTyped.charAt(0))
                || numOf > 15) {
            event.consume();
        }
    }

    @FXML
    private void addFoto(ActionEvent event) {
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
            fxImagen.setImage(image);//refresh

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void eliminarFoto(ActionEvent event) {
        Image image = new Image("/imag/default.png", true);
        member.setImage(image);
        fxImagen.setImage(member.getImage());
    }

    @FXML
    private void TextFieldCode(KeyEvent event) {
         String lastTyped = event.getCharacter();
        int numOf = this.fxCode.getText().length();
        if (!Character.isDigit(lastTyped.charAt(0))
                || numOf > 2) {
            event.consume();
        }
    }
    
}
