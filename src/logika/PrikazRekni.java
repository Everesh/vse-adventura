package logika;

public class PrikazRekni implements IPrikaz {
    private static final String NAZEV = "řekni";
    private HerniPlan herniPlan;

    public PrikazRekni(HerniPlan herniPlan) {
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
