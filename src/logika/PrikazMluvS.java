package logika;

/**
 * Třída PrikazMluvS implementuje pro hru příkaz mluv s.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Příkaz mluv s se pokusí promluvit s věcí
 * v závislosti na obsah batohu a může vést k odlišným výsledkům
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazMluvS implements IPrikaz {
    private static final String NAZEV = "mluv s";
    private final HerniPlan herniPlan;
    private final Hra hra;

    /**
     * Konstruktor třídy
     * @param herniPlan
     * @return this
     */
    public PrikazMluvS(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Metoda provede příkaz mluv s
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return String
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz mluv s příjmá přesně jeden parametr!";
        }

        if (parametry[0].equals("čarokněžník") && herniPlan.getAktualniProstor().obsahujeVec("čarokněžník")) {
            if (herniPlan.getBatoh().obsahujeVec("tabula_rasa")) {
                hra.setEpilog("Čarokněžník s jiskrou v oku nadšeně převezme tabulu rasu. Na oplátku tě vezme jako svého učedníka!\n" +
                              "Vyhrál si! Dobrá práce!\n");
                hra.setKonecHry(true);
                return "";
            } else {
                return "Výtej vyzivateli, vyvoláním prastaré kledby A-brak-A-da-BRA si splnil první z testů pro\n" +
                       "osvojení mutidimenzionální teleportace. Nicméně druhý bude namáhavější. Použí kledbu\n" +
                       "čA--Rym-ARy-fuk a zjiskej pro mě tabulu rasu, incarnaci originální magie of incarnace osudu!\n";
            }
        }

        if (parametry[0].equals("sfinga") && herniPlan.getAktualniProstor().obsahujeVec("sfinga")) {
            return "Hmm, ten starý blázen našel dalšího nešťastníka a přesvědčil jej pokoušet osud.\n" +
                   "Nuže dobrá, chceš-li tabulu rasu, řekni mi génie, kolike je 2 + 2?\n";
        }

        return "S " + parametry[0] + " se nedá mluvit!";
    }

    /**
     * Metoda vrátí nazev příkazu
     * @return String
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
