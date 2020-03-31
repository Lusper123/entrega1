package entrega.pkg1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.stage.Modality;

public class Display {

    private static Stage stage = null;


    public static void Persona(String NumPista, String Hora, String Dia) {


    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Display.stage = stage;
    }

    public static void setView(Class c, String view) throws IOException {
        Parent root = FXMLLoader.load(c.getResource(view));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    public static void showWindow(Class c, String view) throws IOException {
        Parent root = FXMLLoader.load(c.getResource(view));

        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


    public static void error(TextField field, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
        field.requestFocus();
    }
}
