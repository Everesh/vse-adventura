package logika;

public class PrikazAbrakadabra implements IPrikaz {
    private static final String NAZEV = "abrakadabra";
    private final HerniPlan herniPlan;
    private Prostor puvod;
    private final Prostor prostor_abrakadabra;

    public PrikazAbrakadabra(HerniPlan herniPlan) {

        this.herniPlan = herniPlan;
        this.puvod = null;
        this.prostor_abrakadabra = herniPlan.getProstor("abrakadabra");
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Příkaz abrakadabra nepříjmá žádné parametry!";
        }

        if (herniPlan.getAktualniProstor().getNazev().equals("abrakadabra")) {
            herniPlan.setAktualniProstor(puvod);
            return "";
        }

        this.puvod = herniPlan.getAktualniProstor();
        herniPlan.setAktualniProstor(prostor_abrakadabra);
        return "";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
