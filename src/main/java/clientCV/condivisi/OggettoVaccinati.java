package clientCV.condivisi;

import java.io.Serializable;

public class OggettoVaccinati implements Serializable {
    private String nome;
    private String cognome;
    private String codicefiscale;
    private String vaccino;
    private String idVaccinazione;

    /**getter**/

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getVaccino() { return vaccino; }

    public String getIdVaccinazione() { return idVaccinazione; }

    /**setter**/

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    public void setVaccino(String vaccino) { this.vaccino = vaccino; }

    public void setIdVaccinazione(String idVaccinazione) { this.idVaccinazione = idVaccinazione; }
}
