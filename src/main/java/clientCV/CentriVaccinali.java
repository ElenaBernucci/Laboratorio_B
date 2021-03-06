package clientCV;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *Main dei Clienti
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class CentriVaccinali extends Application {

    /**
     * Strart istruzioni eseguite all'avvio dell'applicazione
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Layout/Login.fxml")));

        Scene scene = new Scene(root, 900, 600);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Centri Vaccinali");
        primaryStage.show();



    }

    /**
     * Launcher dell'applicazione
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
