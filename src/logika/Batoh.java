package logika;

import java.util.ArrayList;
import java.util.List;

public class Batoh {

    private List<Vec> seznamVeci;
    private int omezeniBatohu;

    public Batoh(int omezeniBatohu) {
        seznamVeci = new ArrayList<>();
        this.omezeniBatohu = omezeniBatohu;
    }

    public String dlouhyPopis() {
        return "Seznam věcí v batohu:" + vypisBatohu();

    }

    private String vypisBatohu() {
        String vypis = "";
        for (Vec vec : seznamVeci) {
            vypis += (vypis.isEmpty() ? " " : ", ") + vec.getNazev();
        }
        return vypis;
    }

    public boolean vlozDoBatohu(Vec vec) {
        if (seznamVeci.size() < omezeniBatohu) {
            seznamVeci.add(vec);
            return true;
        }
        return false;
    }

    public Vec odeberZBatohu(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                seznamVeci.remove(vec);
                return vec;
            }
        }
        return null;
    }

    public boolean obsahujeVec(String nazev) {
        for(Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }
}
