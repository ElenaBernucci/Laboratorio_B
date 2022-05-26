package serverCV;

import clientCV.condivisi.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * ConnessioneServer
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class ConnessioneServer extends Thread{

    private Semaphore sem;
    private RichiestaServer richiesta;
    private OggettoLogin login;
    private OggettoSintomo sintomo;
    private List<?> lista;
    private OggettoSegnalazione segnalazione;
    private Boolean risultato;

    /**
     * ServerConnection Constructor
     *
     * @param sem
     * @param richiesta
     */

    public ConnessioneServer(RichiestaServer richiesta, Semaphore sem, OggettoLogin login) {
        this.sem = sem;
        this.richiesta = richiesta;
        this.login = login;

        start();
    }
    public ConnessioneServer(RichiestaServer richiesta, Semaphore sem, Boolean risultato){
        this.sem = sem;
        this.richiesta = richiesta;
        this.risultato = risultato;

        start();
    }
    public ConnessioneServer(RichiestaServer richiesta, Semaphore sem, List<?> lista) {
        this.sem = sem;
        this.richiesta = richiesta;
        this.lista = lista;

        start();
    }


    /**
     * Metodo fetchFromDb, si interfaccia con RisorsePerServer per eseguire l'operazione richiesta
     *
     * @param c
     * @throws IOException
     * @throws SQLException
     */

    private void fetchFromDb(Connection c) throws IOException, SQLException {

        RisorsePerServer eseguiRichiesta = new RisorsePerServer(c);


        switch (richiesta.getTipoRichiesta()) {
            case "login" -> login = eseguiRichiesta.login();
            case "riceviSintomi" -> lista = eseguiRichiesta.riceviSintomi();
            case "inserireInDb" -> risultato = eseguiRichiesta.inserireInDb();
            case "registraNuovoCentro" -> risultato = eseguiRichiesta.registraNuovoCentro();
            case "riceviValoriIndividuali" -> lista = eseguiRichiesta.riceviValoriIndividuali();
            case "riceviVaccinati" -> lista = eseguiRichiesta.riceviVaccinati();
            case "filtra" -> lista = eseguiRichiesta.filtra();
            case "riceviSegnalazione" -> lista = eseguiRichiesta.riceviSegnalazione();
            default -> {
            }
        }
    }

    public void run(){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String connectionAddress = "jdbc:postgresql://" + InformazioniServer.getIPSERVER()
                    + ":" + InformazioniServer.getDBPORT() + "/"
                    + InformazioniServer.getDBNAME();

            Connection connection = DriverManager.getConnection(connectionAddress,
                            InformazioniServer.getPGUSERNAME(),
                            InformazioniServer.getPGPASSWORD());

            try {
                    fetchFromDb(connection);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


