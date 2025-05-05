package logika;

public class Vec {
    private String nazev;
    private boolean prenositelnost;
    private boolean nositelnost;
    private Prostor prozkoumej;

    /**
     * Vytvoří předmět se zadaným názvem a přenositelnostní
     * @param nazev Jméno věci jednoznačný identifikátor
     * @param prenositelnost Určuje zda se dá věc odnést z místnosti
     */
    public Vec(String nazev, boolean prenositelnost, boolean nositelnost, Prostor prozkoumej) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.nositelnost = nositelnost;
        this.prozkoumej = prozkoumej;
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
}
