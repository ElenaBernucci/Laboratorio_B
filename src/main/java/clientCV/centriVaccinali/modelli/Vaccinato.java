package clientCV.centriVaccinali.modelli;

import clientCV.cittadini.Persona;

import java.io.Serializable;
import java.sql.Date;

/**
 * Vaccinato
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Vaccinato extends Persona implements Serializable {
    private String centroVaccinale;
    private Vaccino vaccino;
    private Date data;
    private int idVaccinazione;

    /**
     * Vaccinato
     *
     * @param nome
     * @param cognome
     * @param CF
     * @param centroVaccinale
     * @param data
     * @param vaccino
     * @param idVaccinazione
     */

    public Vaccinato (String nome,
                      String cognome,
                      String CF,
                      String centroVaccinale,
                      Date data,
                      Vaccino vaccino,
                      int idVaccinazione) {

        super(nome, cognome, CF);

        this.centroVaccinale = centroVaccinale;
        this.data = data;
        this.idVaccinazione = idVaccinazione;
        this.vaccino = vaccino;
    }

    /**
     * Get CentroVaccinale
     * @return String
     */

    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    /**
     * Get Vaccino
     * @return Vaccino
     */

    public Vaccino getVaccino() {
        return vaccino;
    }

    /**
     * Get Data
     * @return Date
     */

    public Date getData() {
        return data;
    }

    /**
     * Get IdVaccinazione
     * @return int
     */

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * Set CentroVaccinale
     * @param centroVaccinale
     */

    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * Set Vaccino
     * @param vaccino
     */

    public void setVaccino(Vaccino vaccino) {
        this.vaccino = vaccino;
    }

    /**
     * Set Data
     * @param data
     */

    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Set IdVaccinazione
     * @param idVaccinazione
     */

    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
