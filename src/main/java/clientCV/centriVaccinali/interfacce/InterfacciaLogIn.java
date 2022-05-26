package clientCV.centriVaccinali.interfacce;

import clientCV.CentriVaccinali;
import clientCV.RMI;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import serverCV.InformazioniServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.FileSystems;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * InterfacciaLogIn
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaLogIn extends Interfaccia implements Initializable {
    @FXML
    private MediaView mediaView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private Utente utente;

    /**
     * Vai alla schermata Impostazioni
     *
     * @throws IOException
     */
    public void vaiAImpostazioni() throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(CentriVaccinali.class.getClassLoader().getResource(
                        "Layout/Connessione.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Impostazioni connessione");
        stage.show();
    }

    /**
     * Vai alla schermata Registrati
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistra(ActionEvent event) throws IOException, SQLException, NotBoundException {

        if(!tryConnection())
            return;

        cambiaSchermataConUtente("RegistraCittadino.fxml", null, event);
    }

    /**
     * Vai alla schermata HomeCittadini come ospite
     *
     * @param event
     * @throws IOException
     */
    public void logInOspite(ActionEvent event) throws IOException, SQLException, NotBoundException {

        if(!tryConnection())
            return;

        cambiaSchermataConUtente("PrincipaleCittadini.fxml", null, event);
    }

    /**
     * Controlla il collegamento col proxy ed esegue il log in basandosi sul tipo di utente
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void controllaLogIn(ActionEvent event) throws IOException, SQLException, NotBoundException {

        if(!tryConnection())
            return;

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            mostraWarning("Campi vuoti", "Inserire username e password per accedere");
            return;
        }

        RMI RMI = new RMI();
        String query = "SELECT * " +
                "FROM utenti " +
                "WHERE userid = '" + username+
                "'AND pass = '" + password +"'";
        utente = RMI.login(query, username);

        if(utente == null) {

            mostraWarning("Utente non trovato", "Username e Password non corrispondono a nessun utente");
        } else {
            if(utente instanceof Cittadino) {
                cambiaSchermataConUtente("PrincipaleCittadini.fxml", utente, event);

            }
            else {
                cambiaSchermataConUtente("PrincipaleCentri.fxml", utente, event);

            }
        }
    }



    @Override

    public void initialize (URL url, ResourceBundle rb){

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
    }

    /**
     * Controlla la connessione
     *
     * @return boolean
     * @throws IOException
     * @throws SQLException
     */
    public boolean tryConnection() throws IOException, SQLException, NotBoundException {
        boolean connected;

        connected = pingHost(InformazioniServer.getIPSERVER(), InformazioniServer.getPORT());
        if (!connected) {
            vaiAImpostazioni();
            return false;
        }

        Controlli check = new Controlli();

            //Se non ci sono valori sul database, allora riempi i database con i dati di default
        check.databaseVuoto();

        return true;
    }

    /**
     * Ping Host
     *
     * @param host
     * @param port
     * @return boolean
     */
    private static boolean pingHost(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 2000);

                return true;
        } catch (IOException e) {
            e.printStackTrace();
                return false;
        }
    }

}
