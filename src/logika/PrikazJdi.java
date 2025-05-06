package logika;

import java.util.Objects;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!\n";
        } else if ((smer.equals("cell_3") || smer.equals("hlavní_koridor_hradu") || smer.equals("?"))
                   && plan.getProstor("moje_cela").obsahujeVec("zámek_okovů"))  {
            return "Pokusil jsi se přejít do " + smer + ", nicméně tvé okovy ti nedovolí vzdálit se ze své celi na více jak 3 místnosti\n";
        } else {
            if (Objects.equals(smer, "severní_koridor_žaláře") && Objects.equals(plan.getAktualniProstor().getNazev(), "cela_1")) {
                Prostor cela1 = plan.getProstor("cela_1");
                if (cela1.obsahujeVec("sýr")) {
                    cela1.odeberVec("sýr");
                    cela1.vlozVec(new Vec("klíč_od_okovů", true, false, null, plan));
                }
            }
            
            plan.setAktualniProstor(sousedniProstor);
            
            if (sousedniProstor.getNazev().equals("slanit_se_po_okovech_na_straně_hradu")) {
                hra.setEpilog( "Dosel jsi do vyherniho prostoru. Hra konci. Diky!" );
                hra.setKonecHry(true);
            } else if (sousedniProstor.getNazev().equals("hlavní_koridor_hradu") &&
                    (plan.getVybava().masNaSobe("okovy") || !plan.getVybava().masNaSobe("žalářníkův_úbor"))) {
                hra.setEpilog("Narazil jsi na hradní stráž. Podle tvého vzhledu v tobě okamžitě rozpoznali uprchlého vězně. Prohrál jsi. Více štěstí příště!");
                hra.setKonecHry(true);
            }
            return "";
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
