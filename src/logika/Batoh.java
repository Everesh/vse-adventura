package logika;

import java.util.ArrayList;
import java.util.List;

/**
 *  Třída Batoh implementuje pro hru batoh a jeho správu.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class Batoh {

    private List<Vec> seznamVeci;
    private int omezeniBatohu;

    /**
     *  Vytvoří nový batoh s definovanou velikostí
     * @param omezeniBatohu
     * @return this
     */
    public Batoh(int omezeniBatohu) {
        seznamVeci = new ArrayList<>();
        this.omezeniBatohu = omezeniBatohu;
    }

    /**
     * Vrati seznam veci ze seznamVeci předepsaný stringem "Seznam věcí v batohu:"
     * @return
     */
    public String dlouhyPopis() {
        return "Seznam věcí v batohu:" + vypisBatohu();

    }

    /**
     * Vrati seznam veci ze seznamVeci
     * @return
     */
    private String vypisBatohu() {
        String vypis = "";
        for (Vec vec : seznamVeci) {
            vypis += (vypis.isEmpty() ? " " : ", ") + vec.getNazev();
        }
        return vypis;
    }

    /**
     * Vlozi věc do batohu máli volné místo a vrátí bool zda se operace povedla
     * @param vec
     * @return boolean
     */
    public boolean vlozDoBatohu(Vec vec) {
        if (seznamVeci.size() < omezeniBatohu) {
            seznamVeci.add(vec);
            return true;
        }
        return false;
    }

    /**
     * Odebere predmet ze seznamu veci a vrati ho existujeli
     * @param nazev
     * @return Vec
     */
    public Vec odeberZBatohu(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                seznamVeci.remove(vec);
                return vec;
            }
        }
        return null;
    }

    /**
     * Vrátí predmet ze seznamu veci aniž by jej odstranila
     * @param nazev
     * @return Vec
     */
    public Vec peekDoBatohu(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return vec;
            }
        }
        return null;
    }

    /**
     * Vrátí bool informujici zda je predmet v batohu
     * @param nazev
     * @return boolean
     */
    public boolean obsahujeVec(String nazev) {
        for(Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }
}
