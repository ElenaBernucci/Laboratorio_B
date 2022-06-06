package serverCV;

import clientCV.centriVaccinali.modelli.*;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.List;

/**
 * OperazioniServer, descrive tutte le operazioni che implementa Server
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public interface OperazioniServer extends Remote {

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @param richiestaServer
     * @return OggettoLogin
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    OggettoLogin login(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSintomi, richiede i sintomi al database
     *
     * @param richiestaServer
     * @return List(Sintomo)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Sintomo> riceviSintomi(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo inserireInDb, inserisce nel database una query specifica
     *
     * @param richiestaServer
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    Boolean inserireInDb(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo registraCentroVaccinale, registra un Centro Vaccinale
     *
     * @param richiestaServer
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    Boolean registraCentroVaccinale(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviValoriIndividuali, richiede singoli valori al database
     *
     * @param richiestaServer
     * @return List(String)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<String> riceviValoriIndividuali(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviVaccinati, richiede la lista dei cittadini vaccinati al database
     *
     * @param richiestaServer
     * @return List(Vaccinato)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Vaccinato> riceviVaccinati(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo filtra, filtra i risultati di ricerca
     *
     * @param richiestaServer
     * @return List(CentroVaccinale)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<CentroVaccinale> filtra(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSegnalazione, richiede le segnalazioni al database
     * @param richiestaServer
     * @return List(Segnalazione)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    List<Segnalazione> riceviSegnalazione(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;
    }
