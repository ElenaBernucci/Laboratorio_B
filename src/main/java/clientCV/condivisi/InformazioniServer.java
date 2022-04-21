package clientCV.condivisi;

/**
 * InformazioniServer
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */


public class InformazioniServer {

    private static String PGUSERNAME = "postgres";

    private static String PGPASSWORD = "pass";

    private static final int DBPORT = 5432;

    private static final String DBNAME = "centrivaccinali";

    private static int PORT = 7070;

    private static String IPSERVER = "localhost";

    //Setters

    public static void setPGUSERNAME(String PGUSERNAME) {
        InformazioniServer.PGUSERNAME = PGUSERNAME;
    }

    public static void setPGPASSWORD(String PGPASSWORD) {
        InformazioniServer.PGPASSWORD = PGPASSWORD;
    }

    public static void setPORT(int PORT) {
        InformazioniServer.PORT = PORT;
    }

    public static void setIPSERVER(String IPSERVER) {
        InformazioniServer.IPSERVER = IPSERVER;
    }

    //Getters
    public static int getDBPORT() {
        return DBPORT;
    }

    public static String getPGUSERNAME() {
        return PGUSERNAME;
    }

    public static String getPGPASSWORD() {
        return PGPASSWORD;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getDBNAME() {
        return DBNAME;
    }

    public static String getIPSERVER() {
        return IPSERVER;
    }
}
