package clientCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.cittadini.Utente;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;
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
     * @param query
     * @param User
     * @return Utente
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    Utente login(String query, String User) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo filtra, filtra i risultati in base alle richieste dell'utente
     *
     * @param query
     * @return List(CentroVaccinale)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<CentroVaccinale> filtra(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @param nomeCentro
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    Boolean registraCentroVaccinale(String nomeCentro) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @param query
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    Boolean inserireInDb(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @param query
     * @return List(Vaccinato)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSintomi, richiede i sintomi al database
     *
     * @param query
     * @return List(Sintomo)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Sintomo> riceviSintomi(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSegnalazione, richiede le segnalazioni al database
     *
     * @param query
     * @return List(Segnalazione)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Segnalazione> riceviSegnalazione(String query) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviValoriIndividuali, richiede valori individuali al database
     *
     * @param query
     * @param colonna
     * @return List(String)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<String> riceviValoriIndividuali(String query, String colonna) throws IOException, SQLException, InterruptedException;

    }
