package clientCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Utente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * OperazioniClient
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public interface FunzionalitaClient {

    /**
     * Metodo Close, chiude il collegamento
     *
     * @throws IOException
     */
    void close() throws IOException;

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @throws IOException
     * @throws SQLException
     */
    Utente login(String query, String User) throws IOException;

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    ArrayList<CentroVaccinale> filtra(String query) throws IOException, SQLException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    void registraNuovoCentro(String nomeCentro) throws IOException, SQLException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    void inserireInDb(String query) throws IOException, SQLException;

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws IOException
     * @throws SQLException
     */
    ArrayList<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException;

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @throws IOException
     * @throws SQLException
     */
    ArrayList<Sintomo> riceviSintomi(String query) throws IOException, SQLException;

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    ArrayList<Segnalazione> riceviSegnalazione(String query) throws IOException;

    /**
     * Metodo riceviValoriIndividuali, prelieva valori individuali dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    ArrayList<String> riceviValoriIndividuali(String query, String colonna) throws IOException;

    }
