package serverCV;

import clientCV.centriVaccinali.modelli.OggettoLogin;
import clientCV.centriVaccinali.modelli.RichiestaServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private List<?> lista;
    private Boolean risultato;

    /**
     * Costruttore ConnessioneServer
     *
     * @param sem
     * @param richiesta
     */

    public ConnessioneServer(RichiestaServer richiesta, Semaphore sem) {
        this.sem = sem;
        this.richiesta = richiesta;
    }


    /**
     * Metodo fetchFromDb, si interfaccia con RisorsePerServer per eseguire l'operazione richiesta
     *
     * @param c
     * @throws IOException
     * @throws SQLException
     */

    private void fetchFromDb(Connection c) throws IOException, SQLException {

        RisorsePerServer eseguiRichiesta = new RisorsePerServer(c, richiesta);


        switch (richiesta.getTipoRichiesta()) {
            case "login" -> login = eseguiRichiesta.login();
            case "riceviSintomi" -> lista = eseguiRichiesta.riceviSintomi();
            case "inserireInDb" -> risultato = eseguiRichiesta.inserireInDb();
            case "registraNuovoCentro" -> risultato = eseguiRichiesta.registraCentroVaccinale();
            case "riceviValoriIndividuali" -> lista = eseguiRichiesta.riceviValoriIndividuali();
            case "riceviVaccinati" -> lista = eseguiRichiesta.riceviVaccinati();
            case "filtra" -> lista = eseguiRichiesta.filtra();
            case "riceviSegnalazione" -> lista = eseguiRichiesta.riceviSegnalazione();
            default -> {
            }
        }
    }

    /**
     * Get Lista
     *
     * @return List
     */
    public List<?> getLista() {
        return lista;
    }

    /**
     * Get Login
     *
     * @return OggettoLogin
     */
    public OggettoLogin getLogin() {
        return login;
    }

    /**
     * Get Risultato
     *
     * @return boolean
     */
    public Boolean getRisultato() {
        return risultato;
    }

    /**
     * Operazioni del Thread
     */
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


