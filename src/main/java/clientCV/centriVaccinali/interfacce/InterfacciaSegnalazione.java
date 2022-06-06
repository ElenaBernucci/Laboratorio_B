package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.*;

/**
 * InterfacciaSegnalazione
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaSegnalazione extends Interfaccia implements Initializable {
    private CentroVaccinale centroVaccinale;
    private Utente utente;

    private Map<String, Integer> idSintomo;
    private boolean nuovaSegnalazione = true;
    public static final int MAX_CARATTERI = 256;

    @FXML
    private Text benvenutoText, nomeCentroText;
    @FXML
    private Button registratiBtn, logoutBtn;
    @FXML
    private ComboBox<String> sintomoCombo, severitaCombo;
    @FXML
    private TextArea noteAggiuntiveTextArea;
    @FXML
    private Label descrizioneText;
    @FXML
    private MediaView mediaView;

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
     * Vai alla schermata Centro
     *
     * @param event
     * @throws IOException
     */
    public void vaiAVisualizzaCentro(ActionEvent event) throws IOException {

        URL fxmlLocation = getClass().getResource(path + "Centro.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Interfaccia mInterfaccia = loader.getController();
        InterfacciaCentro interfacciaCentro = loader.getController();
        mInterfaccia.setUtente(utente);
        interfacciaCentro.setCentro(centroVaccinale.getNome());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Invia Segnalazione
     * Controlla i campi vuoti ed esegue la query per inserirla sul db
     *
     * Nel caso ci sia già una segnalazione, viene aggiornata
     *
     * @param event
     * @throws IOException
     */
    public void inserisciEventiAvversi(ActionEvent event) throws IOException {
        String nomeCentro = centroVaccinale.getNome();
        String descrizione = noteAggiuntiveTextArea.getText().trim();
        String sintomo = sintomoCombo.getValue();
        int severita = Integer.parseInt(severitaCombo.getValue());
        String query;



        if(descrizione.isBlank() || sintomoCombo.getValue() == null) {
            mostraWarning("Riempire tutti i campi", "É necessario inserire tutti i dati richiesti per poter proseguire");
            return;
        }

        if(nuovaSegnalazione)
            query = "INSERT INTO segnalazioni " +
                    "VALUES("+generaIdSegnalazione()+", " + idSintomo.get(sintomo)+", '"+utente.getUsername()+"', '"+nomeCentro+"', "+severita+",'"+descrizione+"')";
        else
            query = "UPDATE segnalazioni " +
                    "SET idsintomo = "+ idSintomo.get(sintomo)+", severita = "+severita+", descrizione = '"+descrizione+"' " +
                    "WHERE userid = '"+utente.getUsername()+"'";


        RMI RMI;

        try {
            RMI = new RMI();
                RMI.inserireInDb(query);

        } catch (SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

        nuovaSegnalazione = false;

            mostraWarning("Complimenti!", "La tua segnalazione è stata caricata!");

        vaiAVisualizzaCentro(event);
    }

    /**
     * Imposta il centro corrente
     * @param centroVaccinale
     */

    public void setCentro(CentroVaccinale centroVaccinale) {
        Controlli check = new Controlli();
        this.centroVaccinale = centroVaccinale;
        nomeCentroText.setText(check.primaMaiuscola(centroVaccinale.getNome()));
    }

    /**
     * Imposta l'utente corrente
     * Imposta i sintomi sulla comboBox
     * @param utente
     */
    @Override
    public void setUtente(Utente utente) {
        this.utente = utente;
        benvenutoText.setText("Ciao, " + utente.getUsername());
        registratiBtn.setText("Invia Segnalazione");

        RMI RMI;
        List<Segnalazione> segnalazione;

        try {
            RMI = new RMI();
            String query = "SELECT * " +
                    "FROM segnalazioni " +
                    "JOIN sintomi ON (sintomi.idsintomo = segnalazioni.idsintomo) " +
                    "WHERE userid = '" + utente.getUsername() + "'";
            segnalazione = RMI.riceviSegnalazione(query);

            if (segnalazione.size() > 0) {
                mostraWarning("Attenzione:", "Hai già fatto una segnalazione in precedenza.\nSe modifichi la tua segnalazione, quella precedente sarà rimossa");
                sintomoCombo.setValue(segnalazione.get(0).getSintomo());

                    severitaCombo.setValue(Integer.toString(segnalazione.get(0).getSeverita()));
                noteAggiuntiveTextArea.setText(segnalazione.get(0).getDescrizione());
                stampaDescrizioneSintomo();
                nuovaSegnalazione = false;
            }
        } catch (IOException | NotBoundException | SQLException | InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Stampa la descrizione del sintomo
     */
    public void stampaDescrizioneSintomo() {
        String sintomoComboTxt = sintomoCombo.getValue();
        String query = "SELECT * FROM sintomi WHERE sintomo = '" + sintomoComboTxt + "'";
        RMI RMI;
        List<Sintomo> sintomi;

        try {
            RMI = new RMI();
            sintomi = RMI.riceviSintomi(query);
            if(sintomi.size() > 0)
                descrizioneText.setText(sintomi.get(0).getDescrizione());

        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Impostazione al momento di inizializzare la schermata
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

        severitaCombo.setValue("1");

        String query = "SELECT * " +
                        "FROM sintomi";
        List<Sintomo> sintomi;
        RMI RMI;
        idSintomo = new HashMap<>();

        try {
            RMI = new RMI();
            sintomi = RMI.riceviSintomi(query);

            for (Sintomo sintomo: sintomi) {
                sintomoCombo.getItems().add(sintomo.getNome());
                idSintomo.put(sintomo.getNome(), sintomo.getIdsintomo());
            }

        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

        String[] livelliSeverita = {"1","2","3","4","5"};
        severitaCombo.getItems().addAll(livelliSeverita);

        noteAggiuntiveTextArea.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_CARATTERI ? change : null));

        sintomoCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                stampaDescrizioneSintomo();
            }
        });

    }

    /**
     * Genera un UID della segnalazione, verifica che non sia giá presente nel db
     * @return
     */
    private int generaIdSegnalazione() {
        List<String> tmpID = new ArrayList<>();
        Random rand = new Random();
        int uIDSegnalazione = -1;

        RMI RMI;

        while(true) {
            uIDSegnalazione = rand.nextInt(Short.MAX_VALUE);
            String getIDquery = "SELECT idsegnalazione " +
                    "FROM segnalazioni " +
                    "WHERE idsegnalazione = '"+uIDSegnalazione+"'";
            try {
                RMI = new RMI();
                tmpID = RMI.riceviValoriIndividuali(getIDquery, "idsegnalazione");
            } catch (IOException | NotBoundException | SQLException | InterruptedException e) {
                e.printStackTrace();
            }

            if (tmpID.isEmpty())
                break;
        }

        return uIDSegnalazione;
    }
}
