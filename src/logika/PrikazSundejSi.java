package logika;

import java.util.Objects;

/**
 * Třída PrikazSundejSi implementuje pro hru příkaz sundej si.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Příkaz sundej si přesune věc z výbavy do batohu
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazSundejSi implements IPrikaz {
    private static final String NAZEV = "sundej si";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @return this
     */
    public PrikazSundejSi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Provede příkaz sundej si
     *
     * Přesune předmět z výbavy do batohu
     *
     * @param parametry název věci.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz 'sundej si' přijímá pouze jeden parametr\n";
        }
        if (!herniPlan.getVybava().masNaSobe(parametry[0])) {
            return parametry[0] + " nemáš na sobě\n";
        }
        if (Objects.equals(parametry[0], "okovy") && herniPlan.getProstor("moje_cela").obsahujeVec("zámek_okovů")) {
            return "Okovy si nemůžeš momentálně sundat, jsou zabezpečeny zámkem na stěně tvé cely!\n";
        }

        herniPlan.getBatoh().vlozDoBatohu(herniPlan.getVybava().sundejSi(parametry[0]));
        return "";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
