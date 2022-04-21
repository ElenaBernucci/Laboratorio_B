package clientCV.centriVaccinali.interfacce;

import clientCV.CentriVaccinali;
import clientCV.Proxy;
import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Tipologia;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * InterfacciaCerca
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaCerca extends Interfaccia implements Initializable {
    private CentroVaccinale centroVaccinale;
    private Utente utente;
    private Controlli check = new Controlli();
    private Proxy proxy;
    private ArrayList<CentroVaccinale> centrivaccinali;


    @FXML
    private ComboBox<String> tipologiaCBox;
    @FXML
    private RadioButton radComuneTipologia, radNome;
    @FXML
    private TextField nomeField, comuneField;
    @FXML
    private Text benvenutoText;
    @FXML
    private Button registratiBtn, logoutBtn;
    @FXML
    private ScrollPane centriScroll;
    @FXML
    private GridPane centriGrid;

    /**
     * Vai alla schermata Registrati
     *
     * @param event
     * @throws IOException
     */
    public void vaiARegistrati(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("RegistraCittadino.fxml", utente, event);
    }

    public void vaiAHome(ActionEvent event) throws IOException {
        cambiaSchermataConUtente("HomeCittadini.fxml", utente, event);
    }

    /**
     * Vai alla schermata LogIn
     *
     * @param event
     * @throws IOException
     */
    public void vaiALogIn(ActionEvent event) throws IOException {
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


    /**
     * Mostra Centri Vaccinali
     * Verifica se ci sono errori nel inserimento dei campi
     * Invia la query al Proxy
     *
     * @throws IOException
     * @throws SQLException
     */
    public void mostraCentriVaccinali() throws IOException, SQLException {

        if(radNome.isSelected()) {
            String nome = nomeField.getText().trim();

            if(nome.isBlank()) {
                mostraWarning("Campi mancanti", "Inserire il nome del centro per effettuare la ricerca");
                return;
            }

            //Cerca centro per nome
            proxy = new Proxy();
            String query = "SELECT * " +
                    "FROM centrivaccinali " +
                    "WHERE nome LIKE '%" + nome.toLowerCase() + "%'";
            centrivaccinali = proxy.filtra(query);

            if(centrivaccinali.size() == 0)
                mostraWarning("Nessun centro trovato", "Non ci sono centri vaccinali registrati con questo nome");


            centriGrid.getChildren().clear();
            //Creazione dei Risultati
                for (int i = 0; i<centrivaccinali.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                            .getClassLoader()
                            .getResource(path + "CentroSearchItem.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    InterfacciaCercaCentro interfacciaCercaCentro = fxmlLoader.getController();
                    interfacciaCercaCentro.setData(centrivaccinali.get(i), utente);

                    centriGrid.add(anchorPane,0, i);

                    GridPane.setMargin(anchorPane, new Insets(10,0,0,0));

                }

        }
        else if(radComuneTipologia.isSelected()) {
            String comune = check.primaMaiuscola(comuneField.getText().trim());
            String tipologia = tipologiaCBox.getValue();

            if(comune.isBlank() || tipologia == null) {
                mostraWarning("Campi mancanti", "Inserire il comune e la tipologia\nper effettuare la ricerca");
                return;
            }

            //ricerca per comune e tipologia
            proxy = new Proxy();

            String query = "SELECT * " +
                    "FROM centrivaccinali " +
                    "WHERE comune LIKE '%" + comune + "%' " +
                    "AND tipologia='" + tipologia + "'";

            centrivaccinali = proxy.filtra(query);

            if(centrivaccinali.size() == 0)
                mostraWarning("Nessun centro trovato", "Non esistono centri vaccinali registrati \n corrispondenti ai criteri di ricerca");

            centriGrid.getChildren().clear();
            for (int i = 0; i<centrivaccinali.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getClassLoader()
                        .getResource(path + "CentroSearchItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                InterfacciaCercaCentro interfacciaCercaCentro = fxmlLoader.getController();
                interfacciaCercaCentro.setData(centrivaccinali.get(i), utente);

                centriGrid.add(anchorPane,0, i);

                GridPane.setMargin(anchorPane, new Insets(10,0,0,0));

            }
        }
    }

    /**
     * Cambia il tipo di ricerca
     */
    public void enableFiltering () {
        nomeField.setDisable(radComuneTipologia.isSelected());
        comuneField.setDisable(radNome.isSelected());
        tipologiaCBox.setDisable(radNome.isSelected());
    }

    public void saltaASegnalazione(ActionEvent event) throws IOException {
        Proxy proxy, proxy2;


        String query = "SELECT * FROM centrivaccinali WHERE nome = (SELECT centrovaccinale FROM idunivoci WHERE codicefiscale = '"+ utente.getCF() +"')";

        try {
            proxy = new Proxy();
            centroVaccinale = proxy.filtra(query).get(0);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


        Controlli check = new Controlli();
        Cittadino cittadino = (Cittadino)utente;
        String query2 = "SELECT * FROM vaccinati_" + check.nomeTabella(centroVaccinale.getNome()) + " WHERE idvaccinazione = " + cittadino.getIdVaccinazione();

        try {
            proxy2 = new Proxy();
            ArrayList<Vaccinato> vaccinati = proxy2.riceviVaccinati(query2);

            if(vaccinati.isEmpty()) {
                mostraWarning("Non sei registrato a questo centro vaccinale", "Puoi segnalare eventi avversi solo presso il centro \nvaccinale in cui ti è stato somministrato il vaccino");
                return;
            }
        } catch (IOException | SQLException e) {
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
            benvenutoText.setText("Accesso come ospite");
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

        fillGridPane();
    }

    /**
     * Riempi il grid pane con tutti i centri vaccinali
     */
    private void fillGridPane() {
        String query = "SELECT * FROM centrivaccinali";

        try {
            proxy = new Proxy();
            centrivaccinali = proxy.filtra(query);
            for (int i = 0; i<centrivaccinali.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getClassLoader()
                        .getResource(path + "CentroSearchItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                InterfacciaCercaCentro interfacciaCercaCentro = fxmlLoader.getController();
                interfacciaCercaCentro.setData(centrivaccinali.get(i), utente);

                centriGrid.add(anchorPane,0, i);

                GridPane.setMargin(anchorPane, new Insets(10,0,0,0));

            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Impostazioni stetiche
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] tipo = {Tipologia.OSPEDALIERO.toString(), Tipologia.HUB.toString(), Tipologia.AZIENDALE.toString()};
        tipologiaCBox.getItems().addAll(tipo);
        centriScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        centriGrid.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
    }
}
