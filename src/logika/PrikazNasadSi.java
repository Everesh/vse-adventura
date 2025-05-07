package logika;

/**
 *  Třída PrikazNasadSi implementuje pro hru příkaz nasaď si.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Nasad si přesouvá věci z batohu do výbavy
 *
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class PrikazNasadSi implements IPrikaz {

    private static final String NAZEV = "nasaď si";
    private HerniPlan herniPlan;

    public PrikazNasadSi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz 'nandej si' přijímá pouze jeden parametr\n";
        }
        if (!herniPlan.getBatoh().obsahujeVec(parametry[0])) {
            return parametry[0] + " nemáš v batohu!\n";
        }

        Vec vec = herniPlan.getBatoh().odeberZBatohu(parametry[0]);
        herniPlan.getVybava().nasadSi(vec);
        return "";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
