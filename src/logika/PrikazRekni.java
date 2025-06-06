package logika;

import java.util.Arrays;

/**
 * Třída PrikazRekni implementuje pro hru příkaz řekni.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Příkaz řekni vrátí parametry předepsané stringem "Řekl si: "
 * Vyslovení konkrétního slova v konkrétním prostoru může mít další následky
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class PrikazRekni implements IPrikaz {
    private static final String NAZEV = "řekni";
    private final HerniPlan herniPlan;
    private final Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan
     * @return this
     */
    public PrikazRekni(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Provede příkaz rekni
     *
     * Vrátí parametry předepsané stringem "Řekl si: "
     * Vyslovení konkrétního slova v konkrétním prostoru může mít další následky
     *
     * @param parametry string co hráč pronese.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry == null) {
            return "Řekl si: \"\"\n";
        } else if ("koruní_sál".equals(herniPlan.getAktualniProstor().getNazev()) &&
                Arrays.stream(parametry).anyMatch(str -> "ashbourne".equalsIgnoreCase(str))) {
            herniPlan.getAktualniProstor().odeberVec("král");
            Prostor prostorKralovoTelo = new Prostor("", "Prozkoumáváš královo_tělo");
            herniPlan.getAktualniProstor().vlozVec(new Vec("královo_tělo", false, false, prostorKralovoTelo, herniPlan));
            prostorKralovoTelo.setVychod(herniPlan.getAktualniProstor());
            Vec klicOdBrany = new Vec("klíč_od_hlavní_brány", true, false, null, herniPlan);
            prostorKralovoTelo.vlozVec(klicOdBrany);
            Vec koruna = new Vec("koruna", true, true, null, herniPlan);
            prostorKralovoTelo.vlozVec(koruna);
            return "Řekl jsi: \"" + String.join(" ", parametry) + "\"\n" +
                    "Král zbledl, jako by ho zasáhl blesk.\n" +
                    "\"Ashbourne... ne... ne znovu...\"\n" +
                    "Otřásl se, oči se mu zaleskly hrůzou a vzápětí se bezvládně sesunul na trůn.\n" +
                    "Král omdlel!\n";
        } else if ("okraj reality".equals(herniPlan.getAktualniProstor().getNazev())) {
            if (Arrays.stream(parametry).anyMatch(str -> "4".equalsIgnoreCase(str))) {
                Vec tabulaRasa = new Vec("tabula_rasa", true, false, null, herniPlan);
                herniPlan.getAktualniProstor().vlozVec(tabulaRasa);
                return "Řekl jsi: \"" + String.join(" ", parametry) + "\"\n" +
                       "Sfinga se na tebe udiveně podíva s obdivem na tváři. Takové ohromení je hodno odměny!\n" +
                       "Sfinga před tebe položí tabulu rasu!\n";
            } else {
                hra.setEpilog("Řekl jsi: \"" + String.join(" ", parametry) + "\"\n" +
                              "Sfinga na tebe vrhne opovrhavý pohled. Nádech, povzdech a spálí tě na uhel svým pohledem.\n" +
                              "Prohrál si! Výce štěstí příště!\n");
                hra.setKonecHry(true);
                return "";
            }
        }
        return "Řekl si: \"" + String.join(" ", parametry) + "\"\n";
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