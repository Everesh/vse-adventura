package cz.vse.jurj16_jfx_adv.logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída Vybava implementuje pro hru výbavu a její správu.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jan Jurka
 * @version pro školní rok 2024/2025
 */
public class Vybava {

    private final List<Vec> seznamVeci;

    /**
     * Vytvoří novou výbavu s definovanou velikostí
     *
     * @return this
     */
    public Vybava() {
        seznamVeci = new ArrayList<>();
    }

    /**
     * Vrati seznam veci ze seznamVeci předepsaný stringem "Máš na sobě:"
     *
     * @return seznam vybavených věcé
     */
    public String dlouhyPopis() {
        return "Máš na sobě:" + vypisVybavy();
    }

    /**
     * Vrati seznam veci ze seznamVeci
     *
     * @return seznam vybavených věcé
     */
    private String vypisVybavy() {
        String vyis = "";
        for (Vec vec : seznamVeci) {
            vyis += (vyis.length() > 0 ? ", " : " ") + vec.getNazev();
        }
        return vyis;
    }

    /**
     * Pokusí se vybavit věc jeli nositelná
     *
     * @param vec věc kterou se metoda pokusí vybavit
     * @return bool zda se nasazení povedlo
     */
    public boolean nasadSi(Vec vec) {
        if (vec.jeNositelna()) {
            seznamVeci.add(vec);
            return true;
        }
        return false;
    }

    /**
     * Pokusí se sundat věc z vybavebí
     *
     * @param nazev nazev věci kterou se pokusí metoda sundat
     * @return bool zda se sundání povedlo
     */
    public Vec sundejSi(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                seznamVeci.remove(vec);
                return vec;
            }
        }
        return null;
    }

    /**
     * Vrátí informaci zda je věc vybavená
     *
     * @param nazev nazev věci jejíž nasazenost se testuje
     * @return bool
     */
    public boolean masNaSobe(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }

    public List<Vec> getVybaveni() {
        return seznamVeci;
    }
}
