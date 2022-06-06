package clientCV;


import clientCV.centriVaccinali.modelli.*;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import serverCV.OperazioniServer;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

/**
 * RMI
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class RMI extends UnicastRemoteObject implements FunzionalitaClient {
    OperazioniServer stub;

    /**
     * Costruttore RMI
     *
     * @throws IOException
     * @throws NotBoundException
     */

    public RMI() throws IOException, NotBoundException {
        Registry registro = LocateRegistry.getRegistry(1099);
        stub = (OperazioniServer) registro.lookup("ServerAlDB");
    }

    /**
     * Metodo Login, riceve una query che richiede un utente e costruisce un oggetto Utente o Cittadino
     *
     * @param query
     * @param User
     * @return Utente
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
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
     * @return List(CentroVaccinale)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
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
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public Boolean registraCentroVaccinale(String nomeCentro) throws SQLException, IOException, InterruptedException {
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
        return stub.registraCentroVaccinale(richiesta);
    }

    /**
     * Metodo inserireInDb, inserisce i dati passati come argomento sul database
     *
     * @param query
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public Boolean inserireInDb(String query) throws IOException, SQLException, InterruptedException {
        RichiestaServer richiesta = new RichiestaServer(query, "inserireInDb");
        return stub.inserireInDb(richiesta);
    }

    /**
     * Metodo riceviVaccinati, restituisce una List dei vaccinati
     *
     * @param query
     * @return List(Vaccinato)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public List<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviVaccinati");
        return stub.riceviVaccinati(richiesta);
    }

    /**
     * Metodo riceviSintomo, crea una List dei sintomi
     *
     * @param query
     * @return List(Sintomo)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public List<Sintomo> riceviSintomi(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviSintomi");
        return stub.riceviSintomi(richiesta);
    }

    /**
     * Metodo riceviSegnalazione, restituisce una List con tutte le segnalazioni fatte
     *
     * @param query
     *
     * @return List(Segnalazione)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public List<Segnalazione> riceviSegnalazione(String query) throws IOException, SQLException, InterruptedException {

        RichiestaServer richiesta = new RichiestaServer(query, "riceviSegnalazione");
        return stub.riceviSegnalazione(richiesta);
    }

    /**
     * Metodo riceviValoriIndividuali, crea una List con tutti i valori singoli
     *
     * @param query
     * @param colonna
     * @return List(String)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */

    public List<String> riceviValoriIndividuali(String query, String colonna) throws IOException, SQLException, InterruptedException {
        RichiestaServer richiesta = new RichiestaServer(query, colonna, "riceviValoriIndividuali");
        return stub.riceviValoriIndividuali(richiesta);
    }

}

