package cz.vse.jurj16_jfx_adv.logika;

/**
 * Třída PrikazAbrakadabra implementuje pro hru příkaz abrakadabra.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Přenese hráče do jiného prostoru
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazAbrakadabra implements IPrikaz {
    private static final String NAZEV = "abrakadabra";
    private final HerniPlan herniPlan;
    private final Prostor prostor_abrakadabra;

    /**
     * Konstruktor třídy
     * @param herniPlan
     * @return this
     */
    public PrikazAbrakadabra(HerniPlan herniPlan) {

        this.herniPlan = herniPlan;
        this.prostor_abrakadabra = herniPlan.getProstor("abrakadabra");
    }

    /**
     * Metoda provede příkaz abrakadabra
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return String
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Příkaz abrakadabra nepříjmá žádné parametry!\n";
        }

        if ("abrakadabra".equals(herniPlan.getAktualniProstor().getNazev())) {
            herniPlan.setAktualniProstor(herniPlan.getPreMagicOrigin());
            return "";
        }

        if ("okraj reality".equals(herniPlan.getAktualniProstor().getNazev())) {
            herniPlan.setAktualniProstor(prostor_abrakadabra);
            return "Eoeoeoeeeee... teleportuješ se zpět!\n";
        }

        herniPlan.setPreMagicOrigin(herniPlan.getAktualniProstor());
        herniPlan.setAktualniProstor(prostor_abrakadabra);
        return "";
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
