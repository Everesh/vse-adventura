package cz.vse.jurj16_jfx_adv.logika;

import cz.vse.jurj16_jfx_adv.main.ZmenaHry;

/**
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Příkaz seber přesune věc z prostoru do batohu
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";

    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @return this
     */
    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Provede příkaz seber
     *
     * Přesune předmět z prostoru do batohu
     *
     * @param parametry název věci.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat jméno věci!\n";
        }

        if (parametry.length > 1) {
            return "Každá věc je jednoslovná a musí se sebrat zvlášť!\n";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.vyberVec(nazevVeci);

            if (pozadovanaVec == null) {
                return nazevVeci + " se nedá přenést!\n";
            } else {
                boolean povedloSeVlozit = herniPlan.getBatoh().vlozDoBatohu(pozadovanaVec);
                if (povedloSeVlozit) {
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_BATOHU);
                    return "";
                } else {
                    return nazevVeci + " se ti do batohu nevejde, musíš je prvně vyprázdnit\n";
                }
            }
        } else {
            return nazevVeci + " není v tomto prostoru!\n";
        }
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
