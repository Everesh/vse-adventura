package cz.vse.jurj16_jfx_adv.logika;

import cz.vse.jurj16_jfx_adv.main.Pozorovatel;
import cz.vse.jurj16_jfx_adv.main.PredmetPozorovani;
import cz.vse.jurj16_jfx_adv.main.ZmenaHry;

import java.util.*;

/**
 * Třída Vec implementuje pro hru věc a její parametry.
 * Tato třída je součástí jednoduché textové hry.
 *
 * Třída definuje interakce mezi věcmi pro příkaz použij
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class Vec {
    private final String nazev;
    private final boolean prenositelnost;
    private final boolean nositelnost;
    private final Prostor prozkoumej;
    private final HerniPlan herniPlan;

    /**
     * Vytvoří předmět se zadaným názvem a přenositelnostní
     *
     * @param nazev          Jméno věci jednoznačný identifikátor
     * @param prenositelnost Určuje zda se dá věc odnést z místnosti
     * @param nositelnost    Určuje zda se dá věc bléct
     * @param prozkoumej     pseudoProstor předmětu (většinou null)
     * @param herniPlan      reference na herniPlan
     */
    public Vec(String nazev, boolean prenositelnost, boolean nositelnost, Prostor prozkoumej, HerniPlan herniPlan) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.nositelnost = nositelnost;
        this.prozkoumej = prozkoumej;
        this.herniPlan = herniPlan;
    }

    /**
     * Vrátí název předmětu
     *
     * @return název
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí boolean s informací o přenositelnosti
     *
     * @return bool
     */
    public boolean jePrenositelna() {
        return prenositelnost;
    }

    /**
     * Vrátí boolean s informací o nositelnosti
     *
     * @return bool
     */
    public boolean jeNositelna() {
        return nositelnost;
    }

    /**
     * Vrátí boolean s informací o existenci pseudoProstoru
     *
     * @return bool
     */
    public boolean maProstor() {
        return prozkoumej != null;
    }

    /**
     * Vrátí pseudoProstor věci
     *
     * @return Prostor
     */
    public Prostor getProstor() {
        return prozkoumej;
    }

    /**
     * Realizuje logiku příkazu použij
     *
     * @param vec vec se kterou tato vec bude interagovat.
     * @return Error message ("" pokud vše proběhne v pořádku)
     */
    public String pouzij(Vec vec) {
        switch (nazev) {
            case "dveře_celi_1":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_1");
                    Prostor cela1 = herniPlan.getProstor("cela_1");
                    herniPlan.getAktualniProstor().setVychod(cela1);
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            case "dveře_celi_2":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_2");
                    Prostor cela2 = herniPlan.getProstor("cela_2");
                    herniPlan.getAktualniProstor().setVychod(cela2);
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            case "dveře_celi_3":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_3");
                    Prostor cela3 = herniPlan.getProstor("cela_3");
                    herniPlan.getAktualniProstor().setVychod(cela3);
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            case "zámek_okovů":
                if (Objects.equals(vec.getNazev(), "klíč_od_okovů")) {
                    herniPlan.getAktualniProstor().odeberVec("zámek_okovů");
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            case "díra_ve_stěně":
                if (Objects.equals(vec.getNazev(), "okovy")) {
                    herniPlan.getBatoh().odeberZBatohu("okovy");
                    Prostor slanitSe = herniPlan.getProstor("slanit_se_po_okovech_na_straně_hradu");
                    herniPlan.getAktualniProstor().setVychod(slanitSe);
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            case "hlavní_brána":
                if (Objects.equals(vec.getNazev(), "klíč_od_hlavní_brány")) {
                    herniPlan.getBatoh().odeberZBatohu("klíč_od_hlavní_brány");
                    herniPlan.getAktualniProstor().odeberVec("hlavní_brána");
                    Prostor nadvori = herniPlan.getProstor("nádvoří");
                    herniPlan.getAktualniProstor().setVychod(nadvori);
                    herniPlan.upozorniPozorovatele(ZmenaHry.ZMENA_MISTNOSTI);
                    break;
                }
            default:
                return vec.getNazev() + " se nedá použít na " + nazev;
        }
        return "";
    }
}
