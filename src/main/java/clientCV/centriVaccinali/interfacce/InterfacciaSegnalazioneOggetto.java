package clientCV.centriVaccinali.interfacce;

import clientCV.centriVaccinali.modelli.Segnalazione;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * InterfacciaSegnalazioneOggetto
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class InterfacciaSegnalazioneOggetto {
    Segnalazione segnalazione;

    @FXML
    private Text sintomoText;

    @FXML
    private Text severitaText;

    @FXML
    private Text descrizioneText;

    /**
     * Imposta i dati del file fxml con i dati delle segnalazioni
     * @param s
     */
    public void setData(Segnalazione s){
        segnalazione = s;
        StringBuilder severita = new StringBuilder();

        for(int i = 1; i <= 5; i++) {
            if (i <= segnalazione.getSeverita())
                severita.append("●");
        }

        sintomoText.setText("Sintomo: " + segnalazione.getSintomo());
        severitaText.setText(" " + severita);
        descrizioneText.setText("Descrizione: " + segnalazione.getDescrizione());

    }

}
