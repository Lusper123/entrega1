package jfxpaddle;
import entrega.pkg1.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Monica
 */
public class JFXPaddle extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Display.setStage(stage); 
        Display.setMinWH(550, 550);
        Display.showWindow(getClass(), "InicioApp.fxml");
       
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
