package cz.vse.jurj16_jfx_adv.logika;

/**
 * Třída PrikazCarymaryfuk implementuje pro hru příkaz carymaryfuk.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Přenese hráče do jiného prostoru či vyhodí chybu dle hráčovi lokace
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazCarymaryfuk implements IPrikaz {
    private static final String NAZEV = "carymaryfuk";
    private final HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     * @param herniPlan
     * @return this
     */
    public PrikazCarymaryfuk(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda provede příkaz carymaryfuk
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return String
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Příkaz carymaryfuk příjmá přesně jeden parametr!";
        }

        if ("abrakadabra".equals(herniPlan.getAktualniProstor().getNazev())) {
            herniPlan.setAktualniProstor(herniPlan.getProstor("okraj reality"));
            return "Hwazaaaaa... Teleportuješ se\n";
        } else if ("okraj reality".equals(herniPlan.getAktualniProstor().getNazev())) {
            herniPlan.setAktualniProstor(herniPlan.getPreMagicOrigin());
            return "Hwazaaaaa... Teleportuješ se\n";
        }

        throw new RuntimeException("Carymaryfuk mimo dimenzionální linije zničil program!");
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
