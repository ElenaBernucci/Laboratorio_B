package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * InterfacciaRegistraCittadino
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaRegistraCittadino extends Interfaccia {
    @FXML
    private TextField fieldNome, fieldCognome, fieldCodiceFiscale,
            fieldUsername, fieldEmail, fieldID;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private MediaView mediaView;

    private Utente utente;
    private Controlli check = new Controlli();

    /**
     * Vai alla schermata Cerca
     *
     * @param event
     * @throws IOException
     */
    public void vaiACerca(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("Cerca.fxml", utente, event);
    }

    /**
     * Vai alla schermata LogIn
     *
     * @param event
     * @throws IOException
     */
    public void vaiALogin(ActionEvent event) throws IOException {
        cambiaSchermata("Login.fxml", event);
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
    @Override
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Controlla i parametri e salva nel DB il cittadino
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public void registraCittadino() throws IOException, SQLException, InterruptedException, NotBoundException {
        String nome = fieldNome.getText();
        String cognome = fieldCognome.getText();
        String user = fieldUsername.getText();
        String CF = fieldCodiceFiscale.getText();
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();
        String id = fieldID.getText();

        if(nome.isBlank() || cognome.isBlank() || CF.isBlank() ||
                user.isBlank() || password.isBlank() || email.isBlank() || fieldID.getText().isBlank()) {
            mostraWarning("Campi mancanti", "Inserire tutti i campi richiesti");
            return;
        }

        //controllo codice fiscale
        if(!check.cfValido(CF)) {
            mostraWarning("Codice fiscale errato", "Il codice fiscale inserito è errato, riprovare");
            return;
        }

        //controllo email
        if(!check.emailValida(email)) {
            mostraWarning("Email errato", "L'email inserita è errata, riprovare");
            return;
        }

        // controllo id univoco
        if(!controllaID(id)) {
            mostraWarning("ID univoco errato", "L'ID univoco di vaccinazione viene fornito dall'operatore ed è \nformato da sole cifre");
            return;
        }

        if(controllaCodiceFiscale(CF)){
            mostraWarning("CF errato", "Il codice fiscale inserito non è corretto");
            return;
        }

        String insertAsUtente = "INSERT INTO utenti VALUES('"+user+"','"+password+"','"+CF+"','"+nome+"','"+cognome+"')";
        RMI RMIUtenti = new RMI();
        RMIUtenti.inserireInDb(insertAsUtente);

        Thread.sleep(100);

        int IDvaccinazione = Integer.parseInt(id);
        String insertAsCittadino = "INSERT INTO cittadinivaccinati VALUES('"+user+"','"+email+"','"+IDvaccinazione+"')";
        RMI RMICittadini = new RMI();
        RMICittadini.inserireInDb(insertAsCittadino);

        mostraWarning("Sei registrato!", "Adesso puoi accedere");


    }

    /**
     * Controlla che l'ID non sia presente nel DB
     * @param id
     * @return
     * @throws IOException
     */
    private boolean controllaID(String id) throws IOException, NotBoundException, SQLException, InterruptedException {

        if(id.matches("^[a-zA-Z]+$"))
            return false;

        int IDUnivocoVaccinazione = Integer.parseInt(id);
        List<String> ids;
        String query = "SELECT * FROM idunivoci WHERE idvaccinazione = '"+IDUnivocoVaccinazione+"'";


        RMI RMI = new RMI();
        ids = RMI.riceviValoriIndividuali(query, "idvaccinazione");

            return !ids.isEmpty();

        }

    /**
     * Controlla che il codice fiscale inserito sia corretto
     * @param cf
     * @return
     * @throws IOException
     */
    private boolean controllaCodiceFiscale(String cf) throws IOException, NotBoundException, SQLException, InterruptedException {
        List<String> cfs;
        String query = "SELECT * FROM idunivoci WHERE codicefiscale = '"+cf+"'";


        RMI RMI = new RMI();
        cfs = RMI.riceviValoriIndividuali(query, "codicefiscale");

        return cfs.isEmpty();
    }

}
