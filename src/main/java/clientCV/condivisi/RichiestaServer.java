package clientCV.condivisi;

import java.io.Serializable;

public class RichiestaServer implements Serializable {

    private String tipoRichiesta;
    private String query;
    private String param;

    public RichiestaServer(String query, String tipoRichiesta){
        this.query = query;
        this.tipoRichiesta = tipoRichiesta;
    }
    public RichiestaServer(String query, String param, String tipoRichiesta){
        this.query = query;
        this.param = param;
        this.tipoRichiesta = tipoRichiesta;
    }

    /**Getter**/

    public String getTipoRichiesta() { return tipoRichiesta; }

    public String getQuery() {
        return query;
    }

    public String getParam() {
        return param;
    }

    /**Setter**/

    public void setTipoRichiesta(String tipoRichiesta) { this.tipoRichiesta = tipoRichiesta; }

}
