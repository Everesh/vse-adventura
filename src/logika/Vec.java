package logika;

public class Vec {
    private String nazev;
    private boolean prenositelnost;

    /**
     * Vytvoří předmět se zadaným názvem a přenositelnostní
     * @param nazev Jméno věci jednoznačný identifikátor
     * @param prenositelnost Určuje zda se dá věc odnést z místnosti
     */
    public Vec(String nazev, boolean prenositelnost) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean isPrenositelnost() {
        return prenositelnost;
    }
}
