package serverCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.condivisi.*;

import java.io.IOException;
import java.net.Socket;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
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
    OggettoLogin login(RichiestaServer richiestaServer) throws IOException, SQLException;

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Sintomo> riceviSintomi(RichiestaServer richiestaServer) throws IOException, SQLException;

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean inserireInDb(RichiestaServer richiestaServer) throws IOException, SQLException;

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    Boolean registraNuovoCentro(RichiestaServer richiestaServer) throws IOException, SQLException;

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
    List<Vaccinato> riceviVaccinati(RichiestaServer richiestaServer) throws IOException, SQLException;

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    List<CentroVaccinale> filtra(RichiestaServer richiestaServer) throws IOException, SQLException;

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    List<Segnalazione> riceviSegnalazione(RichiestaServer richiestaServer) throws IOException, SQLException;
    }
