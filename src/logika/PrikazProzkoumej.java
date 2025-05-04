package logika;

public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan herniPlan;

    public PrikazProzkoumej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat? Musíš zadat jméno věci!";
        }

        if (parametry.length > 1) {
            return "Každá věc je jednoslovná a musí se zkoumat zvlášť!";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.vyberVec(nazevVeci);

            // TODO

        } else {
            return nazevVeci + " není v tomto prostoru!";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
