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
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Get Descrizione
     * @return String
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Get IdSintomo
     * @return int
     */

    public int getIdsintomo() {
        return idsintomo;
    }
}
