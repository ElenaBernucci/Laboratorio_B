package serverCV;

import clientCV.centriVaccinali.modelli.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * ServerResourcesImpl implementa tutte le funzionalità del database
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class RisorsePerServer{

    private Connection connection;
    private Sintomo.RichiestaServer richiesta;
    private Vaccinato.OggettoLogin login;
    private ArrayList<Object> lista;
    private CentroVaccinale centroVaccinale;


    /**
     * Costruttore ServerResourcesImpl
     * Prende un parametro in, out e connection
     *
     * @param connection
     */

    public RisorsePerServer(Connection connection, Sintomo.RichiestaServer richiesta) {
        this.connection = connection;
        this.richiesta = richiesta;
    }

    /**
     * Metodo LogIn, prende un utente dal database e determina se è un vaccinato o un operatore
     *
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public Vaccinato.OggettoLogin login() throws IOException, SQLException {
        login = new Vaccinato.OggettoLogin();
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

            if (resultSet1.next()) {
                // Non e operatore
                login.setOperatore(false);
                cittadino = true;
            } else {
                // Se e operatore
                login.setOperatore(true);
            }

            System.out.println(login.isOperatore());
            System.out.println((cittadino));
            login.setNome(resultSet.getString("nome"));
            login.setCognome(resultSet.getString("cognome"));
            login.setCodicefiscale(resultSet.getString("codicefiscale"));
            login.setUserid(resultSet.getString("userid"));
            login.setPassword(resultSet.getString("pass"));


            if(cittadino) {
                System.out.println(resultSet1.getString("email"));
                login.setEmail(resultSet1.getString("email"));
                login.setIdVaccinazione(Integer.parseInt(resultSet1.getString("idvaccinazione")));
            }
            statement1.close();
            resultSet1.close();
        }
        else
            login.setRegistrato(false);

        statement.close();
        resultSet.close();
        connection.close();

        return login;
    }

    /**
     * Metodo riceviSintomi, prelieva i sintomi dal database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList riceviSintomi() throws SQLException {
        lista = new ArrayList<>();
        String query= richiesta.getQuery();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                Sintomo sintomo = new Sintomo(resultSet.getInt("idsintomo"),
                        resultSet.getString("sintomo"),
                        resultSet.getString("descrizione"));
                lista.add(sintomo);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        statement.close();
        resultSet.close();
        connection.close();
        return lista;
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

        statement.executeUpdate(query);

        statement.close();
        connection.close();
        return true;

    }

    /**
     * Metodo registraNuovoCentro, registra un Centro Vaccinale
     *
     * @throws IOException
     * @throws SQLException
     */
    public Boolean registraCentroVaccinale() throws SQLException {

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
            statement.close();
            tables.close();
            connection.close();
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
    public ArrayList riceviValoriIndividuali() throws IOException, SQLException {
        lista = new ArrayList<>();
        String query= richiesta.getQuery();
        String columnLabel = richiesta.getParam();

        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        try {
            while (resultSet.next()) {
                lista.add(resultSet.getString(columnLabel));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        statement.close();
        resultSet.close();
        connection.close();
        return lista;
    }

    /**
     * Metodo riceviVaccinati, prende i cittadini vaccinati dal DB
     *
     * @throws SQLException
     */
    public ArrayList riceviVaccinati() throws SQLException {
        lista = new ArrayList<>();
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
                lista.add(vaccinato);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        statement.close();
        resultSet.close();
        connection.close();
        return lista;
    }

    /**
     * Metodo filtra
     *
     * @throws IOException
     * @throws SQLException
     */
    public ArrayList filtra() throws IOException, SQLException {
        lista = new ArrayList<>();
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
                lista.add(centroVaccinale);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        statement.close();
        resultSet.close();
        connection.close();
        return lista;
    }

    /**
     * Metodo riceviSegnalazione, prende segnalazioni dal db
     *
     * @throws IOException
     * @throws SQLException
     */
    public ArrayList riceviSegnalazione() throws SQLException {
        lista = new ArrayList<>();
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
                lista.add(segnalazione);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        statement.close();
        resultSet.close();
        connection.close();
        return lista;
    }
}
