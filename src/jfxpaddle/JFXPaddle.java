package jfxpaddle;
import entrega.pkg1.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Monica
 */
public class JFXPaddle extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Display.setStage(stage);
        Display.showWindow(getClass(), "InicioApp.fxml");
        Display.setTitle("Club");
        Display.getStage().setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
