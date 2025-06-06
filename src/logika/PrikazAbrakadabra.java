package logika;

public class PrikazAbrakadabra implements IPrikaz {
    private static final String NAZEV = "abrakadabra";
    private final HerniPlan herniPlan;
    private final Prostor prostor_abrakadabra;

    public PrikazAbrakadabra(HerniPlan herniPlan) {

        this.herniPlan = herniPlan;
        this.prostor_abrakadabra = herniPlan.getProstor("abrakadabra");
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Příkaz abrakadabra nepříjmá žádné parametry!\n";
        }

        if (herniPlan.getAktualniProstor().getNazev().equals("abrakadabra")) {
            herniPlan.setAktualniProstor(herniPlan.getPreMagicOrigin());
            return "";
        }

        if (herniPlan.getAktualniProstor().getNazev().equals("okraj reality")) {
            herniPlan.setAktualniProstor(prostor_abrakadabra);
            return "Eoeoeoeeeee... teleportuješ se zpět!\n";
        }

        herniPlan.setPreMagicOrigin(herniPlan.getAktualniProstor());
        herniPlan.setAktualniProstor(prostor_abrakadabra);
        return "";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
