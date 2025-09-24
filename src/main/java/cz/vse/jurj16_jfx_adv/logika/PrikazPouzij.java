package cz.vse.jurj16_jfx_adv.logika;

/**
 * Třída PrikazPouzij implementuje pro hru příkaz použij.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Příkaz spravuje párovaní věcí pro metodu třídy Vec, pouzij
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazPouzij implements IPrikaz {
    private static final String NAZEV = "použij";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @return this
     */
    public PrikazPouzij(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Provede příkaz použi x na y
     *
     * Pokusí se použít věc x na y (logika použití realizována v rámci třídy Vec)
     *
     * @param parametry název předmětu v batohu.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
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
