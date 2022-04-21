package clientCV.centriVaccinali.interfacce;

import clientCV.CentriVaccinali;
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
import java.util.Objects;

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
    public static final String path = "Layout/";

    /**
     * Imposta l'utente corrente
     * @param utente
     */
    public abstract void setUtente(Utente utente);

    /**
     * Cambia schermata fxml
     *
     * @param fxml
     * @param event
     * @throws IOException
     */
    public void cambiaSchermata(String fxml, ActionEvent event) throws IOException {
        Parent root = FXMLLoader
                .load(Objects.requireNonNull(CentriVaccinali.class.getClassLoader()
                .getResource(path + fxml)));
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
        FXMLLoader fxmlLoader = new FXMLLoader(CentriVaccinali.class.getClassLoader()
                .getResource( path + fxml));

        Parent root = fxmlLoader.load();

        Interfaccia interfaccia = fxmlLoader.getController();
        interfaccia.setUtente(utente);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    /**
     * Impostazione di alerta
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
