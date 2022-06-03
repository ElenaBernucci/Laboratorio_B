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
        public static void setPGUSERNAME(String PGUSERNAME) {
            InformazioniServer.PGUSERNAME = PGUSERNAME;
        }

        public static void setPGPASSWORD(String PGPASSWORD) {
            InformazioniServer.PGPASSWORD = PGPASSWORD;
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

        public static String getDBNAME() {
            return DBNAME;
        }

        public static String getIPSERVER() {
            return IPSERVER;
        }
}
