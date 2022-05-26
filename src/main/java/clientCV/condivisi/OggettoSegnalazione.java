package clientCV.condivisi;

import java.io.Serializable;

public class OggettoSegnalazione implements Serializable {

    private String centroVaccinale;
    private String userdid;
    private String sintomo;
    private String severita;
    private String descrizione;

    /**Getter**/
    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    public String getUserdid() {
        return userdid;
    }

    public String getSintomo() {
        return sintomo;
    }

    public String getSeverita() {
        return severita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**Setter**/
    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public void setUserdid(String userdid) {
        this.userdid = userdid;
    }

    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }

    public void setSeverita(String severita) {
        this.severita = severita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
