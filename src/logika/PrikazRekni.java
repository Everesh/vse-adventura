package logika;

public class PrikazRekni implements IPrikaz {
    private static final String NAZEV = "řekni";
    private HerniPlan herniPlan;

    public PrikazPoloz(HerniPlan herniPlan) {
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
