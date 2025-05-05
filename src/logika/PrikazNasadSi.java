package logika;

public class PrikazNasadSi implements IPrikaz {

    private static final String NAZEV = "nasaď si";
    private HerniPlan herniPlan;

    public PrikazNasadSi(HerniPlan herniPlan) {
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
