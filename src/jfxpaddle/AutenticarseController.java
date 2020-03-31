/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxpaddle;

import entrega.pkg1.Display;
import DBAcess.ClubDBAccess;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Monica
 */
public class AutenticarseController implements Initializable {
    ClubDBAccess db;
    Member member;
    @FXML
    private Button fxCancela;
    @FXML
    private Button fxEntry;
    @FXML
    private TextField fxLog;
    @FXML
    private TextField fxPass;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = ClubDBAccess.getSingletonClubDBAccess();
        db.getMembers();
    }

    @FXML
    private void cierraVentana(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void entraYa(ActionEvent event) throws IOException {
        String username = fxLog.getText().trim();
        String password = fxPass.getText();

        if (username.isEmpty() && password.isEmpty()) {
            Display.error(fxPass, "El usuario o contraseña no coinciden, intentelo de nuevo");
            return;
        }
        if (password.isEmpty()) {
            Display.error(fxPass, "La contraseña no coincide , intentelo de nuevo");
            return;
        }
        if (username.isEmpty()) {
            Display.error(fxLog, "El usuario no coincide , intentelo de nuevo");
            return;
        }

        Member member = db.getMemberByCredentials(username, password);
        if (member == null || !member.checkLogin(username)) {
            Display.error(fxLog, "El usuario o password no coincide , intentelo de nuevo");
            return;
        }

        Display.setTitle("AppPadel");
        Display.setView(getClass(), "AppPadel.fxml");
    }

    @FXML
    private void compruebaLog(ActionEvent event) {
    }

    @FXML
    private void compruebaPass(ActionEvent event) {
    }

    public void closeWindows() {
        try {
            Display.showWindow(getClass(), "InicioApp.fxml");
            Display.setTitle("Club padel");
            Stage myStage = (Stage) this.fxEntry.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            Logger.getLogger(InicioAppController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
