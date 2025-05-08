package logika;


import java.util.ArrayList;
import java.util.List;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private final List<Prostor> prostory;
    private final Batoh batoh;
    private final Vybava vybava;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    public HerniPlan() {
        this.batoh = new Batoh(4);
        this.vybava = new Vybava();
        this.prostory = new ArrayList<>();
        zalozProstoryHry();
    }

    /**
     * Vytváří jednotlivé prostory, propojuje je pomocí východů
     * a vkládá do nich věci. Jako výchozí aktuální prostor nastaví `moje_cela`.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor mojeCela = new Prostor("moje_cela", "Jsi ve své cele");
        Prostor severniKoridorZalare = new Prostor("severní_koridor_žaláře",
                "Jsi v severním koridoru žaláře");
        Prostor oubliette = new Prostor("oubliette", "Jsi v oubliette");
        Prostor cela1 = new Prostor("cela_1", "Jsi v první cele");
        Prostor stredniKoridorZalare = new Prostor("střední_koridor_žaláře",
                "Jsi ve středním koridoru žaláře");
        Prostor cela2 = new Prostor("cela_2", "Jsi ve druhé cele");
        Prostor zalarnikovaStanice = new Prostor("žalářníkova_stanice", "Jsi v žalářníkově stanici");
        Prostor jizniKoridorZalare = new Prostor("jižní_koridor_žaláře", "Jsi v jižním koridoru žaláře");
        Prostor cela3 = new Prostor("cela_3", "Jsi ve třetí cele");
        Prostor koridorHradu = new Prostor("hlavní_koridor_hradu", "Jsi v hlavním koridoru hradu");
        Prostor koruniSal = new Prostor("koruní_sál", "Jsi v koruním sálu");
        Prostor staniceStraze = new Prostor("strážní_stanice", "Jsi ve strážní stanici");
        Prostor kralovaKomnata = new Prostor("králova_komnata", "Jsi v Králově komnatě");
        Prostor skrytaCapkaMistnost = new Prostor("?", "ʆรเ √є รкгץтє ςคקкค ๓เรтภ๏รтเ");

        // PseudoProstory pro Pruzkum Veci
        Prostor prostorTeloZalarnika = new Prostor("", "Prozkoumáváš tělo_žalářníka");
        Prostor prostorZalarnikuvDiar = new Prostor("", "Prozkoumáváš žalářníkův_diář");
        Prostor prostorMysiDira = new Prostor("", "Prozkoumáváš myší_díra");
        Prostor prostorKraluvDiar = new Prostor("", "Prozkoumáváš králův_diář");

        // VECI woooo hoooo https://www.youtube.com/watch?v=f8mL0_4GeV0
        // startovni vybava
        Vec okovy = new Vec("okovy", true, true, null, this);
        vybava.nasadSi(okovy);
        // moje cela
        Vec teloZalarnika = new Vec("tělo_žalářníka", false, false, prostorTeloZalarnika, this);
        mojeCela.vlozVec(teloZalarnika);
        Vec zamekOkovu = new Vec("zámek_okovů", false, false, null, this);
        mojeCela.vlozVec(zamekOkovu);
        // severni koridor zalare
        Vec dvereCeli1 = new Vec("dveře_celi_1", false, false, null, this);
        severniKoridorZalare.vlozVec(dvereCeli1);
        // stredni koridor zalare
        Vec dvereCeli2 = new Vec("dveře_celi_2", false, false, null, this);
        stredniKoridorZalare.vlozVec(dvereCeli2);
        // jizni koridor zalare
        Vec dvereCeli3 = new Vec("dveře_celi_3", false, false, null, this);
        jizniKoridorZalare.vlozVec(dvereCeli3);
        // zalarnikova stanice
        Vec zalarnikuvDiar = new Vec("žalářníkův_diář", false, false, prostorZalarnikuvDiar, this);
        zalarnikovaStanice.vlozVec(zalarnikuvDiar);
        Vec syr = new Vec("sýr", true, false, null, this);
        zalarnikovaStanice.vlozVec(syr);
        // cela 1
        Vec mysiDira = new Vec("myší_díra", false, false, prostorMysiDira, this);
        cela1.vlozVec(mysiDira);
        // capka mistnost
        Vec capka = new Vec("čapka", true, true, null, this);
        skrytaCapkaMistnost.vlozVec(capka);
        // cela 3
        Vec palice = new Vec("palice", true, false, null, this);
        cela3.vlozVec(palice);
        // oubliette
        Vec nestabilniStena = new Vec("nestabilní_stěna", false, false, null, this);
        oubliette.vlozVec(nestabilniStena);
        // hlavní_koridor_hradu
        Vec straz = new Vec("stráž", false, false, null, this);
        koridorHradu.vlozVec(straz);
        Vec hlavniBrana = new Vec("hlavní_brána", false, false, null, this);
        koridorHradu.vlozVec(hlavniBrana);
        // kralova_komnata
        Vec kraluvDiar = new Vec("králův_diář", false, false, prostorKraluvDiar, this);
        kralovaKomnata.vlozVec(kraluvDiar);
        // koruni_sal
        Vec kral = new Vec("král", false, false, null, this);
        koruniSal.vlozVec(kral);

        // Veci v pseudo prostorech
        // zalarnikovo telo
        Vec zalarnikuvUbor = new Vec("žalářníkův_úbor", true, true, null, this);
        prostorTeloZalarnika.vlozVec(zalarnikuvUbor);
        Vec klicOdCel = new Vec("klíč_od_cel", true, false, null, this);
        prostorTeloZalarnika.vlozVec(klicOdCel);


        // přiřazují se průchody mezi prostory (sousedící prostory)
        mojeCela.setVychod(severniKoridorZalare);
        severniKoridorZalare.setVychod(mojeCela);
        severniKoridorZalare.setVychod(oubliette);
        severniKoridorZalare.setVychod(stredniKoridorZalare);
        stredniKoridorZalare.setVychod(severniKoridorZalare);
        stredniKoridorZalare.setVychod(jizniKoridorZalare);
        stredniKoridorZalare.setVychod(zalarnikovaStanice);
        jizniKoridorZalare.setVychod(stredniKoridorZalare);
        jizniKoridorZalare.setVychod(koridorHradu);
        cela1.setVychod(severniKoridorZalare);
        oubliette.setVychod(severniKoridorZalare);
        zalarnikovaStanice.setVychod(stredniKoridorZalare);
        cela2.setVychod(stredniKoridorZalare);
        cela2.setVychod(skrytaCapkaMistnost);
        cela3.setVychod(jizniKoridorZalare);
        skrytaCapkaMistnost.setVychod(cela2);
        koridorHradu.setVychod(jizniKoridorZalare);
        koridorHradu.setVychod(koruniSal);
        koridorHradu.setVychod(kralovaKomnata);
        koridorHradu.setVychod(staniceStraze);
        koruniSal.setVychod(koridorHradu);
        kralovaKomnata.setVychod(koridorHradu);
        staniceStraze.setVychod(koridorHradu);

        // přechody pseudo mistností
        prostorTeloZalarnika.setVychod(mojeCela);
        prostorZalarnikuvDiar.setVychod(zalarnikovaStanice);
        prostorMysiDira.setVychod(cela1);
        prostorKraluvDiar.setVychod(kralovaKomnata);

        // index prostorů
        prostory.add(mojeCela);
        prostory.add(severniKoridorZalare);
        prostory.add(oubliette);
        prostory.add(cela1);
        prostory.add(stredniKoridorZalare);
        prostory.add(cela2);
        prostory.add(zalarnikovaStanice);
        prostory.add(jizniKoridorZalare);
        prostory.add(cela3);
        prostory.add(koridorHradu);
        prostory.add(koruniSal);
        prostory.add(staniceStraze);
        prostory.add(kralovaKomnata);
        prostory.add(skrytaCapkaMistnost);
        prostory.add(prostorTeloZalarnika);
        prostory.add(prostorZalarnikuvDiar);

        aktualniProstor = mojeCela;  // hra začíná v mojí cele
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }


    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }


    /**
     * Metoda vrátí instanci batohu spojenou s tímto herním plánem
     *
     * @return Batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

    /**
     * Metoda vrátí instanci výbavy spojenou s tímto herním plánem
     *
     * @return Vybava
     */
    public Vybava getVybava() {
        return vybava;
    }

    /**
     * Metoda vrátí instanci prostoru s požadovaným názvem
     * <p>
     * Používá se, potřebujeli metoda přístup do jiného prostoru
     * nežli je prostor aktuální
     *
     * @return Prostor
     */
    public Prostor getProstor(String nazevProstoru) {
        for (Prostor prostor : prostory) {
            if (prostor.getNazev().equals(nazevProstoru)) {
                return prostor;
            }
        }
        return null;
    }

}
