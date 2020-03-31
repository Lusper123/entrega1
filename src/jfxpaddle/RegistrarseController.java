/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;

import DBAcess.ClubDBAccess;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        stage = new Stage();
        final BooleanBinding missingData
                = fxNameMember.textProperty().isEmpty()
                .or(fxApellidoMember.textProperty().isEmpty())
                .or(fxTel.textProperty().isEmpty().and(fxLog.textProperty().isEmpty())
                        .or(fxPass.textProperty().isEmpty()));
        fxSave.disableProperty().bind(missingData);

        fxLog.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("") && newValue.charAt(newValue.length() - 1) == ' ') {
                String s = newValue.substring(0, newValue.length() - 1);
                fxLog.textProperty().setValue(s);
            }
        });
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
        fxTel.setText(member.getTelephon());
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
    private void pulsaSal(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Datos personales del cliente");
        alert.setHeaderText("Saliendo de datos personales del cliente");
        alert.setContentText("¿Seguro que quieres continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Node minodo = (Node) event.getSource();
            minodo.getScene().getWindow().hide();
            System.out.println("Cerrando ventana");
        }
    }

    @FXML
    private void entraYa(ActionEvent event) {
        if (member != null) {
            member.setName(fxNameMember.getText());
            member.setSurname(fxApellidoMember.getText());
            member.setTelephon(fxTel.getText());
            member.setLogin(fxLog.getText());
            member.setPassword(fxPass.getText());
        } else {
            member = new Member(fxNameMember.getText(), fxApellidoMember.getText(), fxTel.getText(), fxLog.getText(), fxPass.getText(), "", "", null);
        }
        stage.close();
    }


    @FXML
    private void TextFieldTelf(KeyEvent event) {
        char key = event.getCharacter().charAt(0);
        if (!Character.isDigit(key)) {
            event.consume();

        }
    }

    @FXML
    private void TextFieldPass(KeyEvent event) {
        int limite = 6;
        if (limite <= fxPass.getText().length()) {
            event.consume();
        }
    }

    @FXML
    private void TextFieldTarjeta(KeyEvent event) {
        char key = event.getCharacter().charAt(0);
        if (!Character.isDigit(key) || fxTarjC.getText().length() >= 16) {
            event.consume();

        }
    }

   @FXML
    private void addFoto(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir imagen");
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
        char key = event.getCharacter().charAt(0);
         int limite = 0;

        if (!Character.isDigit(key) || fxTarjC.getText().length() == limite) {
            event.consume();

        }
    }

    public void handleLoginChange(InputMethodEvent inputMethodEvent) {
        System.out.println(inputMethodEvent);
    }
}
