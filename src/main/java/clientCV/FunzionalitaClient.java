package clientCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Utente;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * OperazioniClient
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public interface FunzionalitaClient extends Remote {

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @throws IOException
     * @throws SQLException
     */
    Utente login(String query, String User) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    List<CentroVaccinale> filtra(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean registraNuovoCentro(String nomeCentro) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean inserireInDb(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Sintomo> riceviSintomi(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Segnalazione> riceviSegnalazione(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviValoriIndividuali, prelieva valori individuali dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    List<String> riceviValoriIndividuali(String query, String colonna) throws IOException, SQLException, InterruptedException;

    }
