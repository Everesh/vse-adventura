package logika;

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
        herniPlan.getBatoh().vlozDoBatohu(herniPlan.getVybava().sundejSi(parametry[0]));
        return "";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }

}
