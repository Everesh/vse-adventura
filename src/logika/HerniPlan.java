package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;
    private Batoh batoh;
    private Vybava vybava;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        this.batoh = new Batoh(4);
        this.vybava = new Vybava();
        zalozProstoryHry();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor moje_cela = new Prostor("moje_cela", "jsi ve své cele");
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
        Prostor staniceStraze = new Prostor( "strážní_stanice", "Jsi ve strážní stanici");
        Prostor kralovaKomnata = new Prostor("králova_komnata", "Jsi v Králově komnatě");
        Prostor skrytaCapkaMistnost = new Prostor("?", "ʆรเ √є รкгץтє ςคקкค ๓เรтภ๏รтเ");
        Prostor vychod1 = new Prostor("slanit_se_po_okovech_na_straně_hradu", "");


        // VECI woooo hoooo https://www.youtube.com/watch?v=f8mL0_4GeV0
        // startovni vybava
        Vec okovy = new Vec("okovy", true, true);
        vybava.nasadSi(okovy);
        // moje cela
        Vec teloZalarnika = new Vec("tělo_žaláříka", false, false);
        moje_cela.vlozVec(teloZalarnika);
        Vec zamekOkovu = new Vec("zámek_okovů", false, false);
        moje_cela.vlozVec(zamekOkovu);
        // severni koridor zalare
        Vec dvereCeli1 = new Vec("dveře_celi_1", false, false);
        severniKoridorZalare.vlozVec(dvereCeli1);
        // stredni koridor zalare
        Vec dvereCeli2 = new Vec("dveře_celi_2", false, false);
        stredniKoridorZalare.vlozVec(dvereCeli2);
        // jizni koridor zalare
        Vec dvereCeli3 = new Vec("dveře_celi_3", false, false);
        jizniKoridorZalare.vlozVec(dvereCeli3);
        // zalarnikova stanice
        Vec syr = new Vec("sýr", true, false);
        zalarnikovaStanice.vlozVec(syr);
        Vec zalarnikuvDiar = new Vec("žalářníkův_diář", false, false);
        zalarnikovaStanice.vlozVec(zalarnikuvDiar);
        // capka mistnost
        Vec capka = new Vec("čapka", true, true);
        skrytaCapkaMistnost.vlozVec(capka);
        // cela 3
        Vec palice = new Vec("palice", true, false);


        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        moje_cela.setVychod(severniKoridorZalare);
        severniKoridorZalare.setVychod(moje_cela);
        severniKoridorZalare.setVychod(oubliette);
        severniKoridorZalare.setVychod(stredniKoridorZalare);
        stredniKoridorZalare.setVychod(severniKoridorZalare);
        stredniKoridorZalare.setVychod(staniceStraze);
        stredniKoridorZalare.setVychod(jizniKoridorZalare);
        jizniKoridorZalare.setVychod(stredniKoridorZalare);
        jizniKoridorZalare.setVychod(koridorHradu);
        cela1.setVychod(severniKoridorZalare);
        oubliette.setVychod(severniKoridorZalare);
        staniceStraze.setVychod(stredniKoridorZalare);
        cela2.setVychod(stredniKoridorZalare);
        cela3.setVychod(jizniKoridorZalare);
        skrytaCapkaMistnost.setVychod(cela2);
        koridorHradu.setVychod(jizniKoridorZalare);
        koridorHradu.setVychod(koruniSal);
        koridorHradu.setVychod(kralovaKomnata);
        koridorHradu.setVychod(staniceStraze);
        koruniSal.setVychod(koridorHradu);
        kralovaKomnata.setVychod(koridorHradu);
        staniceStraze.setVychod(koridorHradu);
                
        aktualniProstor = moje_cela;  // hra začíná v domečku
        vyherniProstor = vychod1;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }


    /**
     * Vraci vyherni prostor
     * @return
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    public Batoh getBatoh() {
        return batoh;
    }

    public Vybava getVybava() {return vybava;}

}
