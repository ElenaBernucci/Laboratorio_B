package serverCV;

import clientCV.centriVaccinali.modelli.CentroVaccinale;
import clientCV.centriVaccinali.modelli.Segnalazione;
import clientCV.centriVaccinali.modelli.Sintomo;
import clientCV.centriVaccinali.modelli.Vaccinato;
import clientCV.condivisi.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * First Runnable Program
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

            while(tryConnection(InformazioniServer.getPGUSERNAME(),
                    InformazioniServer.getPGPASSWORD(),
                    InformazioniServer.getDBNAME(),
                    InformazioniServer.getIPSERVER(),
                    InformazioniServer.getDBPORT()) == null){
                System.out.println("Errore nell'inserimento di credenziali, riprova!");

                System.out.println("DB Username: ");
                    InformazioniServer.setPGUSERNAME(scan.nextLine());
                System.out.println("DB Password: ");
                    InformazioniServer.setPGPASSWORD(scan.nextLine());
            }

            System.out.println("Connesso!");

            /*try {
                while(true) {
                    //new ConessioneServer(sem, InformazioniServer.getPGUSERNAME(), InformazioniServer.getPGPASSWORD());
                    connection = tryConnection(InformazioniServer.getPGUSERNAME(),
                            InformazioniServer.getPGPASSWORD(),
                            InformazioniServer.getDBNAME(),
                            InformazioniServer.getIPSERVER(),
                            InformazioniServer.getDBPORT());
                }
            } catch(Exception e)  {
                e.printStackTrace();
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection tryConnection(String username, String password, String database, String ipAddress, int port) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection connection;
        try{
            connection = DriverManager
                    .getConnection("jdbc:postgresql://" + ipAddress + ":" +
                            port + "/" +
                            database +
                            "?&useUnicode=true&characterEncoding=utf8",
                            username,
                            password);
        }catch (SQLException e){
            return null;
        }
        return connection;
    }

    public OggettoLogin login(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        System.out.println("Ricevuto richiesta di Login");
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getLogin();
    }

    public List<Sintomo> riceviSintomi(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Sintomo>) connessione.getLista();
    }

    public Boolean inserireInDb(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getRisultato();
    }

    public Boolean registraNuovoCentro(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return connessione.getRisultato();
    }

    public List<String> riceviValoriIndividuali(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        System.out.println("Ricevuto richiesta valori individuali");
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<String>) connessione.getLista();
    }

    public List<Vaccinato> riceviVaccinati(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Vaccinato>) connessione.getLista();
    }

    public List<CentroVaccinale> filtra(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<CentroVaccinale>) connessione.getLista();
    }

    public List<Segnalazione> riceviSegnalazione(RichiestaServer richiesta) throws IOException, SQLException, InterruptedException {
        connessione = new ConnessioneServer(richiesta, sem);
        connessione.start();
        connessione.join();
        return (List<Segnalazione>) connessione.getLista();
    }
}
