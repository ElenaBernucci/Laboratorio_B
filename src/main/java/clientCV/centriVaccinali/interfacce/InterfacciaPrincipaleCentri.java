package clientCV.centriVaccinali.interfacce;

import clientCV.cittadini.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ResourceBundle;

/**
 * InterfacciaPrincipaleCentri
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaPrincipaleCentri extends Interfaccia implements Initializable {
    private Utente utente;

    @FXML
    private Button logoutBtn;
    @FXML
    private Text benvenutoText;
    @FXML
    private Button registraCentroBtn, registraVaccinatoBtn;
    @FXML
    private MediaView mediaView;

    /**
     * Vai alla schermata Registra Centro
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistraCentro(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("RegistraCentroVaccinale.fxml", utente, event);
    }

    /**
     * Vai alla schermata Registra Vaccinato
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistraVaccinato(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("RegistraVaccinato.fxml", utente, event);
    }

    /**
     * Implementazione del bottone LogOut
     * Chiede conferma prima di tornare alla Home e settare l'user a null
     * @param event
     */
    public void logoutBtnImpl(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma LogOut");
        alert.setHeaderText("Stai per eseguire il LogOut");
        alert.setContentText("Vuoi Continuare?");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType okButton = new ButtonType("Si", ButtonBar.ButtonData.YES);

        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                try {
                    cambiaSchermataConUtente("Login.fxml", null, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type == noButton) {
                alert.close();
            } else {
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String absolutePath = FileSystems.getDefault().getPath("src/main/resources/Images/sfondoAnimatoVideo.mp4").normalize().toAbsolutePath().toUri().toString();
        Media media = new Media(absolutePath);
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(0);
        player.play();
    }

    /**
     * Imposta l'utente corrente
     *
     * @param utente
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
        benvenutoText.setText("Ciao, " + utente.getUsername());

    }

}
