package logika;

/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Příkaz přesune hráče do pseudoProstoru třídy Vec, existujeli
 *      * pseudoProstor je prostor s názvem "" (prázdný řeťezec)
 *
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan herniPlan;

    public PrikazProzkoumej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat? Musíš zadat jméno věci!\n";
        }

        if (parametry.length > 1) {
            return "Každá věc je jednoslovná a musí se zkoumat zvlášť!\n";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.peekVec(nazevVeci);
            if (pozadovanaVec.maProstor()) {
                herniPlan.setAktualniProstor( pozadovanaVec.getProstor() );
                return "";
            } else {
                return nazevVeci + " nelze prozkoumat!\n";
            }
        } else {
            return nazevVeci + " není v tomto prostoru!\n";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
