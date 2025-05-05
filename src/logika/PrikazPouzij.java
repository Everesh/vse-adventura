package logika;

public class PrikazPouzij implements IPrikaz{
    private static final String NAZEV = "použij";
    private HerniPlan herniPlan;

    public PrikazPouzij(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 3) {
            return "Příkaz 'použij' potřebuje 2 parametry spojené slovem 'na'";
        }

        Vec vec1 = herniPlan.getAktualniProstor().peekVec(parametry[0]);
        if (vec1 == null) {
            vec1 = herniPlan.getBatoh().peekDoBatohu(parametry[0]);
        }
        if (vec1 == null) {
            return "První věc není v tvém batohu ani aktuální místnosti!";
        }

        Vec vec2 = herniPlan.getAktualniProstor().peekVec(parametry[2]);
        if (vec2 == null) {
            return "Druhá věc není v aktuální místnosti!";
        }

        return vec2.pouzij(vec1);
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
