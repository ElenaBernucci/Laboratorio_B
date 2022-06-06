package clientCV.centriVaccinali.modelli;

import java.io.Serializable;

/**
 * CentroVaccinale
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class CentroVaccinale implements Serializable {
    private String nome;
    private Indirizzo indirizzo;
    private Tipologia tipologia;

    /**
     * Costruttore CentroVaccinale
     * @param nome
     * @param indirizzo
     * @param tipologia
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    /**
     * Get Nome
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set Nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get Indirizzo
     * @return Indirizzo
     */
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Set Indirizzo
     * @param indirizzo
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Get Tipologia
     * @return Tipologia
     */
    public Tipologia getTipologia() {
        return tipologia;
    }

    /**
     * Set Tipologia
     * @param tipologia
     */
    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }



}
