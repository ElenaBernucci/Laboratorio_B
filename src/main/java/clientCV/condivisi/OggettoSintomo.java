package clientCV.condivisi;

import java.io.Serializable;

public class OggettoSintomo implements Serializable {
    private String sintomo;
    private int idSintomo;
    private String descrizione;

    /**getter**/

    public String getSintomo() {
        return sintomo;
    }

    public int getIdSintomo() {
        return idSintomo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**setter**/

    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }

    public void setIdSintomo(int idSintomo) {
        this.idSintomo = idSintomo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
