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
     * @return centroVaccinale
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
     * @return Data
     */

    public Date getData() {
        return data;
    }

    /**
     * Get IdVaccinazione
     * @return idVaccinazione
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

    /**
     * OggettoLogin
     *
     * @author Bernucci Elena 740283 VA
     * @author Clementi Luca 740350 VA
     */

    public static class OggettoLogin implements Serializable {
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
}
