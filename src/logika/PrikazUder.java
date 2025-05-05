package logika;

public class PrikazUder implements IPrikaz {
    private static final String NAZEV = "udeř";
    private HerniPlan herniPlan;

    public PrikazUder(HerniPlan herniPlan) {
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
