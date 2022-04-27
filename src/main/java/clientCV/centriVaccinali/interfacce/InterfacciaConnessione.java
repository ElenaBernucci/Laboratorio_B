package clientCV.centriVaccinali.interfacce;

import clientCV.cittadini.Utente;
import clientCV.condivisi.InformazioniServer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * InterfacciaConnessione
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaConnessione extends Interfaccia {

    @FXML
    private TextField portField, ipField;
    @FXML
    private Label infoLabel;
    @FXML
    private MediaView mediaView;

    /**
     * Controlla la connessione
     */
    public void connetti() {
        boolean connected;

        InformazioniServer.setIPSERVER(ipField.getText());
        InformazioniServer.setPORT(Integer.parseInt(portField.getText()));

        connected = pingHost(InformazioniServer.getIPSERVER(), InformazioniServer.getPORT());

        if (connected)
            infoLabel.setText("Connesso!");
        else
            infoLabel.setText("Non connesso, riprova");

    }

    /**
     * Ping Host
     *
     * @param host
     * @param port
     * @return
     */
    private static boolean pingHost(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 2000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media("file:///src/main/resources/Images/sfondoAnimatoVideo.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
    }

    /**
     * Imposta l'utente corrente
     *
     * @param utente
     */
    @Override
    public void setUtente(Utente utente) { }

    /**
     * Parametri di default
     *
     */
    public void setDefault() {
        ipField.setText("localhost");
        portField.setText("7070");
    }
}
