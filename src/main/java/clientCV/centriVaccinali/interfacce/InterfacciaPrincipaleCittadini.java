package clientCV.centriVaccinali.interfacce;

import clientCV.RMI;
import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * InterfacciaPrincipaleCittadini
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaPrincipaleCittadini extends Interfaccia implements Initializable {
    CentroVaccinale centroVaccinale;
    @FXML
    private Text benvenutoText;
    @FXML
    private Button logoutBtn, registratiBtn, cercaBtn;
    @FXML
    private MediaView mediaView;

    private Utente utente;

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
     * Vai alla schermata Registrati
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistrati(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("RegistraCittadino.fxml",utente, event);
    }

    /**
     * Vai alla schermata Segnalazione
     *
     * @param event
     * @throws IOException
     */

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
        URL fxmlLocation = getClass().getResource(path + "Segnalazione.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Interfaccia mInterfaccia = loader.getController();
        InterfacciaSegnalazione InterfacciaSegnala = loader.getController();

        mInterfaccia.setUtente(utente);
        InterfacciaSegnala.setCentro(centroVaccinale);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Vai alla schermata LogIn
     *
     * @param event
     * @throws IOException
     */
    public void vaiALogIn(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("Login.fxml", utente, event);
    }

    /**
     * Implementazione del bottone LogOut
     * Chiede conferma prima di tornare alla schermata principale e settare l'user a null
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
     * Inizializzazione della schermata
     *
     * @param url
     * @param rb
     */

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
            registratiBtn.setDisable(false);
            logoutBtn.setText("Accedi");
            logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        vaiALogIn(actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            benvenutoText.setText("Ciao, " + utente.getUsername());
            logoutBtn.setText("LogOut");
            registratiBtn.setText("Invia Segnalazione");

            registratiBtn.setOnAction(actionEvent -> {
                try{
                    saltaASegnalazione(actionEvent);
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
        }


    }
}
