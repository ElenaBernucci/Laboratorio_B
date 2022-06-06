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

    /**
     * Set PGUSERNAME
     *
     * @param PGUSERNAME
     */

    public static void setPGUSERNAME(String PGUSERNAME) {
        InformazioniServer.PGUSERNAME = PGUSERNAME;
    }

    /**
     * Set PGPASSWORD
     *
     * @param PGPASSWORD
     */
    public static void setPGPASSWORD(String PGPASSWORD) {
        InformazioniServer.PGPASSWORD = PGPASSWORD;
    }

    /**
     * Set PORT
     *
     * @param PORT
     */
    public static void setPORT(int PORT) {
        InformazioniServer.PORT = PORT;
    }

    /**
     * Set IPSERVER
     *
     * @param IPSERVER
     */
    public static void setIPSERVER(String IPSERVER) {
        InformazioniServer.IPSERVER = IPSERVER;
    }

    //Getters

    /**
     * Get DBPORT
     *
     * @return
     */
    public static int getDBPORT() {
        return DBPORT;
    }

    /**
     * Get PGUSERNAME
     *
     * @return
     */
    public static String getPGUSERNAME() {
        return PGUSERNAME;
    }

    /**
     * Get PGPASSWORD
     *
     * @return
     */
    public static String getPGPASSWORD() {
        return PGPASSWORD;
    }

    /**
     * Get PORT
     *
     * @return
     */
    public static int getPORT() {
        return PORT;
    }

    /**
     * Get DBNAME
     *
     * @return
     */
    public static String getDBNAME() {
        return DBNAME;
    }

    /**
     * Get IPSERVER
     *
     * @return
     */
    public static String getIPSERVER() {
        return IPSERVER;
    }
}
