package logika;

public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";

    private HerniPlan herniPlan;

    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat jméno věci!";
        }

        if (parametry.length > 1) {
            return "Každá věc je jednoslovná a musí se sebrat zvlášť!";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.vyberVec(nazevVeci);

            if (pozadovanaVec == null) {
                return nazevVeci + " se nedá přenést!";
            } else {
                boolean povedloSeVlozit = herniPlan.getBatoh().vlozDoBatohu(pozadovanaVec);
                if (povedloSeVlozit) {
                    return nazevVeci + " jsi vložil do batohu";
                } else {
                    // TODO dodelat do konce
                    return nazevVeci + " se ti do batohu nevejde, musíš je prvně vyprázdnit";
                }
            }
        } else {
            return nazevVeci + " není v tomto prostoru!";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
