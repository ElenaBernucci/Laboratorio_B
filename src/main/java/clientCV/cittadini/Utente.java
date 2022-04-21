package clientCV.cittadini;

/**
 * Utente
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Utente extends Persona{
    private String username, password;

    /**
     * Costruttore Utente
     *
     * @param nome
     * @param cognome
     * @param username
     * @param password
     * @param CF
     */

    public Utente(String nome,
                  String cognome,
                  String username,
                  String password,
                  String CF) {
        super(nome, cognome, CF);
        this.username = username;
        this.password = password;
    }

    /**
     * Get Username
     *
     * @return username
     */
    //Getters & Setters
    public String getUsername() {
        return username;
    }

    /**
     * Set Username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password
     *
     * @return username
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
