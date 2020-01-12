
package chinook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author PSluis
 */
public class Chinook extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
 
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
        
        Parent root = FXMLLoader.load(getClass().getResource("Chinook.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/images/mylogo2.png"));
        stage.getIcons().add(icon);
        Scene scene = new Scene(root,1000,900);
        scene.getStylesheets().add(getClass().getResource("ChinookCSS.css").toExternalForm());
        stage.setTitle("Chinook");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
