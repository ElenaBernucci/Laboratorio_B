package serverCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.condivisi.OggettoLogin;
import clientCV.condivisi.RichiestaServer;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.List;

/**
 * OperazioniServer, descrive tutte le operazioni che realizza il RisorsePerServer
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public interface OperazioniServer extends Remote {

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @throws IOException
     * @throws SQLException
     */
    OggettoLogin login(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Sintomo> riceviSintomi(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean inserireInDb(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean registraCentroVaccinale(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviValoriIndividuali, prelieva valori individuali dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    List<String> riceviValoriIndividuali(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Vaccinato> riceviVaccinati(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    List<CentroVaccinale> filtra(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Segnalazione> riceviSegnalazione(RichiestaServer richiestaServer) throws IOException, SQLException, InterruptedException;
    }
