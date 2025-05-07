import logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HraTest {

    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @Test
    public void zakladniTestovaciScenar() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                       "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                       "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                       "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                       "Máš na sobě: okovy\n" +
                       "Seznam věcí v batohu:\n" +
                       "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                       "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                       "Máš na sobě: okovy\n" +
                       "Seznam věcí v batohu: klíč_od_cel\n" +
                       "Poznatky:\n" +
                       "Obsahuje: žalářníkův_úbor\n" +
                       "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                       "Máš na sobě: okovy\n" +
                       "Seznam věcí v batohu: klíč_od_cel\n" +
                       "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                       "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry JDI žalářníkova_stanice
        assertEquals(hra.zpracujPrikaz("jdi žalářníkova_stanice"),
                "Jsi v žalářníkově stanici\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: žalářníkův_diář, sýr\n" +
                        "Východy: střední_koridor_žaláře");

        // 8. krok hry SEBER sýr
        assertEquals(hra.zpracujPrikaz("seber sýr"),
                "Jsi v žalářníkově stanici\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, sýr\n" +
                        "Seznam věcí v místnosti: žalářníkův_diář\n" +
                        "Východy: střední_koridor_žaláře");

        // 9. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, sýr\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 10. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, sýr\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 11. krok hry JDI cela_1
        assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, sýr\n" +
                        "Seznam věcí v místnosti: myší_díra\n" +
                        "Východy: severní_koridor_žaláře");

        // 12. krok hry POLOŽ sýr
        assertEquals(hra.zpracujPrikaz("polož sýr"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: myší_díra, sýr\n" +
                        "Východy: severní_koridor_žaláře");

        // 13. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 14. krok hry JDI cela_1
        assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: myší_díra, klíč_od_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 15. krok hry SEBER klíč_od_okovů
        assertEquals(hra.zpracujPrikaz("seber klíč_od_okovů"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů\n" +
                        "Seznam věcí v místnosti: myší_díra\n" +
                        "Východy: severní_koridor_žaláře");

        // 16. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 17. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 18. krok hry použij klíč_od_okovů NA zámek_okovů
        assertEquals(hra.zpracujPrikaz("použij klíč_od_okovů na zámek_okovů"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka\n" +
                        "Východy: severní_koridor_žaláře");

        // 19. krok hry SUNDEJ SI okovy
        assertEquals(hra.zpracujPrikaz("sundej si okovy"),
                "Jsi ve své cele\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka\n" +
                        "Východy: severní_koridor_žaláře");

        // 20. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 21. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 22. krok hry JDI jižní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

        // 23. krok hry použij klíč_od_cel NA dveře_celi_3
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_3"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu, cela_3");

        // 24. krok hry JDI cela_3
        assertEquals(hra.zpracujPrikaz("jdi cela_3"),
                "Jsi ve třetí cele\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: palice\n" +
                        "Východy: jižní_koridor_žaláře");

        // 25. krok hry SEBER palice
        assertEquals(hra.zpracujPrikaz("seber palice"),
                "Jsi ve třetí cele\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: jižní_koridor_žaláře");

        // 26. krok hry JDI jižní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu, cela_3");

        // 27. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 28. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 29. krok hry JDI oubliette
        assertEquals(hra.zpracujPrikaz("jdi oubliette"),
                "Jsi v oubliette\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti: nestabilní_stěna\n" +
                        "Východy: severní_koridor_žaláře");

        // 30. krok hry UDEŘ nestabilní_stěna
        assertEquals(hra.zpracujPrikaz("udeř nestabilní_stěna"),
                "Jsi v oubliette\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, palice\n" +
                        "Seznam věcí v místnosti: díra_ve_stěně\n" +
                        "Východy: severní_koridor_žaláře");

        // 31. krok hry použij okovy NA díra_ve_stěně
        assertEquals(hra.zpracujPrikaz("použij okovy na díra_ve_stěně"),
                "Jsi v oubliette\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, palice\n" +
                        "Seznam věcí v místnosti: díra_ve_stěně\n" +
                        "Východy: severní_koridor_žaláře, slanit_se_po_okovech_na_straně_hradu");

        // 32. krok hry JDI slanit_se_po_okovech_na_straně_hradu
        assertEquals(hra.zpracujPrikaz("jdi slanit_se_po_okovech_na_straně_hradu"),
                     hra.vratEpilog("slanění"));
    }

    @Test
    public void zalarnikovoTeloNelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry SEBER tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("seber tělo_žalářníka"),
                "tělo_žalářníka se nedá přenést!\n" +
                        "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");
    }

    @Test
    public void nestabilniStenaNelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 2. krok hry JDI oubliette
        assertEquals(hra.zpracujPrikaz("jdi oubliette"),
                "Jsi v oubliette\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: nestabilní_stěna\n" +
                        "Východy: severní_koridor_žaláře");

        // 3. krok hry SEBER nestabilní_stěna
        assertEquals(hra.zpracujPrikaz("seber nestabilní_stěna"),
                "nestabilní_stěna se nedá přenést!\n" +
                        "Jsi v oubliette\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: nestabilní_stěna\n" +
                        "Východy: severní_koridor_žaláře");
    }

    @Test
    public void dvereCeli1NelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");


        // 1. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("seber dveře_celi_1"),
                "dveře_celi_1 se nedá přenést!\n" +
                        "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");
    }

    @Test
    public void mysiDiraNelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");


        // 6. krok hry JDI cela_1
        assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: myší_díra\n" +
                        "Východy: severní_koridor_žaláře");

        // 7. krok hry SEBER myší_díra
        assertEquals(hra.zpracujPrikaz("seber myší_díra"),
                "myší_díra se nedá přenést!\n" +
                        "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: myší_díra\n" +
                        "Východy: severní_koridor_žaláře");
    }

    @Test
    public void prozkoumaniMysiDiry() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");


        // 6. krok hry JDI cela_1
        assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                "Jsi v první cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: myší_díra\n" +
                        "Východy: severní_koridor_žaláře");

        // 7. krok hry PROZKOUMEJ myší_díra
        assertEquals(hra.zpracujPrikaz("prozkoumej myší_díra"),
                "Prozkoumáváš myší_díra\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky: - Uvnitř vidíš hromádku různých kovových objektů, nicméně jsou příliš daleko, než aby si je mohl zdvihnout\n" +
                        "Obsahuje:\n" +
                        "Východy: cela_1");
    }

    @Test
    public void zamekOkovuNelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry SEBER tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("seber zámek_okovů"),
                "zámek_okovů se nedá přenést!\n" +
                        "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");
    }

    @Test
    public void prozkoumaniZalarnikovaDiare() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry JDI žalářníkova_stanice
        assertEquals(hra.zpracujPrikaz("jdi žalářníkova_stanice"),
                "Jsi v žalářníkově stanici\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: žalářníkův_diář, sýr\n" +
                        "Východy: střední_koridor_žaláře");
        // 8. krok hry PROZKOUMEJ žalářníkův_diář
        assertEquals(hra.zpracujPrikaz("prozkoumej žalářníkův_diář"),
                "Prozkoumáváš žalářníkův_diář\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky: - Soužití žalářníka a myší v žaláři se nedávno zhoršilo kvůli jejich nově vznikající tendenci krást drobné kovové předměty\n" +
                        "          - Před dvěma dny král z paranoie odvolal většinu členů stráže, nová stráž se stále seznamuje s poddanými sloužícími v prostorech hradu\n" +
                        "Obsahuje:\n" +
                        "Východy: žalářníkova_stanice");
    }

    @Test
    public void zalarnikuvDiareNelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry JDI žalářníkova_stanice
        assertEquals(hra.zpracujPrikaz("jdi žalářníkova_stanice"),
                "Jsi v žalářníkově stanici\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: žalářníkův_diář, sýr\n" +
                        "Východy: střední_koridor_žaláře");
        // 8. krok hry PROZKOUMEJ žalářníkův_diář
        assertEquals(hra.zpracujPrikaz("seber žalářníkův_diář"),
                "žalářníkův_diář se nedá přenést!\n" +
                        "Jsi v žalářníkově stanici\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: žalářníkův_diář, sýr\n" +
                        "Východy: střední_koridor_žaláře");
    }

    @Test
    public void dvereCeli2NelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry SEBER střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("seber dveře_celi_2"),
                "dveře_celi_2 se nedá přenést!\n" +
                        "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");
    }

    @Test
    public void dvereCeli3NelzeSebrat() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry JDI jižní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

        // 8. krok hry SEBER dveře_celi_3
        assertEquals(hra.zpracujPrikaz("seber dveře_celi_3"),
                "dveře_celi_3 se nedá přenést!\n" +
                        "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");
    }

    @Test
    public void nelzeSeVzdalitOViceJak3MistnosiVOkovech() {
        // Uvitani - zapnuti hry
        assertEquals(hra.vratUvitani(),
                "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                        "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                        "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                        "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 1. krok hry PROZKOUMEJ tělo_žalářníka
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu:\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                        "Východy: moje_cela");

        // 2. krok hry SEBER klíč_od_cel
        assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 3. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                        "Východy: severní_koridor_žaláře");

        // 4. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_1\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře");

        // 5. krok hry použij klíč_od_cel NA dveře_celi_1
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 6. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 7. krok hry JDI jižní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

        // 8. krok hry JDI hlavní_koridor_hradu
        assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                "Pokusil jsi se přejít do hlavní_koridor_hradu, nicméně tvé okovy ti nedovolí vzdálit se ze své celi na více jak 3 místnosti\n" +
                        "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

        // 9. krok hry POUZIJ klíč_od_cel NA dveře_celi_3
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_3"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu, cela_3");

        // 10. krok hry JDI cela_3
        assertEquals(hra.zpracujPrikaz("jdi cela_3"),
                "Pokusil jsi se přejít do cela_3, nicméně tvé okovy ti nedovolí vzdálit se ze své celi na více jak 3 místnosti\n" +
                        "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu, cela_3");

        // 11. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 12. krok hry POUZIJ klíč_od_cel NA dveře_celi_2
        assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_2"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice, cela_2");

        // 13. krok hry JDI cela_2
        assertEquals(hra.zpracujPrikaz("jdi cela_2"),
                "Jsi ve druhé cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, ?");

        // 14. krok hry JDI ?
        assertEquals(hra.zpracujPrikaz("jdi ?"),
                "Pokusil jsi se přejít do ?, nicméně tvé okovy ti nedovolí vzdálit se ze své celi na více jak 3 místnosti\n" +
                        "Jsi ve druhé cele\n" +
                        "Máš na sobě: okovy\n" +
                        "Seznam věcí v batohu: klíč_od_cel\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: střední_koridor_žaláře, ?");
    }

    @Nested
    class HlavniKoridorHradu {
        @BeforeEach
        public void utekZZalare() {
            // Uvitani - zapnuti hry
            assertEquals(hra.vratUvitani(),
                    "Tělo žálářníka se přestalo vzpírat a nehybné se svalilo z tvých rukou k zemi.\n" +
                            "Panika. Pot. Chlad. Chuť grepu na spodním rtu. Okovy kolem nohou.\n" +
                            "To je stav ve kterém jsi přišel ke svému vědomí. Ať už se má situace jakkoli\n" +
                            "tvé další kroky jsou instinktivně jasné: Dostaň se ven!\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu:\n" +
                            "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                            "Východy: severní_koridor_žaláře");

            // 1. krok hry PROZKOUMEJ tělo_žalářníka
            assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                    "Prozkoumáváš tělo_žalářníka\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu:\n" +
                            "Poznatky:\n" +
                            "Obsahuje: žalářníkův_úbor, klíč_od_cel\n" +
                            "Východy: moje_cela");

            // 2. krok hry SEBER klíč_od_cel
            assertEquals(hra.zpracujPrikaz("seber klíč_od_cel"),
                    "Prozkoumáváš tělo_žalářníka\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel\n" +
                            "Poznatky:\n" +
                            "Obsahuje: žalářníkův_úbor\n" +
                            "Východy: moje_cela");

            // 3. krok hry SEBER žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("seber žalářníkův_úbor"),
                    "Prozkoumáváš tělo_žalářníka\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Poznatky:\n" +
                            "Obsahuje:\n" +
                            "Východy: moje_cela");

            // 4. krok hry JDI moje_cela
            assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                    "Jsi ve své cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                            "Východy: severní_koridor_žaláře");

            // 5. krok hry JDI severní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: dveře_celi_1\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře");

            // 6. krok hry použij klíč_od_cel NA dveře_celi_1
            assertEquals(hra.zpracujPrikaz("použij klíč_od_cel na dveře_celi_1"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti:\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

            // 7. krok hry JDI střední_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                    "Jsi ve středním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: dveře_celi_2\n" +
                            "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

            // 8. krok hry JDI žalářníkova_stanice
            assertEquals(hra.zpracujPrikaz("jdi žalářníkova_stanice"),
                    "Jsi v žalářníkově stanici\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: žalářníkův_diář, sýr\n" +
                            "Východy: střední_koridor_žaláře");

            // 9. krok hry SEBER sýr
            assertEquals(hra.zpracujPrikaz("seber sýr"),
                    "Jsi v žalářníkově stanici\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, sýr\n" +
                            "Seznam věcí v místnosti: žalářníkův_diář\n" +
                            "Východy: střední_koridor_žaláře");

            // 10. krok hry JDI střední_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                    "Jsi ve středním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, sýr\n" +
                            "Seznam věcí v místnosti: dveře_celi_2\n" +
                            "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

            // 11. krok hry JDI severní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, sýr\n" +
                            "Seznam věcí v místnosti:\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

            // 12. krok hry JDI cela_1
            assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                    "Jsi v první cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, sýr\n" +
                            "Seznam věcí v místnosti: myší_díra\n" +
                            "Východy: severní_koridor_žaláře");

            // 13. krok hry POLOŽ sýr
            assertEquals(hra.zpracujPrikaz("polož sýr"),
                    "Jsi v první cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: myší_díra, sýr\n" +
                            "Východy: severní_koridor_žaláře");

            // 14. krok hry JDI severní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti:\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

            // 15. krok hry JDI cela_1
            assertEquals(hra.zpracujPrikaz("jdi cela_1"),
                    "Jsi v první cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor\n" +
                            "Seznam věcí v místnosti: myší_díra, klíč_od_okovů\n" +
                            "Východy: severní_koridor_žaláře");

            // 16. krok hry SEBER klíč_od_okovů
            assertEquals(hra.zpracujPrikaz("seber klíč_od_okovů"),
                    "Jsi v první cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů\n" +
                            "Seznam věcí v místnosti: myší_díra\n" +
                            "Východy: severní_koridor_žaláře");

            // 17. krok hry JDI severní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů\n" +
                            "Seznam věcí v místnosti:\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

            // 18. krok hry JDI moje_cela
            assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                    "Jsi ve své cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů\n" +
                            "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                            "Východy: severní_koridor_žaláře");

            // 19. krok hry použij klíč_od_okovů NA zámek_okovů
            assertEquals(hra.zpracujPrikaz("použij klíč_od_okovů na zámek_okovů"),
                    "Jsi ve své cele\n" +
                            "Máš na sobě: okovy\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů\n" +
                            "Seznam věcí v místnosti: tělo_žalářníka\n" +
                            "Východy: severní_koridor_žaláře");

            // 20. krok hry SUNDEJ SI okovy
            assertEquals(hra.zpracujPrikaz("sundej si okovy"),
                    "Jsi ve své cele\n" +
                            "Máš na sobě:\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: tělo_žalářníka\n" +
                            "Východy: severní_koridor_žaláře");

            // 21. krok hry JDI severní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                    "Jsi v severním koridoru žaláře\n" +
                            "Máš na sobě:\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti:\n" +
                            "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

            // 22. krok hry JDI střední_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                    "Jsi ve středním koridoru žaláře\n" +
                            "Máš na sobě:\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_2\n" +
                            "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

            // 23. krok hry JDI jižní_koridor_žaláře
            assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě:\n" +
                            "Seznam věcí v batohu: klíč_od_cel, žalářníkův_úbor, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");
        }

        @Test
        public void zastizeniStrazi() {
            // 24. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Narazil jsi na hradní stráž. Podle tvého vzhledu v tobě okamžitě rozpoznali uprchlého vězně. Prohrál jsi. Více štěstí příště!\n");
        }

        @Test
        public void agreseVuciStrazi() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 26. krok hry UDEŘ stráž
            assertEquals(hra.zpracujPrikaz("udeř stráž"),
                    "Tvůj neuvážený útok na stráž se ukázal být osudovou chybou." +
                            " Strážný tě snadno přemohl a okamžitě v tobě rozpoznal uprchlého vězně. " +
                            "Byl jsi odvlečen zpět do cely, kde budeš trávit zbytek svých dní. " +
                            "Prohrál jsi, snad budeš mít příště více štěstí.\n");


        }

        @Test
        public void rozpoznaniStrazciVeStanici() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 26. krok hry JDI strážní_stanice
            assertEquals(hra.zpracujPrikaz("jdi strážní_stanice"),
                    "Ve strážní stanici jsi narazil na strážného, který tě ihned " +
                            "rozpoznal jako uprchlého vězně. Prohrál jsi. Hodně štěstí příště!\n");
        }

        @Test
        public void prozkoumaniKralovaDiare() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");
            // 26. krok hry JDI králova_komnata
            assertEquals(hra.zpracujPrikaz("jdi králova_komnata"),
                    "Jsi v Králově komnatě\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: králův_diář\n" +
                            "Východy: hlavní_koridor_hradu");
            // 27. krok hry PROZKOUMEJ králův_diář
            assertEquals(hra.zpracujPrikaz("prozkoumej králův_diář"),
                    "Prozkoumáváš králův_diář\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Poznatky: - Král se chvěje při pouhém vyslovení \"Ashbourne.\" Je to jméno prokletého bojiště, kde přišel o své syny. \n" +
                            "Obsahuje:\n" +
                            "Východy: králova_komnata");
        }

        @Test
        public void agreseVuciKrali() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 26. krok hry JDI koruní_sál
            assertEquals(hra.zpracujPrikaz("jdi koruní_sál"),
                    "Jsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: král\n" +
                            "Východy: hlavní_koridor_hradu");

            // 27. krok hry UDER král
            assertEquals(hra.zpracujPrikaz("udeř král"),
                    "Tvůj útok na krále byl neuvěřitelně pošetilý. " +
                            "Dřív než jsi stačil zasadit ránu, král si všiml " +
                            "tvé řeči těla a zavolal stráž. Byl jsi okamžitě zpacifikován " +
                            "a odvlečen na popraviště. Tvůj osud byl zpečetěn. Prohrál jsi, " +
                            "snad příště zvolíš moudřeji.\n");
        }

        @Test
        public void utecHlavniBranou() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 26. krok hry JDI koruní_sál
            assertEquals(hra.zpracujPrikaz("jdi koruní_sál"),
                    "Jsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: král\n" +
                            "Východy: hlavní_koridor_hradu");

            // 27. krok hry REKNI AshBourne
            assertEquals(hra.zpracujPrikaz("řekni Bojiště AshBourne a nějáký další text!"),
                    "Řekl jsi: \"Bojiště AshBourne a nějáký další text!\"\n" +
                            "Král zbledl, jako by ho zasáhl blesk.\n" +
                            "\"Ashbourne... ne... ne znovu...\"\n" +
                            "Otřásl se, oči se mu zaleskly hrůzou a vzápětí se bezvládně sesunul na trůn.\n" +
                            "Král omdlel!\nJsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: královo_tělo\n" +
                            "Východy: hlavní_koridor_hradu");

            // 28. krok hry PROZKOUMEJ královo_tělo
            assertEquals(hra.zpracujPrikaz("prozkoumej královo_tělo"),
                    "Prozkoumáváš královo_tělo\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Poznatky:\n" +
                            "Obsahuje: klíč_od_hlavní_brány, koruna\n" +
                            "Východy: koruní_sál");

            // 29. krok hry SEBER klíč_od_hlavní_brány
            assertEquals(hra.zpracujPrikaz("seber klíč_od_hlavní_brány"),
                    "Prozkoumáváš královo_tělo\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, klíč_od_hlavní_brány\n" +
                            "Poznatky:\n" +
                            "Obsahuje: koruna\n" +
                            "Východy: koruní_sál");

            // 30. krok hry JDI koruní_sál
            assertEquals(hra.zpracujPrikaz("jdi koruní_sál"),
                    "Jsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, klíč_od_hlavní_brány\n" +
                            "Seznam věcí v místnosti: královo_tělo\n" +
                            "Východy: hlavní_koridor_hradu");

            // 31. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, klíč_od_hlavní_brány\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 32. krok hry POUZIJ hlavní_koridor_hradu NA hlavní_brána
            assertEquals(hra.zpracujPrikaz("použij klíč_od_hlavní_brány na hlavní_brána"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice, nádvoří");

            // 33. krok hry JDI nádvoří
            assertEquals(hra.zpracujPrikaz("jdi nádvoří"),
                    "Proklouzl jsi hlavní bránou. Za zády se zvedá chaos – možná kvůli prázdné cele, možná kvůli králi. Ty už ale mizíš v dálce. Utekl jsi. Gratulace!\n");
        }

        @Test
        public void limitBatohu() {
            // 24. krok hry NASAD SI žalářníkův_úbor
            assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                    "Jsi v jižním koridoru žaláře\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: dveře_celi_3\n" +
                            "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

            // 25. krok hry JDI hlavní_koridor_hradu
            assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                    "Jsi v hlavním koridoru hradu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: stráž, hlavní_brána\n" +
                            "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

            // 26. krok hry JDI koruní_sál
            assertEquals(hra.zpracujPrikaz("jdi koruní_sál"),
                    "Jsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: král\n" +
                            "Východy: hlavní_koridor_hradu");

            // 27. krok hry REKNI AshBourne
            assertEquals(hra.zpracujPrikaz("řekni Bojiště AshBourne a nějáký další text!"),
                    "Řekl jsi: \"Bojiště AshBourne a nějáký další text!\"\n" +
                            "Král zbledl, jako by ho zasáhl blesk.\n" +
                            "\"Ashbourne... ne... ne znovu...\"\n" +
                            "Otřásl se, oči se mu zaleskly hrůzou a vzápětí se bezvládně sesunul na trůn.\n" +
                            "Král omdlel!\nJsi v koruním sálu\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Seznam věcí v místnosti: královo_tělo\n" +
                            "Východy: hlavní_koridor_hradu");

            // 28. krok hry PROZKOUMEJ královo_tělo
            assertEquals(hra.zpracujPrikaz("prozkoumej královo_tělo"),
                    "Prozkoumáváš královo_tělo\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                            "Poznatky:\n" +
                            "Obsahuje: klíč_od_hlavní_brány, koruna\n" +
                            "Východy: koruní_sál");

            // 29. krok hry SEBER klíč_od_hlavní_brány
            assertEquals(hra.zpracujPrikaz("seber klíč_od_hlavní_brány"),
                    "Prozkoumáváš královo_tělo\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, klíč_od_hlavní_brány\n" +
                            "Poznatky:\n" +
                            "Obsahuje: koruna\n" +
                            "Východy: koruní_sál");

            // 30. krok hry SEBER koruna
            assertEquals(hra.zpracujPrikaz("seber koruna"),
                    "koruna se ti do batohu nevejde, musíš je prvně vyprázdnit\n" +
                            "Prozkoumáváš královo_tělo\n" +
                            "Máš na sobě: žalářníkův_úbor\n" +
                            "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, klíč_od_hlavní_brány\n" +
                            "Poznatky:\n" +
                            "Obsahuje:\n" +
                            "Východy: koruní_sál");
        }
    }

}
