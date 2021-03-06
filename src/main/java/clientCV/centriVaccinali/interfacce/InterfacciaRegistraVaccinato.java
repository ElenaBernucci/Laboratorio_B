package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.centriVaccinali.modelli.Vaccino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * InterfacciaRegistraVaccinato
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaRegistraVaccinato extends Interfaccia implements Initializable {

    private Utente utente;

    @FXML
    private Text benvenutoText;

    @FXML
    private Button registraCentroBtn, registraVaccinatoBtn, logoutBtn;

    @FXML
    private TextField nomeField, codFiscaleField, cognomeField;

    @FXML
    private ComboBox<String> centrivaccinaliCombo;

    @FXML
    private ComboBox<String> vaccinoCombo;

    @FXML
    private DatePicker dataField;

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
     * Controlla i dati ed esegue la query per registrare un vaccinato
     *
     * @param event
     * @throws ParseException
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public void registraVaccinato(ActionEvent event) throws ParseException, IOException, SQLException, InterruptedException, NotBoundException {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String CF = codFiscaleField.getText();
        String vaccino = vaccinoCombo.getValue();
        String centrovaccinale = centrivaccinaliCombo.getValue();

        LocalDate date = dataField.getValue();

        Controlli check = new Controlli();

        //Controllo del nome, cognome e CF
        if(nome.isBlank() || cognome.isBlank() || CF.isBlank()
                || vaccino == null || centrovaccinale == null || date == null) {
            mostraWarning("Riempire tutti i campi", "?? necessario inserire tutti i dati richiesti per poter proseguire");
            return;
        }

        //Controllo della data
        if(date.isAfter(LocalDate.now())) {
            mostraWarning("Attenzione:", "La data inserita non ?? corretta");
            return;
        }

        if(!check.cfValido(CF)) {
            mostraWarning("Attenzione:", "Il codice fiscale inserito ?? errato");
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data = date.toString();
        Date myDate = dateFormat.parse(data);

        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        int idvaccino = generaUID();

        if(nuovoVaccinato(CF)) {
            String insertIntoIdunivoci = "INSERT INTO idunivoci VALUES('"+idvaccino+"', '"+CF+"', '" + centrovaccinale.toLowerCase() + "')";
            RMI RMI1 = new RMI();
            RMI1.inserireInDb(insertIntoIdunivoci);

            Thread.sleep(100);

            String query = "INSERT INTO vaccinati_" + check.nomeTabella(centrovaccinale.toLowerCase()) + " VALUES('"+nome+"', '"+cognome+"','"+CF+"','"+sqlDate+"','"+vaccino+"', '"+idvaccino+"')";
            RMI RMI = new RMI();
            RMI.inserireInDb(query);

            System.out.println("Vaccinato Registrato! Id Vaccinazione: " + ": " + idvaccino);
            mostraWarning("Cittadino registrato", "Cittadino si ?? registrato con ID: " + idvaccino);

                vaiAHome(event);
        }
        else
            mostraWarning("Errore", "Questo cittadino ?? gi?? stato registrato");
    }

    /**
     * Verifica che il vaccinato non sia presente nel db
     *
     * @param codfisc
     * @return boolean
     */
    private boolean nuovoVaccinato(String codfisc) {

        String getCF = "SELECT codicefiscale " +
                        "FROM idunivoci " +
                        "WHERE codicefiscale = '"+codfisc+"'";
        List<String> tmpCF = new ArrayList<>();

        RMI RMI;

        try {
            RMI = new RMI();
            tmpCF = RMI.riceviValoriIndividuali(getCF, "codicefiscale");
        } catch (IOException | NotBoundException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return tmpCF.isEmpty();
    }

    /**
     * Genera un UID e verifica che non sia presente nel db
     * @return int
     */
    private int generaUID() {
        List<String> tmpID = new ArrayList<>();
        Random rand = new Random();
        int idvacc = -1;
        RMI RMI;

        while(true) {
            idvacc = rand.nextInt(Short.MAX_VALUE);
            String getIDquery = "SELECT idvaccinazione " +
                                "FROM idunivoci " +
                                "WHERE idvaccinazione = '"+idvacc+"'";
            try {
                RMI = new RMI();
                tmpID = RMI.riceviValoriIndividuali(getIDquery, "idvaccinazione");
            } catch (IOException | NotBoundException | SQLException | InterruptedException e) {
                e.printStackTrace();
            }



            if (tmpID.isEmpty())
                break;


        }
        return idvacc;
    }

    /**
     * Inizializza la schermata
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

        RMI RMI;
        List<String> nomiCentri;
        String query = "SELECT * FROM centrivaccinali";

        String[] vaccini = {Vaccino.ASTRAZENECA.toString(),
                Vaccino.JOHNSONANDJOHNSON.toString(),
                Vaccino.MODERNA.toString(),
                Vaccino.PFIZER.toString()};

        vaccinoCombo.getItems().addAll(vaccini);

        try {
            RMI = new RMI();
            nomiCentri = RMI.riceviValoriIndividuali(query, "nome");
            centrivaccinaliCombo.getItems().addAll(nomiCentri);
        } catch (IOException | NotBoundException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
