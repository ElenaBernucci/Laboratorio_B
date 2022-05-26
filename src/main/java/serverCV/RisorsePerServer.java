package serverCV;

import clientCV.centriVaccinali.modelli.*;
import clientCV.condivisi.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ServerResourcesImpl implementa tutte le funzionalità del database
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class RisorsePerServer{

    private Connection connection;
    private RichiestaServer richiesta;
    private OggettoLogin login;
    private List<String> colonna;
    private CentroVaccinale centroVaccinale;
    private List<CentroVaccinale> listaFiltrata;


    /**
     * Costruttore ServerResourcesImpl
     * Prende un parametro in, out e connection
     *
     * @param connection
     */

    public RisorsePerServer(Connection connection) {
        this.connection = connection;
    }

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public OggettoLogin login() throws IOException, SQLException {

        login = new OggettoLogin();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(richiesta.getQuery());
        boolean registrato = false;

        if(resultSet.next())
            registrato = true;

        if(registrato) {
            login.setRegistrato(true);
            String query1 = "SELECT * FROM cittadinivaccinati " +
                    "JOIN utenti ON cittadinivaccinati.userid = utenti.userid " +
                    "WHERE utenti.userid = '" + richiesta.getParam() + "'";

            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);

            boolean cittadino = false;
            login.setOperatore(true);

            if (resultSet1.next()) {
                // Non e operatore
                login.setOperatore(false);
                cittadino = true;
            } else {
                // Se e operatore
                login.setOperatore(true);
            }

            login.setNome(resultSet.getString("nome"));
            login.setCognome(resultSet.getString("cognome"));
            login.setCodicefiscale(resultSet.getString("codicefiscale"));
            login.setUserid(resultSet.getString("userid"));
            login.setPassword(resultSet.getString("pass"));

            if(cittadino)
                login.setEmail(resultSet1.getString("email"));

            if(cittadino)
                login.setIdVaccinazione(Integer.parseInt(resultSet1.getString("idvaccinazione")));
        }
        else
            login.setRegistrato(false);
        return login;
    }

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @return
     * @throws SQLException
     */
    public List<Sintomo> riceviSintomi() throws SQLException {
        List<Sintomo> listaSintomi = new ArrayList<>();
        String query= richiesta.getQuery();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                Sintomo sintomo = new Sintomo(resultSet.getInt("idsintomo"),
                        resultSet.getString("sintomo"),
                        resultSet.getString("descrizione"));
                listaSintomi.add(sintomo);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return listaSintomi;
    }

    /**
     * Metodo inserireInDb, Inserisce nel db un commando specifico
     *
     * @throws IOException
     * @throws SQLException
     */
    public Boolean inserireInDb() throws IOException, SQLException {

        String query = richiesta.getQuery();

        Statement statement = connection.createStatement();

        try {
            statement.executeUpdate(query);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    public Boolean registraNuovoCentro() throws SQLException {

        String nomeCentro = richiesta.getParam();
        String createTableQuery= richiesta.getQuery();

        Statement statement = connection.createStatement();

        try {
            DatabaseMetaData dbm = connection.getMetaData();
            // Verifica se esiste la tabella vaccinati-"nome-centro"
            ResultSet tables = dbm.getTables(null, null, "vaccinati_" +nomeCentro, null);
            if (!tables.next()) {
                // Se la tabella non esiste
                statement.executeUpdate(createTableQuery);
            }
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo riceviValoriIndividuali, prelieva valori individuali dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    public List<String> riceviValoriIndividuali() throws IOException, SQLException {
        colonna = new ArrayList<>();
        String query= richiesta.getQuery();
        String columnLabel = richiesta.getParam();

        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {
            while (resultSet.next()) {
                colonna.add(resultSet.getString(columnLabel));
            }
            //out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return colonna;
    }

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws SQLException
     */
    public List<Vaccinato> riceviVaccinati() throws SQLException {
        List<Vaccinato> listaVaccinati = new ArrayList<>();
        String query = richiesta.getQuery();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                Vaccinato vaccinato = new Vaccinato(resultSet.getString("nomecittadino"),
                        resultSet.getString("cognomecittadino"),
                        resultSet.getString("codicefiscale"),
                        null,
                        null,
                        Vaccino.valueOf(resultSet.getString("vaccino")),
                        Integer.parseInt(resultSet.getString("idvaccinazione")));
                listaVaccinati.add(vaccinato);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return listaVaccinati;
    }

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    public List<CentroVaccinale> filtra() throws IOException, SQLException {
        listaFiltrata = new ArrayList<>();
        String query= richiesta.getQuery();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                Indirizzo indirizzo = new Indirizzo(Qualificatore.valueOf(resultSet.getString("qualificatore")),
                        resultSet.getString("strada"),
                        resultSet.getString("civico"),
                        resultSet.getString("comune"),
                        resultSet.getString("provincia"),
                        resultSet.getString("cap"));
                centroVaccinale = new CentroVaccinale(resultSet.getString("nome"), indirizzo, Tipologia.valueOf(resultSet.getString("tipologia")));
                listaFiltrata.add(centroVaccinale);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaFiltrata;
    }

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    public List<Segnalazione> riceviSegnalazione() throws SQLException {
        List<Segnalazione> listaSegnalazioni = new ArrayList<>();
        String query = richiesta.getQuery();
        Statement statement= connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        try {
            while (resultSet.next()) {
                Segnalazione segnalazione = new Segnalazione(resultSet.getString("userid"),
                        resultSet.getString("sintomo"),
                        Integer.parseInt(resultSet.getString("severita")),
                        resultSet.getString("descrizione"),
                        resultSet.getString("centrovaccinale"));
                listaSegnalazioni.add(segnalazione);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return listaSegnalazioni;
    }
}
