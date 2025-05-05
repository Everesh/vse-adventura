package logika;

public class PrikazSundejSi implements IPrikaz {
    private static final String NAZEV = "sundej si";
    private HerniPlan herniPlan;

    public PrikazSundejSi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return "TODO";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
