package logika;

import java.util.Arrays;

/**
 * Třída Hra - třída představující logiku adventury.
 *
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 * a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */

public class Hra implements IHra {
    // obsahuje seznam přípustných příkazů
    private final SeznamPrikazu platnePrikazy;
    private final HerniPlan herniPlan;
    private boolean konecHry = false;
    // Default fallback epilog, měl by být přepsán v závislosti na způsob zakončení hry
    private String epilog = "Hra končí. Dík, že jste si zahráli.";

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRekni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUder(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazNasadSi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSundejSi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazAbrakadabra(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluvS(herniPlan));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return String
     */
    public String vratUvitani() {
        return "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                herniPlan.getVybava().dlouhyPopis() + "\n" +
                herniPlan.getBatoh().dlouhyPopis() + "\n" +
                herniPlan.getAktualniProstor().seznamVeciDlouhy() + "\n" +
                herniPlan.getAktualniProstor().seznamVychodyDlouhy();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return String
     */
    public String vratEpilog() {
        return epilog;
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * Z důvodu mé krátkozrakosti je vyžadována metoda s parametrem mým původním testovacím scénářem
     * v reálné produkci by byla depreciated
     *
     * @param _epilog - irelevantní, pouze pro kompatibilitu
     * @return String
     */
    public String vratEpilog(String _epilog) {
        return epilog + "\n";
    }

    /**
     * Moznost upravit epilog hry
     *
     * @param epilog - nový řetězec epilogu
     */
    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }

    /**
     * Vrací true, pokud hra skončila.
     *
     * @return boolean
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];

        // Alias pro nápovědu
        if ("pomoc".equals(slovoPrikazu)) {
            slovoPrikazu = "nápověda";
        }

        String[] parametry = new String[slova.length - 1];
        System.arraycopy(slova, 1, parametry, 0, parametry.length);

        // Extrakce zvratného 'si'
        if (parametry.length > 0 && "si".equals(parametry[0])) {
            slovoPrikazu += " si";
            parametry = Arrays.copyOfRange(parametry, 1, parametry.length);
        }

        // Extrakce spojky 's'
        if (parametry.length > 0 && "s".equals(parametry[0])) {
            slovoPrikazu += " s";
            parametry = Arrays.copyOfRange(parametry, 1, parametry.length);
        }

        String textKVypsani = "";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);

            // Breakpoint - exit reached
            if (konecHry) {
                textKVypsani += vratEpilog() + "\n";
                return textKVypsani;
            }

            textKVypsani += herniPlan.getAktualniProstor().getPopis() + "\n";
            textKVypsani += herniPlan.getVybava().dlouhyPopis() + "\n";
            textKVypsani += herniPlan.getBatoh().dlouhyPopis() + "\n";

            // pseudoProstor handeling
            if ("".equals(herniPlan.getAktualniProstor().getNazev())) {
                textKVypsani += getPseudoProstorTextace();
            } else {
                textKVypsani += herniPlan.getAktualniProstor().seznamVeciDlouhy() + "\n";
            }

            textKVypsani += herniPlan.getAktualniProstor().seznamVychodyDlouhy();
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }

    /**
     * Řeší výpis poznatků jednotlivých pseudoProstorů
     * * pseudoProstor je prostor s názvem ""(prázdný řetězec)
     *
     * @return textace konkrétního pseudoProstoru
     */
    public String getPseudoProstorTextace() {
        String textKVypsani = "";
        textKVypsani = "Poznatky:";
        switch (herniPlan.getAktualniProstor().getPopis().split(" ")[1]) {
            case "myší_díra":
                textKVypsani += " - Uvnitř vidíš hromádku různých kovových objektů, nicméně jsou příliš daleko, než aby si je mohl zdvihnout";
                break;
            case "žalářníkův_diář":
                textKVypsani += " - Soužití žalářníka a myší v žaláři se nedávno zhoršilo kvůli jejich nově vznikající tendenci krást drobné kovové předměty\n";
                textKVypsani += "          - Před dvěma dny král z paranoie odvolal většinu členů stráže, nová stráž se stále seznamuje s poddanými sloužícími v prostorech hradu";
                break;
            case "králův_diář":
                textKVypsani += " - Král se chvěje při pouhém vyslovení \"Ashbourne.\" Je to jméno prokletého bojiště, kde přišel o své syny. ";
                break;
            default:
                textKVypsani += "";
        }
        return textKVypsani + "\nObsahuje:" + herniPlan.getAktualniProstor().seznamVeci() + "\n";
    }


    /**
     * Nastaví, že je konec hry
     *
     * @param konecHry hodnota true = konec hry, false = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

}