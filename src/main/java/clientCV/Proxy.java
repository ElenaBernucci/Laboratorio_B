package clientCV;


import clientCV.centriVaccinali.modelli.*;
import clientCV.cittadini.Cittadino;
import clientCV.cittadini.Utente;
import clientCV.condivisi.Controlli;
import serverCV.ServerInfo;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Proxy
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Proxy implements FunzionalitaClient {

    private final Socket socket;
    private boolean operatore = false;

    private BufferedReader in = null;
    private PrintWriter out = null;

    /**
     * Proxy Constructor
     *
     * @throws IOException
     */

    public Proxy() throws IOException {

        socket = new Socket(ServerInfo.getIPSERVER(), ServerInfo.getPORT());

        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                    true);
        } catch (IOException e) {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            throw e;
        }
    }

    /**
     * Close, chiude il collegamento col proxy
     *
     * @throws IOException
     */

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    /**
     * Metodo Login, prende una query che richiede un utente e costruisce un oggetto Utente o Cittadino
     *
     * @param query
     * @param User
     * @return Utente
     * @throws IOException
     */

    public Utente login(String query, String User) throws IOException {
        out.println("login");
        out.println(query);
        out.println(User);

        boolean find = Boolean.parseBoolean(in.readLine());

        if(!find)
            return null;
        else {
            operatore = Boolean.parseBoolean(in.readLine());
            if(operatore) {
                String nome = in.readLine();
                String cognome = in.readLine();
                String CF = in.readLine();
                String username = in.readLine();
                String password = in.readLine();

                Utente u = new Utente(
                        nome,
                        cognome,
                        username,
                        password,
                        CF
                );
                return u;
            }
            else {
                String nome = in.readLine();
                String cognome = in.readLine();
                String CF = in.readLine();
                String username = in.readLine();
                String password = in.readLine();
                String email = in.readLine();
                int idvaccinazione = Integer.parseInt(in.readLine());

                Cittadino u = new Cittadino(
                        password,
                        CF,
                        nome,
                        cognome,
                        email,
                        username,
                        idvaccinazione
                );
                return u;
            }
        }
    }

    /**
     * Metodo filtra
     *
     * @param query
     * @return ArrayList Centro Vaccinale
     * @throws IOException
     * @throws SQLException
     */

    public ArrayList<CentroVaccinale> filtra(String query) throws IOException, SQLException {
        out.println("filtra");
        out.println(query);
        ArrayList<CentroVaccinale> centrivaccinali = new ArrayList<>();

        while (true) {

            String nome = in.readLine();

            if(nome.equals("exit"))
                break;
            else {
                Tipologia tipologia = Tipologia.valueOf(in.readLine());
                Qualificatore qualificatore = Qualificatore.valueOf(in.readLine());
                String strada = in.readLine();
                String civico = in.readLine();
                String comune = in.readLine();
                String provincia = in.readLine();
                String cap = in.readLine();

                centrivaccinali.add(new CentroVaccinale(
                        nome,
                        new Indirizzo(
                                qualificatore,
                                strada,
                                civico,
                                comune,
                                provincia,
                                cap
                        ),
                        tipologia
                ));
            }
        }

        return centrivaccinali;
    }

    /**
     * Metodo registraNuovoCentro, registra un nuovo centro e crea la tabella per i suoi vaccinati
     *
     * @param nomeCentro
     * @throws IOException
     * @throws SQLException
     */

    public void registraNuovoCentro(String nomeCentro) throws IOException, SQLException {
        Controlli check = new Controlli();
        out.println("registraNuovoCentro");
        out.println(nomeCentro);
        out.println("CREATE TABLE vaccinati_" + check.nomeTabella(nomeCentro) +
                " (" +
                "nomecittadino VARCHAR(50), " +
                "cognomecittadino VARCHAR(50), " +
                "codicefiscale VARCHAR(50), " +
                "data DATE, vaccino VARCHAR(20), " +
                "idvaccinazione SMALLINT, " +
                "PRIMARY KEY(codicefiscale), " +
                "FOREIGN KEY(idvaccinazione) REFERENCES idunivoci(idvaccinazione), " +
                "FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale)" +
                ")");
    }

    /**
     * Metodo inserireInDb, inserisce i dati passati come argomento sul db
     *
     * @param query
     * @throws IOException
     * @throws SQLException
     */

    public void inserireInDb(String query) throws IOException, SQLException {
        out.println("inserireInDb");
        out.println(query);
    }

    /**
     * Metodo riceviVaccinati, restituisce i un ArrayList con i vaccinati
     *
     * @param query
     * @return ArrayList Vaccinato
     * @throws IOException
     * @throws SQLException
     */

    public ArrayList<Vaccinato> riceviVaccinati(String query) throws IOException, SQLException {
        ArrayList<Vaccinato> vaccinati = new ArrayList<>();

        out.println("riceviVaccinati");
        out.println(query);

        while (true) {
            String nomecittadino = in.readLine();

            if (nomecittadino.equals("exit"))
                break;
            else {
                String cognomecittadino = in.readLine();
                String codfisc = in.readLine();
                String vaccino = in.readLine();
                int idvaccinazione = Integer.parseInt(in.readLine());

                vaccinati.add(new Vaccinato(
                        nomecittadino,
                        cognomecittadino,
                        codfisc,
                        null,
                        null,
                        Vaccino.valueOf(vaccino),
                        idvaccinazione
                ));
            }
        }
        return vaccinati;
    }

    /**
     * Metodo riceviSintomo, genera un ArrayList pieno dei sintomi
     *
     * @param query
     * @return ArrayList Sintomo
     * @throws IOException
     * @throws SQLException
     */

    public ArrayList<Sintomo> riceviSintomi(String query) throws IOException, SQLException {

        ArrayList<Sintomo> sintomi = new ArrayList<>();

        out.println("riceviSintomi");
        out.println(query);

        while (true) {

            String nomeSintomo = in.readLine();

            if (nomeSintomo.equals("exit"))
                break;
            else {
                int idsintomo = Integer.parseInt(in.readLine());
                String descrizione = in.readLine();
                sintomi.add(new Sintomo(
                        idsintomo,
                        nomeSintomo,
                        descrizione
                ));
            }
        }

        return sintomi;
    }

    /**
     * Metodo riceviSegnalazione, restituisce un ArrayList con tutte le segnalazioni fatte
     *
     * @param query
     * @return
     * @throws IOException
     */

    public ArrayList<Segnalazione> riceviSegnalazione(String query) throws IOException {
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();

            out.println("riceviSegnalazione");
            out.println(query);

        while (true) {
            String centroVaccinale = in.readLine();

            if (centroVaccinale.equals("exit"))
                break;
            else {
                String userid = in.readLine();
                String sintomo = in.readLine();
                int severita = Integer.parseInt(in.readLine());
                String descrizione = in.readLine();

                segnalazioni.add(new Segnalazione(
                        userid,
                        sintomo,
                        severita,
                        descrizione,
                        centroVaccinale
                ));
            }
        }
        return segnalazioni;
    }

    /**
     * Metodo riceviValoriIndividuali, crea un ArrayList con tutti i valori individuali
     *
     * @param query
     * @param colonna
     * @return ArrayList String
     * @throws IOException
     */

    public ArrayList<String> riceviValoriIndividuali(String query, String colonna) throws IOException {
        ArrayList<String> values = new ArrayList<>();

        out.println("riceviValoriIndividuali");
        out.println(query);
        out.println(colonna);

        while (true) {
            String valore = in.readLine();

            if (valore.equals("exit")) break;
            else {
                values.add(valore);
            }
        }
        return values;
    }

}

