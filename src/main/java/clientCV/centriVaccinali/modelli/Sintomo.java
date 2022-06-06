package clientCV.centriVaccinali.modelli;

import java.io.Serializable;

/**
 * Sintomo
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class Sintomo implements Serializable {
    private String nome, descrizione;
    private int idsintomo;

    /**
     * Costruttore Sintomo
     *
     * @param idevento
     * @param nome
     * @param descrizione
     */
    public Sintomo(int idevento, String nome, String descrizione) {
        this.idsintomo = idevento;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    /**
     * Get Nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Descrizione
     * @return Descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Get IdSintomo
     * @return IdSintomo
     */

    public int getIdsintomo() {
        return idsintomo;
    }

    /**
     * RichiestaServer
     *
     * @author Bernucci Elena 740283 VA
     * @author Clementi Luca 740350 VA
     */

    public static class RichiestaServer implements Serializable {

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
}
