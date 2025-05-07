package logika;

import java.util.Objects;

public class Vec {
    private String nazev;
    private boolean prenositelnost;
    private boolean nositelnost;
    private Prostor prozkoumej;
    private HerniPlan herniPlan;

    /**
     * Vytvoří předmět se zadaným názvem a přenositelnostní
     * @param nazev Jméno věci jednoznačný identifikátor
     * @param prenositelnost Určuje zda se dá věc odnést z místnosti
     */
    public Vec(String nazev, boolean prenositelnost, boolean nositelnost, Prostor prozkoumej, HerniPlan herniPlan) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.nositelnost = nositelnost;
        this.prozkoumej = prozkoumej;
        this.herniPlan = herniPlan;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean jePrenositelna() {
        return prenositelnost;
    }

    public boolean jeNositelna() {
        return nositelnost;
    }

    public boolean maProstor() {
        return prozkoumej != null;
    }

    public Prostor getProstor() {
        return prozkoumej;
    }

    // Best practices be damned, Im hard coding this
    public String pouzij(Vec vec) {
        switch (nazev) {
            case "dveře_celi_1":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_1");
                    Prostor cela1 = herniPlan.getProstor("cela_1");
                    herniPlan.getAktualniProstor().setVychod(cela1);
                    break;
                }
            case "dveře_celi_2":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_2");
                    Prostor cela2 = herniPlan.getProstor("cela_2");
                    herniPlan.getAktualniProstor().setVychod(cela2);
                    break;
                }
            case "dveře_celi_3":
                if (Objects.equals(vec.getNazev(), "klíč_od_cel")) {
                    herniPlan.getAktualniProstor().odeberVec("dveře_celi_3");
                    Prostor cela3 = herniPlan.getProstor("cela_3");
                    herniPlan.getAktualniProstor().setVychod(cela3);
                    break;
                }
            case "zámek_okovů":
                if (Objects.equals(vec.getNazev(), "klíč_od_okovů")) {
                    herniPlan.getAktualniProstor().odeberVec("zámek_okovů");
                    break;
                }
            case "díra_ve_stěně":
                if (Objects.equals(vec.getNazev(), "okovy")) {
                    herniPlan.getBatoh().odeberZBatohu("okovy");
                    herniPlan.getAktualniProstor().setVychod(new Prostor("slanit_se_po_okovech_na_straně_hradu", ""));
                    break;
                }
            case "hlavní_brána":
                if (Objects.equals(vec.getNazev(), "klíč_od_hlavní_brány")) {
                    herniPlan.getBatoh().odeberZBatohu("klíč_od_hlavní_brány");
                    herniPlan.getAktualniProstor().odeberVec("hlavní_brána");
                    herniPlan.getAktualniProstor().setVychod(new Prostor("nádvoří", ""));
                    break;
                }
            default:
                return vec.getNazev() + " se nedá použít na " + nazev;
        }
        return "";
    }
}
