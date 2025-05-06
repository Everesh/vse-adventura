package logika;

public class PrikazRekni implements IPrikaz {
    private static final String NAZEV = "řekni";
    private HerniPlan herniPlan;

    public PrikazRekni(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry == null) {
            return "\"\"\n";
        }
        return "\"" + String.join(" ", parametry) + "\"\n";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}