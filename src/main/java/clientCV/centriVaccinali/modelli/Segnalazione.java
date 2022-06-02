package clientCV.centriVaccinali.modelli;

import java.io.Serializable;

/**
 * Segnalazione
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class Segnalazione implements Serializable {
    private String userid, sintomo, descrizione, centroVaccinale;
    private int severita;

    /**
     * Costruttore Segnalazione
     *
     * @param userid
     * @param sintomo
     * @param severita
     * @param descrizione
     * @param centroVaccinale
     */
    public Segnalazione(String userid,
                        String sintomo,
                        int severita,
                        String descrizione,
                        String centroVaccinale) {

        this.userid = userid;
        this.sintomo = sintomo;
        this.severita = severita;
        this.descrizione = descrizione;
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * Get Severita
     * @return severita
     */
    public int getSeverita() {
        return severita;
    }

    /**
     * Set Severita
     * @param severita
     */
    public void setSeverita(int severita) {
        this.severita = severita;
    }

    /**
     * Get UserId
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Set UserId
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Get Descrizione
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Set Descrizione
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Get Centro Vaccinale
     * @return centroVaccinale
     */
    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    /**
     * Set Centro Vaccinale
     * @param centroVaccinale
     */
    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * Get Sintomo
     * @return Sintomo
     */
    public String getSintomo() {
        return sintomo;
    }

    /**
     * Set Sintomo
     * @param sintomo
     */
    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }
}
