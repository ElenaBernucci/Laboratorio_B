package serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * OperazioniServer, descrive tutte le operazioni che realizza il RisorsePerServer
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public interface OperazioniServer {

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @throws IOException
     * @throws SQLException
     */
    void login() throws IOException, SQLException;

    /**
     * Metodo Close, chiude il collegamento
     *
     * @param socket
     * @throws IOException
     */
    void close(Socket socket) throws IOException;

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @throws IOException
     * @throws SQLException
     */
    void riceviSintomi() throws IOException, SQLException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    void inserireInDb() throws IOException, SQLException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    void registraNuovoCentro() throws IOException, SQLException;

    /**
     * Metodo riceviValoriIndividuali, prelieva valori individuali dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    void riceviValoriIndividuali() throws IOException, SQLException;

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws IOException
     * @throws SQLException
     */
    void riceviVaccinati() throws IOException, SQLException;

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    void filtra() throws IOException, SQLException;

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    void riceviSegnalazione() throws IOException, SQLException;
    }
