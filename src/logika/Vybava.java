package logika;

import java.util.ArrayList;
import java.util.List;

/**
 *  Třída Vybava implementuje pro hru výbavu a její správu.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jan Jurka
 *@version    pro školní rok 2024/2025
 */
public class Vybava {

    private List<Vec> seznamVeci;

    public Vybava() {
        seznamVeci = new ArrayList<>();
    }

    public String dlouhyPopis() {
        return "Máš na sobě:" + vypisVybavy();
    }

    private String vypisVybavy() {
        String vyis = "";
        for (Vec vec : seznamVeci) {
            vyis += (vyis.length() > 0 ? ", " : " ") + vec.getNazev();
        }
        return vyis;
    }

    public boolean nasadSi(Vec vec) {
        if (vec.jeNositelna()) {
            seznamVeci.add(vec);
            return true;
        }
        return false;
    }

    public Vec sundejSi(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                seznamVeci.remove(vec);
                return vec;
            }
        }
        return null;
    }

    public boolean masNaSobe(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }
}
