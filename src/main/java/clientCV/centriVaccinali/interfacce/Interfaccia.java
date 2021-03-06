package clientCV.centriVaccinali.interfacce;

import clientCV.cittadini.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Interfaccia
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public abstract class Interfaccia {
    /**
     * Path
     */
    public static final String path = "/Layout/";

    public abstract void initialize (URL url, ResourceBundle rb);

    /**
     * Imposta l'utente corrente
     * @param utente
     */
    public abstract void setUtente(Utente utente);

    /**
     * Cambia schermata da file fxml
     *
     * @param fxml
     * @param event
     * @throws IOException
     */
    public void cambiaSchermata(String fxml, ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource(path + fxml);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);

        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia schermata passando come argomento l'utente corrente
     *
     * @param fxml
     * @param utente
     * @param event
     * @throws IOException
     */
    public void cambiaSchermataConUtente(String fxml, Utente utente, ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource(path + fxml);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);

        Parent root = loader.load();

        Interfaccia interfaccia = loader.getController();
        interfaccia.setUtente(utente);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    /**
     * Mostra avviso
     *
     * @param title
     * @param body
     */
    public void mostraWarning(String title, String body) {
        Alert warning = new Alert(Alert.AlertType.WARNING, "", ButtonType.CLOSE);
        warning.getDialogPane().getStylesheets();

        warning.setHeaderText(title);
        warning.setContentText(body);
        warning.show();
    }


}
