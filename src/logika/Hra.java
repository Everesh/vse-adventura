package logika;

import java.util.Arrays;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private String epilog = "Dík, že jste si zahráli.  Ahoj.";

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRekni(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUder(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazNasadSi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSundejSi(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                herniPlan.getVybava().dlouhyPopis() + "\n" +
                herniPlan.getBatoh().dlouhyPopis() + "\n" +
                herniPlan.getAktualniProstor().seznamVeciDlouhy() + "\n" +
                herniPlan.getAktualniProstor().seznamVychodyDlouhy();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return epilog;
    }


    public String vratEpilog(String type) {
        return epilog + "\n" + type;
    }
    /**
     * Moznost upravit epilog hry
     * @param epilog
     */
    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];

        // Alias pro nápovědu
        if (slovoPrikazu.equals("pomoc")) { slovoPrikazu = "nápověda";}

        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];
        }

        // Extrakce zvratného 'si'
        if (parametry.length > 0 && parametry[0].equals("si")) {
            slovoPrikazu += " si";
            parametry = Arrays.copyOfRange(parametry, 1, parametry.length);
        }

        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            textKVypsani += herniPlan.getAktualniProstor().getPopis() + "\n";
            textKVypsani += herniPlan.getVybava().dlouhyPopis() + "\n";
            textKVypsani += herniPlan.getBatoh().dlouhyPopis() + "\n";
            if (herniPlan.getAktualniProstor().getNazev().isEmpty()) {
                textKVypsani += "Poznatky:" + "\n"; //TODO
                textKVypsani += "Obsahuje:" + herniPlan.getAktualniProstor().seznamVeci() + "\n";
            } else {
                textKVypsani += herniPlan.getAktualniProstor().seznamVeciDlouhy() + "\n";
            }
            textKVypsani += herniPlan.getAktualniProstor().seznamVychodyDlouhy();
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

