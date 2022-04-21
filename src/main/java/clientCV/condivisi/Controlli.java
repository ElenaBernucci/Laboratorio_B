package clientCV.condivisi;

import clientCV.Proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controlli
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class Controlli {

    /**
     * Verifica se il CF è valido
     *
     * @param CF
     * @return boolean
     */
    public boolean cfValido(String CF) {
        return CF.matches("^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]" +
                "|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|" +
                "[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]+$");
    }

    /**
     * Converte la prima lettera della String passata come argomento in maiuscola
     *
     * @param str
     * @return String
     */
    public String primaMaiuscola(String str) {
        if (str.isBlank())
            return "";

        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
    }

    /**
     * Verifica se l'email è un indirizzo valido
     *
     * @param email
     * @return boolean
     */
    public boolean emailValido(String email) {

        String EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Stabilisce il nome della tabella
     *
     * @param tableName
     * @return String
     */
    public String nomeTabella(String tableName) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < tableName.length(); i++) {
            if(tableName.charAt(i) != ' ')
                name.append(tableName.charAt(i));
            else
                name.append('_');
        }
        return name.toString();
    }

    /**
     * Generazione di data di default se il database è vuoto
     *
     * @return boolean
     * @throws IOException
     * @throws SQLException
     */
    public boolean databaseVuoto() throws IOException, SQLException {
        Proxy proxyCheck = new Proxy();
        Proxy proxyPopulate = new Proxy();

        String queryCheck = "SELECT idsegnalazione FROM segnalazioni";
        ArrayList<String> segnalazioni = proxyCheck.riceviValoriIndividuali(queryCheck, "idsegnalazione");

        if(segnalazioni.size() > 9)
            return false;

        StringBuilder query = new StringBuilder();
        try {
            File inserzioni = new File("src/main/resources/Database/tablesInsertions_centrivaccinali.sql");
            Scanner scanner = new Scanner(inserzioni);

            while (scanner.hasNextLine())
                query.append(scanner.nextLine());

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        proxyPopulate.inserireInDb(query.toString());

                System.out.println("Database vuoti -> Data di default generata");
        return true;
    }

}
