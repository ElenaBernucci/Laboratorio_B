package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Qualificatore;
import clientCV.centriVaccinali.modelli.Tipologia;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * IterfacciaRegistraCentro
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaRegistraCentro extends Interfaccia implements Initializable {
    private Utente utente;
    private Controlli check = new Controlli();
    @FXML
    private TextField nomeField, stradaField, civicoField, provField, comuneField, capField;
    @FXML
    private ComboBox<String> qualificatoreCombo, tipologiaCombo;
    @FXML
    private Text benvenutoText;
    @FXML
    private MediaView mediaView;

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
     * Vai alla schermata principale
     *
     * @param event
     * @throws IOException
     */
    public void vaiAHome(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("PrincipaleCentri.fxml", utente, event);
    }

    /**
     * Implementazione del bottone LogOut
     * Chiede conferma prima di tornare alla Home e settare l'user a null
     * @param event
     */
    public void logoutBtnImpl(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attenzione:");
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

    /**
     * Imposta l'utente corrente
     *
     * @param utente
     */
    @Override
    public void setUtente(Utente utente) {
        this.utente = utente;
        benvenutoText.setText("Ciao, " + utente.getUsername());
    }

    /**
     * Controlla i campi e registra il centro vaccinale
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void registraCentro(ActionEvent event) throws IOException, SQLException, NotBoundException, InterruptedException {
        String nomeCentro = nomeField.getText().trim();
        String tipologia = tipologiaCombo.getValue();
        String qualificatore = qualificatoreCombo.getValue();
        String strada = check.primaMaiuscola(stradaField.getText());
        String civico = civicoField.getText();
        String comune = check.primaMaiuscola(comuneField.getText());
        String cap = capField.getText();
        String provincia = provField.getText();

        // Controllo dei campi
        //Nome Centro
        if(nomeCentro.isBlank() || qualificatore == null || strada.isBlank() ||
                civico.isBlank() || comune.isBlank() || provincia.isBlank()
                || tipologia == null) {
            mostraWarning("Riempire tutti i campi", "?? necessario inserire tutti i dati richiesti per poter proseguire");
            return;
        }

        // CAP
        if(!cap.matches("[0-9]+")) {
            mostraWarning("Attenzione:", "Il CAP inserito non ?? valido");
            return;
        } else if(cap.length() != 5 || Integer.parseInt(cap) < 10) {
            mostraWarning("Attenzione:", "Il CAP inserito ?? errato o non esistente");
            return;
        }

        // controllo provincia
        if(provincia.length() != 2 || !provincia.matches("^[a-zA-Z]+$")) {
            mostraWarning("Attenzione:", "La provincia inserita ?? errata");
            return;
        }

        // controllo civico
        if(civico.length()  > 5) {
            mostraWarning("Attenzione:", "Il numero civico inserito ?? errato");
            return;
        }

        String query = "INSERT INTO centrivaccinali VALUES('"
                + nomeCentro.toLowerCase() + "', '"
                + tipologia + "', '"
                + qualificatore + "', '"
                + strada + "', '"
                + civico + "', '"
                + comune + "', '"
                + provincia.toUpperCase() + "', '"
                + cap + "')";

        RMI RMI = new RMI();
        RMI RMI1 = new RMI();

        if (controllaCentro())
            mostraWarning("Attenzione:", "Questo centro ?? gi?? stato registrato in precedenza");
        else {
            if(provinciaValida())
                mostraWarning("Attenzione:", "La provincia inserita non ?? valida");
            //Se il centro e stato registrato correttamente
            else{
                RMI.inserireInDb(query);
                RMI1.registraCentroVaccinale(nomeCentro);
                mostraWarning("Complimenti!", "Il centro vaccinale ?? stato registrato correttamente!");
                vaiAHome(event);
            }
        }

    }

    /**
     * Controlla che la provincia inserita sia una provincia valida
     * @return boolean
     */
    private boolean provinciaValida() {
        RMI RMI;
        List<String> province = new ArrayList<>();
        String prov = provField.getText().trim().toUpperCase();

        String query = "SELECT * FROM province " +
                "WHERE sigla = '" + prov + "'";
        try {
            RMI = new RMI();
            province = RMI.riceviValoriIndividuali(query,"sigla");
        } catch (IOException | NotBoundException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }

        return province.isEmpty();
    }

    /**
     * Controlla che il centro non sia gi?? registrato
     * @return boolean
     */
    private boolean controllaCentro() {
        RMI RMI;
        List<CentroVaccinale> centriVaccinali = new ArrayList<>();
        String centro = nomeField.getText().trim().toLowerCase();

        String query = "SELECT * FROM centrivaccinali " +
                        "WHERE nome = '" + centro + "'";

        try {
            RMI = new RMI();
            centriVaccinali = RMI.filtra(query);
        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

        return !centriVaccinali.isEmpty();
    }

    /**
     * Inizializza la schermata
     * Limita la quantit?? e tipologia dei caratteri
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String absolutePath = FileSystems.getDefault().getPath("src/main/resources/Images/sfondoAnimatoVideo.mp4").normalize().toAbsolutePath().toUri().toString();
        Media media = new Media(absolutePath);
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(0);
        player.play();

        String[] tipologia = {
                Tipologia.AZIENDALE.toString(),
                Tipologia.HUB.toString(),
                Tipologia.OSPEDALIERO.toString() };

        tipologiaCombo.getItems().addAll(tipologia);

        String[] qualificatore = {
                Qualificatore.VIA.toString(),
                Qualificatore.VIALE.toString(),
                Qualificatore.PIAZZA.toString(),
                Qualificatore.CORSO.toString() };
        qualificatoreCombo.getItems().addAll(qualificatore);

        //Imposta limiti di caratteri
        nomeField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 50 ? change : null));

        stradaField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 30 ? change : null));

        comuneField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 40 ? change : null));

        provField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 2 ? change : null));
        provField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                provField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });


        capField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 5 ? change : null));
        capField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    capField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        civicoField.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 4 ? change : null));
    }

}
