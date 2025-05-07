package logika;

/**
 *  Třída PrikazUder implementuje pro hru příkaz udeř.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  Příkaz udeř se pokusí udeřit věc
 *  v závislosti na obsah batohu a věc kterou se hráč snaží udeřit může vést k následkům
 *  
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class PrikazUder implements IPrikaz {
    private static final String NAZEV = "udeř";
    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor třídy
     * @param herniPlan
     * @param hra
     * @return this
     */
    public PrikazUder(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Provede příkaz udeř
     *
     * Udeří předmět
     *
     * @param parametry název věci.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
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
            case "král":
                hra.setEpilog("Tvůj útok na krále byl neuvěřitelně pošetilý. Dřív než jsi stačil zasadit ránu, král si všiml tvé řeči těla a zavolal stráž. Byl jsi okamžitě zpacifikován a odvlečen na popraviště. Tvůj osud byl zpečetěn. Prohrál jsi, snad příště zvolíš moudřeji.");
                hra.setKonecHry(true);
                return "";
            default:
                return parametry[0] + " je odolný úderům\n";
        }
        return "";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}