package logika;

public class PrikazUder implements IPrikaz {
    private static final String NAZEV = "udeř";
    private HerniPlan herniPlan;
    private Hra hra;

    public PrikazUder(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz udeř vyžaduje jeden parametr\n";
        }
        if (!herniPlan.getAktualniProstor().obsahujeVec(parametry[0])) {
            return parametry[0] + " není v této místnosti!\n";
        }

        switch (parametry[0]) {
            case "nestabilní_stěna":
                if (herniPlan.getBatoh().obsahujeVec("palice")) {
                    herniPlan.getProstor("oubliette").odeberVec("nestabilní_stěna");
                    herniPlan.getProstor("oubliette").vlozVec(new Vec("díra_ve_stěně", false, false, null, herniPlan));
                } else {
                    return "Tvé ruce nemají proti kameni šanci, možná kdyby si měl nějáký nástroj\n";
                }
                break;
            case "stráž":
                hra.setEpilog("Tvůj neuvážený útok na stráž se ukázal být osudovou chybou. Strážný tě snadno přemohl a okamžitě v tobě rozpoznal uprchlého vězně. Byl jsi odvlečen zpět do cely, kde budeš trávit zbytek svých dní. Prohrál jsi, snad budeš mít příště více štěstí.");
                hra.setKonecHry(true);
                return "";
            default:
                return parametry[0] + " je odolný úderům\n";
        }
        return "";
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
