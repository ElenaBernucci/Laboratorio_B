package serverCV;

/**
 * InformazioniServer
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class InformazioniServer {

    /**
     * Postgres USERNAME
     */

        private static String PGUSERNAME = "postgres";

    /**
     * Postgres Password
     */

        private static String PGPASSWORD = "pass";

    /**
     * Postgres Port
     */

        private static final int DBPORT = 5432;

    /**
     * Postgres Database name
     */

        private static final String DBNAME = "centrivaccinali";

    /**
     * Server ip
     */

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
     * @return int
     */
    public static int getDBPORT() {
        return DBPORT;
    }

    /**
     * Get PGUSERNAME
     *
     * @return String
     */
    public static String getPGUSERNAME() {
        return PGUSERNAME;
    }

    /**
     * Get PGPASSWORD
     *
     * @return String
     */
    public static String getPGPASSWORD() {
        return PGPASSWORD;
    }

    /**
     * Get DBNAME
     *
     * @return String
     */
    public static String getDBNAME() {
        return DBNAME;
    }

    /**
     * Get IPSERVER
     *
     * @return String
     */
    public static String getIPSERVER() {
        return IPSERVER;
    }
}
