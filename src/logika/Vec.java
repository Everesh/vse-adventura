package logika;

public class Vec {
    private String nazev;
    private boolean prenositelnost;
    private boolean nositelnost;

    /**
     * Vytvoří předmět se zadaným názvem a přenositelnostní
     * @param nazev Jméno věci jednoznačný identifikátor
     * @param prenositelnost Určuje zda se dá věc odnést z místnosti
     */
    public Vec(String nazev, boolean prenositelnost, boolean nositelnost) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.nositelnost = nositelnost;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean jePrenositelnost() {
        return prenositelnost;
    }

    public boolean jeNositelna() {
        return nositelnost;
    }
}
