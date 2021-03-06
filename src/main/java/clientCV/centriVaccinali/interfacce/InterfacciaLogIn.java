package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.rmi.NotBoundException;
import java.sql.SQLException;
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
     * Vai alla schermata Registrati
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     * @throws NotBoundException
     * @throws InterruptedException
     */
    public void vaiARegistra(ActionEvent event) throws IOException, SQLException, NotBoundException, InterruptedException {

        controlliInserimento();

        cambiaSchermataConUtente("RegistraCittadino.fxml", null, event);
    }

    /**
     * Vai alla schermata PrincipaleCittadini come ospite
     *
     * @param event
     * @throws IOException
     */
    public void logInOspite(ActionEvent event) throws IOException {

        cambiaSchermataConUtente("PrincipaleCittadini.fxml", null, event);
    }

    /**
     * Controlla che esista l'utente inserito ed esegue il log in
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     * @throws NotBoundException
     * @throws InterruptedException
     */
    public void controllaLogIn(ActionEvent event) throws IOException, SQLException, NotBoundException, InterruptedException {

        controlliInserimento();

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            mostraWarning("Riempire tutti i campi", "?? necessatio inserire username e password per accedere");
            return;
        }

        RMI RMI = new RMI();
        String query = "SELECT * " +
                "FROM utenti " +
                "WHERE userid = '" + username+
                "'AND pass = '" + password +"'";
        utente = RMI.login(query, username);

        if(utente == null) {

            mostraWarning("Utente non trovato", "Username e Password non corrispondono a nessun utente registrato");
        } else {
            if(utente instanceof Cittadino) {
                cambiaSchermataConUtente("PrincipaleCittadini.fxml", utente, event);
                System.out.println("Cittadino");

            }
            else {
                cambiaSchermataConUtente("PrincipaleCentri.fxml", utente, event);
                System.out.println("Operatore");

            }
        }
    }

    /**
     * Inizializza la schermata
     *
     * @param url
     * @param rb
     */


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
     * Controlla che il database non sia vuoto
     *
     * @throws IOException
     * @throws SQLException
     * @throws NotBoundException
     * InterruptedException
     */
    public boolean controlliInserimento() throws IOException, SQLException, NotBoundException, InterruptedException {

        Controlli check = new Controlli();

            //Se non ci sono valori sul database, allora riempi i database con i dati di default
        return check.databaseVuoto();
    }
}
