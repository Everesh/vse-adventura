package cz.vse.jurj16_jfx_adv.logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private final String nazev;
    private final String popis;
    private final List<Prostor> vychody;   // obsahuje sousední místnosti
    private final List<Vec> seznamVeci;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *              víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new ArrayList<>();
        seznamVeci = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor druhy)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return java.util.Objects.equals(this.nazev, druhy.nazev);
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrací popis prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return popis prostoru
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "Vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String seznamVychodyDlouhy() {
        return "Východy:" + seznamVychody();
    }

    /**
     * Vrací textový řetězec, který je prostým seznamem sousedních východů delimetrovaný ", "
     * Například: "hala, chodba, předsíň "
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String seznamVychody() {
        String vracenyText = "";
        for (Prostor sousedni : vychody) {
            vracenyText += (vracenyText.isEmpty() ? " " : ", ") + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Prida vec do seznamu veci
     *
     * @param neco
     */
    public void vlozVec(Vec neco) {
        seznamVeci.add(neco);
    }

    /**
     * Zkontroluje zda je v seznamu konkretni vec
     *
     * @param nazev
     * @return
     */
    public boolean obsahujeVec(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Odebere predmet ze seznamu veci jeli prenesitelny a vrati ho
     *
     * @param nazev
     * @return Vec
     */
    public Vec vyberVec(String nazev) {
        Vec vybranaVec = null;
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                vybranaVec = vec;
            }
        }

        if (vybranaVec != null && vybranaVec.jePrenositelna()) {
            seznamVeci.remove(vybranaVec);
        } else {
            vybranaVec = null;
        }

        return vybranaVec;
    }

    /**
     * Odebere predmet ze seznamu veci
     *
     * @param nazev
     * @return
     */
    public void odeberVec(String nazev) {
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                seznamVeci.remove(vec);
                return;
            }
        }
    }

    /**
     * Vrátí predmet ze seznamu veci aniž by jej odstranila
     *
     * @param nazev
     * @return Vec
     */
    public Vec peekVec(String nazev) {
        Vec vybranaVec = null;
        for (Vec vec : seznamVeci) {
            if (vec.getNazev().equals(nazev)) {
                vybranaVec = vec;
            }
        }
        return vybranaVec;
    }

    /**
     * Vrati seznam veci ze seznamVeci předepsaný stringem "Seznam věcí v místnosti:"
     *
     * @return
     */
    public String seznamVeciDlouhy() {
        return "Seznam věcí v místnosti:" + seznamVeci();
    }

    /**
     * Vrati seznam veci ze seznamVeci
     *
     * @return
     */
    public String seznamVeci() {
        String seznam = "";
        for (Vec vec : seznamVeci) {
            seznam += (seznam.isEmpty() ? " " : ", ") + vec.getNazev();
        }
        return seznam;
    }

    @Override
    public String toString() {
        return getNazev();
    }
}
