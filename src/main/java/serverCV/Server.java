package serverCV;

import clientCV.centriVaccinali.modelli.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * Server Class
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Server extends UnicastRemoteObject implements OperazioniServer{

    static Connection connection;
    Semaphore sem= new Semaphore(100);

    ConnessioneServer connessione;

    protected Server() throws RemoteException {
        super();
    }

    /**
     * Eseguibile Server
     * @param args
     */

    public static void main(String[] args) {
        try{
            Server obj = new Server();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("ServerAlDB", obj);
            System.out.println("RMI server pronto");

            Scanner scan = new Scanner(System.in);

            System.out.println("DB Username: ");
                InformazioniServer.setPGUSERNAME(scan.nextLine());
            System.out.println("DB Password: ");
                InformazioniServer.setPGPASSWORD(scan.nextLine());

            while(!testConnessione(InformazioniServer.getPGUSERNAME(),
                    InformazioniServer.getPGPASSWORD(),
                    InformazioniServer.getDBNAME(),
                    InformazioniServer.getIPSERVER(),
                    InformazioniServer.getDBPORT())){
                System.out.println("Errore nell'inserimento di credenziali, riprova!");

                System.out.println("DB Username: ");
                    InformazioniServer.setPGUSERNAME(scan.nextLine());
                System.out.println("DB Password: ");
                    InformazioniServer.setPGPASSWORD(scan.nextLine());
            }

            System.out.println("Connesso!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo testConnessione effettua una connessione di prova al database
     *
     * @param username
     * @param password
     * @param database
     * @param ipAddress
     * @param port
     * @return boolean
     * @throws ClassNotFoundException
     */
    public static Boolean testConnessione(String username, String password, String database, String ipAddress, int port) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Boolean connessione = false;
        try{
            Connection connection;
            connection = DriverManager
                    .getConnection("jdbc:postgresql://" + ipAddress + ":" +
                                    port + "/" +
                                    database +
                                    "?&useUnicode=true&characterEncoding=utf8",
                            username,
                            password);
            connessione = true;
            connection.close();
            return connessione;
        } catch (SQLException e) {
            return connessione;
        }
    }

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @param richiesta
     * @return OggettoLogin
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public OggettoLogin login(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        System.out.println("Ricevuto richiesta di Login");
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getLogin();
    }

    /**
     * Metodo riceviSintomi, richiede i sintomi al database
     *
     * @param richiesta
     * @return List(Sintomo)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public List<Sintomo> riceviSintomi(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Sintomo>) connessione.getLista();
    }

    /**
     * Metodo inserireInDb, inserisce nel database una query specifica
     *
     * @param richiesta
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public Boolean inserireInDb(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getRisultato();
    }

    /**
     * Metodo registraCentroVaccinale, registra un Centro Vaccinale
     *
     * @param richiesta
     * @return boolean
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public Boolean registraCentroVaccinale(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getRisultato();
    }

    /**
     *Metodo riceviValoriIndividuali, richiede singoli valori al database
     *
     * @param richiesta
     * @return List(String)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public List<String> riceviValoriIndividuali(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        System.out.println("Ricevuto richiesta valori individuali");
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<String>) connessione.getLista();
    }

    /**
     * Metodo riceviVaccinati, richiede la lista dei cittadini vaccinati al database
     *
     * @param richiesta
     * @return List(Vaccinato)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public List<Vaccinato> riceviVaccinati(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Vaccinato>) connessione.getLista();
    }

    /**
     * Metodo filtra, filtra i risultati di ricerca
     *
     * @param richiesta
     * @return List(CentroVaccinale)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public List<CentroVaccinale> filtra(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<CentroVaccinale>) connessione.getLista();
    }

    /**
     * Metodo riceviSegnalazione, richiede le segnalazioni al database
     *
     * @param richiesta
     * @return List(Segnalazione)
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException
     */
    public List<Segnalazione> riceviSegnalazione(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Segnalazione>) connessione.getLista();
    }
}
