package clientCV.centriVaccinali.modelli;

import java.io.Serializable;


/**
 * OggettoLogin
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class OggettoLogin implements Serializable {
    private String userid;
    private boolean isRegistrato;
    private boolean isOperatore;
    private String nome;
    private String cognome;
    private String codicefiscale;
    private String password;
    private String email;
    private int idVaccinazione;

    //getter

    /**
     * Is Registrato
     *
     * @return boolean
     */
    public boolean isRegistrato() { return isRegistrato; }

    /**
     * Is Operatore
     *
     * @return boolean
     */
    public boolean isOperatore() {
        return isOperatore;
    }

    /**
     * Get Nome
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Congnome
     *
     * @return String
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Get Codice Fiscale
     *
     * @return String
     */
    public String getCodicefiscale() {
        return codicefiscale;
    }

    /**
     * Get Password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get Email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get Id Vaccinazione
     *
     * @return int
     */
    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * Get User Id
     *
     * @return String
     */
    public String getUserid() {
        return userid;
    }

    //setter

    /**
     * Set User Id
     *
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Set Registrato
     * @param registrato
     */
    public void setRegistrato(boolean registrato) {
        isRegistrato = registrato;
    }

    /**
     * Set Operatore
     *
     * @param vaccinato
     */
    public void setOperatore(boolean vaccinato){
        isOperatore = vaccinato;
    }

    /**
     * Set Nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Set Cognome
     *
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Set Codice Fiscale
     *
     * @param codicefiscale
     */
    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    /**
     * Set Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set Email
     *
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Set Id Vaccinazione
     *
     * @param idVaccinazione
     */
    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
