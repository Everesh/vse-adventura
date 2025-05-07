package logika;

/**
 *  Třída PrikazPoloz implementuje pro hru příkaz polož.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Příkaz polož přesouvá věci z batohu do prostoru
 *  
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "polož";
    private HerniPlan herniPlan;
    private Batoh batoh;


    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        this.batoh = herniPlan.getBatoh();
        if (parametry.length == 0) {
            return "Co mám položit, dej mi název věci!\n";
        }

        if (parametry.length > 1) {
            return "Každá věc má jednoslovný název a musí se položit zvlášť!\n";
        }

        String nazevVeci = parametry[0];

        if (!batoh.obsahujeVec(nazevVeci)) {
            return nazevVeci + " nemáš v batohu!\n";
        }

        Vec pozadovanaVec = batoh.odeberZBatohu(nazevVeci);
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        aktualniProstor.vlozVec(pozadovanaVec);
        return "";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}