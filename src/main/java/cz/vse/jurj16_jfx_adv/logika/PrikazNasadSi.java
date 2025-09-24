package cz.vse.jurj16_jfx_adv.logika;

/**
 * Třída PrikazNasadSi implementuje pro hru příkaz nasaď si.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Nasad si přesouvá věci z batohu do výbavy
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazNasadSi implements IPrikaz {

    private static final String NAZEV = "nasaď si";
    private final HerniPlan herniPlan;


    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @return this
     */
    public PrikazNasadSi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Provede příkaz nasaď si
     *
     * Přesune věc z batohu do výbavy
     *
     * @param parametry název předmětu v batohu.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 1) {
            return "Příkaz 'nandej si' přijímá pouze jeden parametr\n";
        }
        if (!herniPlan.getBatoh().obsahujeVec(parametry[0])) {
            return parametry[0] + " nemáš v batohu!\n";
        }

        Vec vec = herniPlan.getBatoh().odeberZBatohu(parametry[0]);
        herniPlan.getVybava().nasadSi(vec);
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
