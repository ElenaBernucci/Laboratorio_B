package clientCV.centriVaccinali.interfacce;

import clientCV.CentriVaccinali;
import clientCV.RMI;
import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * InterfacciaCentro
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaCentro extends Interfaccia {
    private Utente utente;
    private CentroVaccinale centroVaccinale;

    @FXML
    private Text benvenutoText, nomeText, tipologiaText, numSegnalazioni, mediaSeverita;
    @FXML
    private Button segnalaBtn, registratiBtn, logoutBtn;
    @FXML
    private Label indirizzoText;
    @FXML
    private GridPane segnalazioniGrid;
    @FXML
    private ScrollPane segnalazioniScroll;
    @FXML
    private MediaView mediaView;


    /**
     * Vai alla schermata Registrati
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistrati(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("RegistraCittadino.fxml", utente, event);
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
     * Nel caso sia nullo, attiva il bottone Accedi
     *
     * @param utente
     */
    @Override
    public void setUtente(Utente utente) {
        this.utente = utente;

        if (utente == null) {
            benvenutoText.setText("Accesso eseguito come ospite");
            segnalaBtn.setDisable(true);
            logoutBtn.setText("Accedi");
            logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        vaiALogin(actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            benvenutoText.setText("Ciao, " + utente.getUsername());
            segnalaBtn.setDisable(false);
            registratiBtn.setText("Invia Segnalazione");

            registratiBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try{
                        saltaASegnalazione(actionEvent);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * Imposta il centro richiesto
     * @param centro
     */
    public void setCentro(String centro){
        //Imposta il background dello Scroll Pane
        segnalazioniScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        segnalazioniGrid.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");

        RMI RMI, RMI2;
        Controlli check = new Controlli();

        String query = "SELECT * FROM centrivaccinali WHERE nome = '" + centro + "'";
        String querySegnalazione = "SELECT * " +
                                    "FROM segnalazioni JOIN sintomi ON (sintomi.idsintomo = segnalazioni.idsintomo)" +
                                    "WHERE centrovaccinale = '" + centro + "'";
        StringBuilder severita = new StringBuilder();

        int totaleSegnalazioni = 0;
            List<Segnalazione> segnalazioni = new ArrayList<>();

        try {
            RMI = new RMI();
            RMI2 = new RMI();
            centroVaccinale = RMI.filtra(query).get(0);
            segnalazioni = RMI2.riceviSegnalazione(querySegnalazione);

        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

            for (int i = 0; i<segnalazioni.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getClassLoader()
                        .getResource(path + "SegnalazioneOggetto.fxml"));

                AnchorPane anchorPane = null;
                try {
                    anchorPane = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InterfacciaSegnalazioneOggetto interfacciaSegnalazione = fxmlLoader.getController();
                interfacciaSegnalazione.setData(segnalazioni.get(i));

                segnalazioniGrid.add(anchorPane, 0, i);

                GridPane.setMargin(anchorPane, new Insets(10, 0, 0, 0));

                totaleSegnalazioni += segnalazioni.get(i).getSeverita();

                severita.delete(0, severita.length());
            }

        nomeText.setText(check.primaMaiuscola(centroVaccinale.getNome()));
        tipologiaText.setText("Tipologia: " + centroVaccinale.getTipologia().toString());
        indirizzoText.setText("Indirizzo: " + centroVaccinale.getIndirizzo().toString());

        numSegnalazioni.setText(String.valueOf(segnalazioni.size()));

        double media = ((double) totaleSegnalazioni) / segnalazioni.size();
        if (Double.isNaN(media))
            mediaSeverita.setText("0,0 / 5,00");
        else
            mediaSeverita.setText(String.format("%.02f", media) + " / 5,00");

    }

    /**
     * Vai alla schermata per inserire una segnalazione
     * Controlla che l'utente corrente sia presente nella tabella del centro selezionato
     *
     * @param event
     * @throws IOException
     */
    public void vaiASegnalazione(ActionEvent event) throws IOException {
        RMI RMI;
        Controlli check = new Controlli();
        Cittadino cittadino = (Cittadino)utente;
        String query = "SELECT * FROM vaccinati_" + check.nomeTabella(centroVaccinale.getNome()) + " WHERE idvaccinazione = " + cittadino.getIdVaccinazione();

        try {
            RMI = new RMI();
            List<Vaccinato> vaccinati = RMI.riceviVaccinati(query);

            if(vaccinati.isEmpty()) {
                mostraWarning("Non sei registrato come paziente presso questo centro vaccinale", "Puoi segnalare eventi avversi solo presso il centro \nvaccinale in cui ti è stato somministrato il vaccino");
                return;
            }
        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + "Segnalazione.fxml"));
        Parent root = loader.load();

        Interfaccia minterfaccia = loader.getController();
        InterfacciaSegnalazione interfacciaSegnalazione = loader.getController();

        minterfaccia.setUtente(utente);
        interfacciaSegnalazione.setCentro(centroVaccinale);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void saltaASegnalazione(ActionEvent event) throws IOException {
        RMI RMI, RMI2;


        String query = "SELECT * FROM centrivaccinali WHERE nome = (SELECT centrovaccinale FROM idunivoci WHERE codicefiscale = '"+ utente.getCF() +"')";

        try {
            RMI = new RMI();
            centroVaccinale = RMI.filtra(query).get(0);
        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }


        Controlli check = new Controlli();
        Cittadino cittadino = (Cittadino)utente;
        String query2 = "SELECT * FROM vaccinati_" + check.nomeTabella(centroVaccinale.getNome()) + " WHERE idvaccinazione = " + cittadino.getIdVaccinazione();

        try {
            RMI2 = new RMI();
            List<Vaccinato> vaccinati = RMI2.riceviVaccinati(query2);

            if(vaccinati.isEmpty()) {
                mostraWarning("Non sei registrato come paziente presso questo centro vaccinale", "Puoi segnalare eventi avversi solo presso il centro \nvaccinale in cui ti è stato somministrato il vaccino");
                return;
            }
        } catch (IOException | SQLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new
                FXMLLoader(CentriVaccinali.class.getClassLoader().getResource(path + "Segnalazione.fxml"));
        Parent root = loader.load();

        Interfaccia minterfaccia = loader.getController();
        InterfacciaSegnalazione interfacciaSegnalazione = loader.getController();

        minterfaccia.setUtente(utente);
        interfacciaSegnalazione.setCentro(centroVaccinale);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
