import logika.Hra;
import org.junit.jupiter.api.BeforeEach;
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
    public void zastizeniStrazi() {
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
        // 23. krok hry JDI hlavní_koridor_hradu
        assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                "Narazil jsi na hradní stráž. Podle tvého vzhledu v tobě okamžitě rozpoznali uprchlého vězně. Prohrál jsi. Více štěstí příště!\n");
    }

    @Test
    public void agreseVuciStrazi() {
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

        // 20. krok hry PROZKOUMEJ tělo_žalářníka 
        assertEquals(hra.zpracujPrikaz("prozkoumej tělo_žalářníka"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Poznatky:\n" +
                        "Obsahuje: žalářníkův_úbor\n" +
                        "Východy: moje_cela");

        // 21. krok hry SEBER žalářníkův_úbor
        assertEquals(hra.zpracujPrikaz("seber žalářníkův_úbor"),
                "Prozkoumáváš tělo_žalářníka\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, žalářníkův_úbor\n" +
                        "Poznatky:\n" +
                        "Obsahuje:\n" +
                        "Východy: moje_cela");

        // 22. krok hry JDI moje_cela
        assertEquals(hra.zpracujPrikaz("jdi moje_cela"),
                "Jsi ve své cele\n" +
                        "Máš na sobě:\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy, žalářníkův_úbor\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka\n" +
                        "Východy: severní_koridor_žaláře");

        // 23. krok hry OBLÉKNI SI žalářníkův_úbor
        assertEquals(hra.zpracujPrikaz("nasaď si žalářníkův_úbor"),
                "Jsi ve své cele\n" +
                        "Máš na sobě: žalářníkův_úbor\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: tělo_žalářníka\n" +
                        "Východy: severní_koridor_žaláře");

        // 24. krok hry JDI severní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi severní_koridor_žaláře"),
                "Jsi v severním koridoru žaláře\n" +
                        "Máš na sobě: žalářníkův_úbor\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti:\n" +
                        "Východy: moje_cela, oubliette, střední_koridor_žaláře, cela_1");

        // 25. krok hry JDI střední_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi střední_koridor_žaláře"),
                "Jsi ve středním koridoru žaláře\n" +
                        "Máš na sobě: žalářníkův_úbor\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: dveře_celi_2\n" +
                        "Východy: severní_koridor_žaláře, jižní_koridor_žaláře, žalářníkova_stanice");

        // 26. krok hry JDI jižní_koridor_žaláře
        assertEquals(hra.zpracujPrikaz("jdi jižní_koridor_žaláře"),
                "Jsi v jižním koridoru žaláře\n" +
                        "Máš na sobě: žalářníkův_úbor\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: dveře_celi_3\n" +
                        "Východy: střední_koridor_žaláře, hlavní_koridor_hradu");

        // 27. krok hry JDI hlavní_koridor_hradu
        assertEquals(hra.zpracujPrikaz("jdi hlavní_koridor_hradu"),
                "Jsi v hlavním koridoru hradu\n" +
                        "Máš na sobě: žalářníkův_úbor\n" +
                        "Seznam věcí v batohu: klíč_od_cel, klíč_od_okovů, okovy\n" +
                        "Seznam věcí v místnosti: stráž\n" +
                        "Východy: jižní_koridor_žaláře, koruní_sál, králova_komnata, strážní_stanice");

        // 28. krok hry JDI hlavní_koridor_hradu
        assertEquals(hra.zpracujPrikaz("udeř stráž"),
                "Tvůj neuvážený útok na stráž se ukázal být osudovou chybou." +
                       " Strážný tě snadno přemohl a okamžitě v tobě rozpoznal uprchlého vězně. " +
                       "Byl jsi odvlečen zpět do cely, kde budeš trávit zbytek svých dní. " +
                       "Prohrál jsi, snad budeš mít příště více štěstí.\n");


    }
}
