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

/**
 * InterfacciaCercaCentro
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaCercaCentro {
    private CentroVaccinale centro;

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
     * @param c
     * @param u
     */
    public void setData(CentroVaccinale c, Utente u){
        this.centro = c;

        nomeCentroText.setText(check.primaMaiuscola(centro.getNome()));
        tipoText.setText(String.valueOf(centro.getTipologia()));
        indirizzoText.setText("Indirizzo: " + centro.getIndirizzo().toString());

        entraBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getClassLoader()
                    .getResource(Interfaccia.path + "Centro.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Interfaccia cinterfaccia = loader.getController();
            InterfacciaCentro interfacciaCentro = loader.getController();

            cinterfaccia.setUtente(u);
            interfacciaCentro.setCentro(centro.getNome());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

}
