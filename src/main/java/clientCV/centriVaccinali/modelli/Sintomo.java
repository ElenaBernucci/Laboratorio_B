package clientCV.centriVaccinali.modelli;

/**
 * Sintomo
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class Sintomo {
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
}
