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
        zalozProstoryHry();
        this.batoh = new Batoh(4);
        this.vybava = new Vybava();
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


        Vec hamburger = new Vec("Hamburger", true, false);
        domecek.vlozVec(hamburger);
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        domecek.setVychod(les);
        les.setVychod(domecek);
        les.setVychod(hlubokyLes);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(jeskyne);
        hlubokyLes.setVychod(chaloupka);
        jeskyne.setVychod(hlubokyLes);
        chaloupka.setVychod(hlubokyLes);
                
        aktualniProstor = domecek;  // hra začíná v domečku
        vyherniProstor = jeskyne;
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
