package clientCV.centriVaccinali.modelli;

/**
 * Richiesta Server
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class RichiestaServer {
    private String tipoRichiesta;
    private String query;
    private String param;

    /**
     * Costruttore RichiestaServer a 2 parametri
     *
     * @param query
     * @param tipoRichiesta
     */
    public RichiestaServer(String query, String tipoRichiesta){
        this.query = query;
        this.tipoRichiesta = tipoRichiesta;
    }

    /**
     * Costruttore RichiestaServer a 3 parametri
     *
     * @param query
     * @param param
     * @param tipoRichiesta
     */
    public RichiestaServer(String query, String param, String tipoRichiesta){
        this.query = query;
        this.param = param;
        this.tipoRichiesta = tipoRichiesta;
    }
    //Getter

    /**
     * Get Tipo Richiesta
     *
     * @return String
     */
    public String getTipoRichiesta() { return tipoRichiesta; }

    /**
     * Get Query
     *
     * @return String
     */
    public String getQuery() {
        return query;
    }

    /**
     * Get Param
     *
     * @return String
     */

    public String getParam() {
        return param;
    }
}
