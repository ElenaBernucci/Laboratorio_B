package clientCV.persone;


/**
 * Cittadino
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Cittadino extends Utente {
    private String email;
    private int idVaccinazione;

    /**
     * Costruttore Cittadino
     *
     * @param username
     * @param password
     * @param nome
     * @param cognome
     * @param email
     * @param CF
     * @param idVaccinazione
     */
    public Cittadino(
            String username,
            String password,
            String nome,
            String cognome,
            String email,
            String CF,
            int idVaccinazione) {

        super(nome, cognome, CF, username, password);
        this.email = email;
        this.idVaccinazione = idVaccinazione;

    }

    /**
     * Metodo toString
     *
     * @return String
     */
    @Override
    public String toString(){
        return " Cittadino to String -> Username: " + getUsername()
                + " , password: " + getPassword()
                + " , nome: " + getNome()
                + " , cognome: " + getCognome()
                + " , email: " + email
                + " , CF: " + getCF()
                + " , IDVac: " + getIdVaccinazione();
    }

    /**
     * Get Email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get IDVaccinazione
     * @return idVaccinazione
     */
    public int getIdVaccinazione() {
        return idVaccinazione;
    }



}

