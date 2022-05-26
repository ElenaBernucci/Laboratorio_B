package clientCV.condivisi;

import java.io.Serializable;

public class OggettoFiltro implements Serializable {
    private String nome;
    private String tipologia;
    private String qualificatore;
    private String strada;
    private String civico;
    private String comune;
    private String provincia;
    private String cap;

    /**Getter**/
    public String getNome() { return nome; }

    public String getTipologia() {
        return tipologia;
    }

    public String getQualificatore() {
        return qualificatore;
    }

    public String getStrada() {
        return strada;
    }

    public String getCivico() {
        return civico;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCap() {
        return cap;
    }

    /**Setter**/

    public void setNome(String nome) { this.nome = nome; }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setQualificatore(String qualificatore) {
        this.qualificatore = qualificatore;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
}
