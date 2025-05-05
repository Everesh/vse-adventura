package logika;

public class PrikazPouzij implements IPrikaz{
    private static final String NAZEV = "použij";
    private HerniPlan herniPlan;

    public PrikazPouzij(HerniPlan herniPlan) {
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
