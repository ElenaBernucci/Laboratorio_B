package clientCV.centriVaccinali.modelli;

import clientCV.condivisi.Controlli;

import java.io.Serializable;

/**
 * Indirizzo
 *
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */
public class Indirizzo implements Serializable {
    private String strada, civico, comune, provincia, CAP;
    private Qualificatore qualificatore;

        private Controlli controlli = new Controlli();

    /**
     * Costruttore Indirizzo
     *
     * @param qualificatore
     * @param strada
     * @param civico
     * @param comune
     * @param provincia
     * @param CAP
     */
    public Indirizzo(Qualificatore qualificatore,
                     String strada,
                     String civico,
                     String comune,
                     String provincia,
                     String CAP) {

        this.qualificatore = qualificatore;
        this.strada = strada;
        this.civico = civico;
        this.comune = comune;
        this.provincia = provincia;

        if(Integer.parseInt(CAP) > 9 && Integer.parseInt(CAP) < 98101 && CAP.length() == 5)
            this.CAP = CAP;
        else
            this.CAP = "00000";
    }

    //Getters

    /**
     * Get Strada
     * @return String
     */
    public String getStrada() {
        return strada;
    }

    /**
     * Get Civico
     * @return String
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Get Comune
     * @return String
     */
    public String getComune() {
        return comune;
    }

    /**
     * Get Provincia
     * @return String
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Get CAP
     * @return String
     */
    public String getCAP() {
        return CAP;
    }

    /**
     * Get Qualificatore
     * @return String
     */
    public Qualificatore getQualificatore() {
        return qualificatore;
    }

    //To String
    /**
     * ToString
     * @return String
     */
    @Override
    public String toString() {
        return controlli.primaMaiuscola(qualificatore.toString()) +
                " " +
                strada +
                " " +
                civico +
                " - " +
                comune +
                " (" +
                provincia +
                "), " +
                CAP;
    }

}
