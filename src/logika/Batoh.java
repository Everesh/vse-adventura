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
        return "Mas v batohu: " + vypisBatohu();

    }

    private String vypisBatohu() {
        String vyis = "";
        for (Vec vec : seznamVeci) {
            vyis += vec.getNazev() + " ";
        }
        return vyis;
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
