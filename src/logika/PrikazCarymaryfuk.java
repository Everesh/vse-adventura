package logika;

public class PrikazCarymaryfuk implements IPrikaz {
    private static final String NAZEV = "carymaryfuk";
    private final HerniPlan herniPlan;

    public PrikazCarymaryfuk(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Příkaz carymaryfuk příjmá přesně jeden parametr!";
        }

        if (herniPlan.getAktualniProstor().getNazev().equals("abrakadabra")) {
            herniPlan.setAktualniProstor(herniPlan.getProstor("okraj reality"));
            return "Hwazaaaaa... Teleportuješ se\n";
        } else if (herniPlan.getAktualniProstor().getNazev().equals("okraj reality")) {
            herniPlan.setAktualniProstor(herniPlan.getPreMagicOrigin());
            return "Hwazaaaaa... Teleportuješ se\n";
        }

        throw new RuntimeException("Carymaryfuk mimo dimenzionální linije zničil program!");
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
