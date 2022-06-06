package clientCV.condivisi;

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

    /**getter**/
    public boolean isRegistrato() { return isRegistrato; }

    public boolean isOperatore() {
        return isOperatore;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    /**setter**/
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
    public void setRegistrato(boolean registrato) {
        isRegistrato = registrato;
    }

    public void setOperatore(boolean vaccinato){
        isOperatore = vaccinato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodicefiscale(String codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }

    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
