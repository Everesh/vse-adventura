package logika;

public class PrikazMluvS implements IPrikaz {
    private static final String NAZEV = "mluv s";
    private final HerniPlan herniPlan;

    public PrikazMluvS(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz mluv s příjmá přesně jeden parametr!";
        }

        if (parametry[0].equals("čarokněžník") && herniPlan.getAktualniProstor().obsahujeVec("čarokněžník")) {
            if (herniPlan.getBatoh().obsahujeVec("tabula_rasa")) {
                // TODO vyhra
            } else {
                return "Výtej vyzivateli, vyvoláním prastaré kledby A-brak-A-da-BRA si splnil první z testů pro\n" +
                       "osvojení mutidimenzionální teleportace. Nicméně druhý bude namáhavější. Použí kledbu\n" +
                       "čA--Rym-ARy-fuk a zjiskej pro mě tabulu rasu, incarnaci originální magie of incarnace osudu!\n";
            }
        }

        if (parametry[0].equals("sfinga") && herniPlan.getAktualniProstor().obsahujeVec("sfinga")) {
            return "Hmm, ten starý blázen našel dalšího blázna pokoušet osud.\n" +
                   "Nuže dobrá, chceš-li tabulu rasu, řekni mi génie, kolike je 2 + 2?\n";
        }

        return "S " + parametry[0] + " se nedá mluvit!";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
