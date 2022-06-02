package clientCV;


import clientCV.centriVaccinali.modelli.*;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import clientCV.condivisi.OggettoLogin;
import clientCV.condivisi.RichiestaServer;
import serverCV.OperazioniServer;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Proxy
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class RMI extends UnicastRemoteObject implements FunzionalitaClient {

    private boolean operatore = false;
    OperazioniServer stub;

    /**
     * Proxy Constructor
     *
     * @throws IOException
     */

    public RMI() throws IOException, NotBoundException {
        Registry registro = LocateRegistry.getRegistry(1099);
        stub = (OperazioniServer) registro.lookup("ServerAlDB");
    }

    /**
     * Metodo Login, prende una query che richiede un utente e costruisce un oggetto Utente o Cittadino
     *
     * @param query
     * @param User
     * @return Utente
     * @throws IOException
     */

    public Utente login(String query, String User) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiestaServer = new RichiestaServer(query, User, "login");
        OggettoLogin login = stub.login(richiestaServer);

        if(!login.isRegistrato())
            return null;
        else {
            System.out.println("E' un operatore: " + login.isOperatore());
            if(login.isOperatore()) {

                Utente u = new Utente(login.getNome(),
                        login.getCognome(),
                        login.getUserid(),
                        login.getPassword(),
                        login.getCodicefiscale()
                );
                return u;
            }
            else {
                Cittadino u = new Cittadino(
                        login.getPassword(),
                        login.getCodicefiscale(),
                        login.getNome(),
                        login.getCognome(),
                        login.getEmail(),
                        login.getUserid(),
                        login.getIdVaccinazione()
                );
                return u;
            }
        }
    }

    /**
     * Metodo filtra
     *
     * @param query
     * @return List Centro Vaccinale
     * @throws IOException
     * @throws SQLException
     */

    public List<CentroVaccinale> filtra(String query) throws IOException, SQLException, InterruptedException {
        RichiestaServer richiesta = new RichiestaServer(query, "filtra");
        List<CentroVaccinale> centrivaccinali = stub.filtra(richiesta);

        return centrivaccinali;
    }

    /**
     * Metodo registraNuovoCentro, registra un nuovo centro e crea la tabella per i suoi vaccinati
     *
     * @param nomeCentro
     * @throws IOException
     * @throws SQLException
     */

    public Boolean registraNuovoCentro(String nomeCentro) throws SQLException, IOException, InterruptedException {
        Controlli check = new Controlli();
        String query = "CREATE TABLE vaccinati_" + check.nomeTabella(nomeCentro) +
                " (" +
                "nomecittadino VARCHAR(50), " +
                "cognomecittadino VARCHAR(50), " +
                "codicefiscale VARCHAR(50), " +
                "data DATE, vaccino VARCHAR(20), " +
                "idvaccinazione SMALLINT, " +
                "PRIMARY KEY(codicefiscale), " +
                "FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), " +
                "FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale)" +
                ")";
        RichiestaServer richiesta = new RichiestaServer(query, nomeCentro, "registraNuovoCentro");
        return stub.registraNuovoCentro(richiesta);
    }

    /**
     * Metodo inserireInDb, inserisce i dati passati come argomento sul db
     *
     * @param query
     * @throws IOException
     * @throws SQLException
     */

    public Boolean inserireInDb(String query) throws IOException, SQLException, InterruptedException {
        RichiestaServer richiesta = new RichiestaServer(query, "inserireInDb");
        return stub.inserireInDb(richiesta);
    }

    /**
     * Metodo riceviVaccinati, restituisce i un ArrayList con i vaccinati
     *
     * @param query
     * @return ArrayList Vaccinato
     * @throws IOException
     * @throws SQLException
     */

    public List<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviVaccinati");
        return stub.riceviVaccinati(richiesta);
    }

    /**
     * Metodo riceviSintomo, genera un ArrayList pieno dei sintomi
     *
     * @param query
     * @return ArrayList Sintomo
     * @throws IOException
     * @throws SQLException
     */

    public List<Sintomo> riceviSintomi(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviSintomi");
        return stub.riceviSintomi(richiesta);
    }

    /**
     * Metodo riceviSegnalazione, restituisce un ArrayList con tutte le segnalazioni fatte
     *
     * @param query
     * @return
     * @throws IOException
     */

    public List<Segnalazione> riceviSegnalazione(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviSegnalazione");
        return stub.riceviSegnalazione(richiesta);
    }

    /**
     * Metodo riceviValoriIndividuali, crea un ArrayList con tutti i valori individuali
     *
     * @param query
     * @param colonna
     * @return ArrayList String
     * @throws IOException
     */

    public List<String> riceviValoriIndividuali(String query, String colonna) throws IOException, SQLException, InterruptedException {
        RichiestaServer richiesta = new RichiestaServer(query, colonna, "riceviValoriIndividuali");
        return stub.riceviValoriIndividuali(richiesta);
    }

}

