package logika;

import java.util.Objects;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Luboš Pavlíček
 * @version pro školní rok 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private final HerniPlan plan;
    private final Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
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
        }

        if (jeOmezenyOkovy(smer)) {
            return "Pokusil jsi se přejít do " + smer + ", nicméně tvé okovy ti nedovolí vzdálit se ze své celi na více jak 3 místnosti\n";
        }

        zpracujSpecialniUdalostProCelu1(smer);

        plan.setAktualniProstor(sousedniProstor);

        return zkontrolujKonecHry(sousedniProstor);
    }

    /**
     * Kontroluje zda je hráč omezen okovy a nemůže se vzdálit na více jak 3 místnosti od své cely
     *
     * @param smer směr pohybu hráče
     * @return true pokud je hráč omezen okovy, jinak false
     */
    private boolean jeOmezenyOkovy(String smer) {
        return (smer.equals("cela_3") || smer.equals("hlavní_koridor_hradu") || smer.equals("?"))
                && plan.getProstor("moje_cela").obsahujeVec("zámek_okovů");
    }

    /**
     * Zpracovává speciální událost v cele 1,
     * kde se může objevit klíč od okovů pokud v ní hráč zanechal sýr pro myši a celu opustil
     *
     * @param smer směr pohybu hráče
     */
    private void zpracujSpecialniUdalostProCelu1(String smer) {
        if (Objects.equals(smer, "severní_koridor_žaláře") &&
                Objects.equals(plan.getAktualniProstor().getNazev(), "cela_1")) {
            Prostor cela1 = plan.getProstor("cela_1");
            if (cela1.obsahujeVec("sýr")) {
                cela1.odeberVec("sýr");
                cela1.vlozVec(new Vec("klíč_od_okovů", true, false, null, plan));
            }
        }
    }

    /**
     * Kontroluje, zda hráč nedosáhl některého z možných konců hry
     *
     * @param novyProstor prostor, do kterého hráč vstoupil
     * @return prázdný řetězec nebo text konce hry
     */
    private String zkontrolujKonecHry(Prostor novyProstor) {
        String nazevProstoru = novyProstor.getNazev();

        if (nazevProstoru.equals("slanit_se_po_okovech_na_straně_hradu")) {
            hra.setEpilog("Slanil si se po řetězech na straně hradu. Srdce ti buší, ruce krvácí, ale jsi na svobodě. Podařilo se ti uniknout! Gratulace!");
            hra.setKonecHry(true);
        } else if (nazevProstoru.equals("nádvoří")) {
            hra.setEpilog("Proklouzl jsi hlavní bránou. Za zády se zvedá chaos – možná kvůli prázdné cele, možná kvůli králi. Ty už ale mizíš v dálce. Utekl jsi. Gratulace!");
            hra.setKonecHry(true);
        } else if (nazevProstoru.equals("hlavní_koridor_hradu") &&
                (plan.getVybava().masNaSobe("okovy") || !plan.getVybava().masNaSobe("žalářníkův_úbor"))) {
            hra.setEpilog("Narazil jsi na hradní stráž. Podle tvého vzhledu v tobě okamžitě rozpoznali uprchlého vězně. Prohrál jsi. Více štěstí příště!");
            hra.setKonecHry(true);
        } else if (nazevProstoru.equals("strážní_stanice")) {
            hra.setEpilog("Ve strážní stanici jsi narazil na strážného, který tě ihned rozpoznal jako uprchlého vězně. Prohrál jsi. Hodně štěstí příště!");
            hra.setKonecHry(true);
        }
        return "";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}