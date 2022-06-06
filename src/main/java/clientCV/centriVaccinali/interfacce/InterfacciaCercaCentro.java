package clientCV.centriVaccinali.interfacce;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * InterfacciaCercaCentro
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaCercaCentro {

    private Controlli check = new Controlli();

    @FXML
    private Button entraBtn;

    @FXML
    private Text nomeCentroText;

    @FXML
    private Text tipoText;

    @FXML
    private Text indirizzoText;

    /**
     * Imposta il file fxml con i dati del centro
     * @param centro
     * @param utente
     */
    public void setData(CentroVaccinale centro, Utente utente){

        nomeCentroText.setText(check.primaMaiuscola(centro.getNome()));
        tipoText.setText(String.valueOf(centro.getTipologia()));
        indirizzoText.setText("Indirizzo: " + centro.getIndirizzo().toString());

        entraBtn.setOnAction(event -> {

            URL fxmlLocation = getClass().getResource(Interfaccia.path + "Centro.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Interfaccia interfaccia = loader.getController();
            InterfacciaCentro interfacciaCentro = loader.getController();

            interfaccia.setUtente(utente);
            interfacciaCentro.setCentro(centro.getNome());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

}
