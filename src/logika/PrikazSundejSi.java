package logika;

public class PrikazSundejSi implements IPrikaz {
    private static final String NAZEV = "sundej si";
    private HerniPlan herniPlan;

    public PrikazSundejSi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz 'sundej si' přijímá pouze jeden parametr\n";
        }
        if (!herniPlan.getVybava().masNaSobe(parametry[0])) {
            return parametry[0] + " nemáš na sobě\n";
        }
        herniPlan.getBatoh().vlozDoBatohu(herniPlan.getVybava().sundejSi(parametry[0]));
        return "";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
