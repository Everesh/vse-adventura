package logika;

public class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "polož";
    private HerniPlan herniPlan;
    private Batoh batoh;


    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        this.batoh = herniPlan.getBatoh();
        if (parametry.length == 0) {
            return "Co mám položit, dej mi název věci!";
        }

        if (parametry.length > 1) {
            return "Každá věc má jednoslovný název a musí se položit zvlášť!";
        }

        String nazevVeci = parametry[0];

        if (!batoh.obsahujeVec(nazevVeci)) {
            return nazevVeci + " nemáš v batohu!";
        }

        Vec pozadovanaVec = batoh.odeberZBatohu(nazevVeci);
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        aktualniProstor.vlozVec(pozadovanaVec);
        return "Položil(a) jsi " + nazevVeci + ".";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
