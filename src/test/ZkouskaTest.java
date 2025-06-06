import logika.Hra;
import logika.Vec;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZkouskaTest {

    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @AfterEach
    public void tearDown() {
        hra = null;
    }

    @Test
    public void abrakadabraPreneseHrace() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                              "Máš na sobě: okovy\n" +
                              "Seznam věcí v batohu:\n" +
                              "Seznam věcí v místnosti: čarokněžník\n" +
                              "Východy:", hra.zpracujPrikaz("abrakadabra"));
    }

    @Test
    public void abrakadabraNastavyRealityOrigin() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("moje_cela", hra.getHerniPlan().getPreMagicOrigin().getNazev());
    }

    @Test
    public void abrakadabraPreneseHraceZpet() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Jsi ve své cele\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                "Východy: severní_koridor_žaláře", hra.zpracujPrikaz("abrakadabra"));
    }

    @Test
    public void abrakadabraPreneseHraceZpetDoSpravnehoProstoru() {
        assertEquals("Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("jdi severní_koridor_žaláře"));

        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("abrakadabra"));
    }

    @Test
    public void abrakadabraNastaviSpravnyOrigin() {
        assertEquals("Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("jdi severní_koridor_žaláře"));

        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("severní_koridor_žaláře", hra.getHerniPlan().getPreMagicOrigin().getNazev());
    }

    @Test
    public void promluvSCarokneznikem() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
            "Máš na sobě: okovy\n" +
            "Seznam věcí v batohu:\n" +
            "Seznam věcí v místnosti: čarokněžník\n" +
            "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Výtej vyzivateli, vyvoláním prastaré kledby A-brak-A-da-BRA si splnil první z testů pro\n" +
                "osvojení mutidimenzionální teleportace. Nicméně druhý bude namáhavější. Použí kledbu\n" +
                "čA--Rym-ARy-fuk a zjiskej pro mě tabulu rasu, incarnaci originální magie of incarnace osudu!\n" +
                "Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("mluv s čarokněžník"));
    }

    @Test
    public void carymaryfukThrowsErrorFromOutsideAbrakadabraSpace() {
        assertThrows(RuntimeException.class, () -> hra.zpracujPrikaz("carymaryfuk"));
    }

    @Test
    public void carymaryfukPreneseZAbrakadabraSpace() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));
    }

    @Test
    public void carymaryfukMaPristupKOriginRealita() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("moje_cela", hra.getHerniPlan().getPreMagicOrigin().getNazev());
    }

    @Test
    public void carymaryfukMaPristupKeSpravnemuOriginRealita() {
        assertEquals("Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("jdi severní_koridor_žaláře"));

        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("severní_koridor_žaláře", hra.getHerniPlan().getPreMagicOrigin().getNazev());
    }

    @Test
    public void carymaryfukPreneseZKrajeRealityDoReality() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi ve své cele\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: tělo_žalářníka, zámek_okovů\n" +
                "Východy: severní_koridor_žaláře", hra.zpracujPrikaz("carymaryfuk"));
    }

    @Test
    public void carymaryfukPreneseZKrajeRealityDoSpravnehoProstoru() {
        assertEquals("Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("jdi severní_koridor_žaláře"));

        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v severním koridoru žaláře\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: dveře_celi_1\n" +
                "Východy: moje_cela, oubliette, střední_koridor_žaláře", hra.zpracujPrikaz("carymaryfuk"));
    }

    @Test
    public void abrakadabraPreneseZKrajeRealityDoAbrakadabraSpace() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Eoeoeoeeeee... teleportuješ se zpět!\n" +
                "Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));
    }

    @Test
    public void mluvSSfinga() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Hmm, ten starý blázen našel dalšího nešťastníka a přesvědčil jej pokoušet osud.\n" +
                "Nuže dobrá, chceš-li tabulu rasu, řekni mi génie, kolike je 2 + 2?\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:",  hra.zpracujPrikaz("mluv s sfinga"));
    }

    @Test
    public void odpovedSfinceSpatne() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Řekl jsi: \"1\"\n" +
                "Sfinga na tebe vrhne opovrhavý pohled. Nádech, povzdech a spálí tě na uhel svým pohledem.\n" +
                "Prohrál si! Výce štěstí příště!\n\n",  hra.zpracujPrikaz("řekni 1"));

        assertTrue(hra.konecHry());
    }

    @Test
    public void odpovedSfinceDobre() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Řekl jsi: \"4\"\n" +
                "Sfinga se na tebe udiveně podíva s obdivem na tváři. Takové ohromení je hodno odměny!\n" +
                "Sfinga před tebe položí tabulu rasu!\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga, tabula_rasa\n" +
                "Východy:",  hra.zpracujPrikaz("řekni 4"));

        assertFalse(hra.konecHry());
    }

    @Test
    public void seberTabulaRasa() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Řekl jsi: \"4\"\n" +
                "Sfinga se na tebe udiveně podíva s obdivem na tváři. Takové ohromení je hodno odměny!\n" +
                "Sfinga před tebe položí tabulu rasu!\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga, tabula_rasa\n" +
                "Východy:", hra.zpracujPrikaz("řekni 4"));

        assertEquals("Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu: tabula_rasa\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("seber tabula_rasa"));
    }

    @Test
    public void tabulaRasaNelzeSebratJeliPlnyBatoh() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Řekl jsi: \"4\"\n" +
                "Sfinga se na tebe udiveně podíva s obdivem na tváři. Takové ohromení je hodno odměny!\n" +
                "Sfinga před tebe položí tabulu rasu!\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga, tabula_rasa\n" +
                "Východy:", hra.zpracujPrikaz("řekni 4"));

        hra.getHerniPlan().getBatoh().vlozDoBatohu(
                new Vec("blocker1", true, false, null, hra.getHerniPlan())
        );
        hra.getHerniPlan().getBatoh().vlozDoBatohu(
                new Vec("blocker2", true, false, null, hra.getHerniPlan())
        );
        hra.getHerniPlan().getBatoh().vlozDoBatohu(
                new Vec("blocker3", true, false, null, hra.getHerniPlan())
        );
        hra.getHerniPlan().getBatoh().vlozDoBatohu(
                new Vec("blocker4", true, false, null, hra.getHerniPlan())
        );

        assertEquals("tabula_rasa se ti do batohu nevejde, musíš je prvně vyprázdnit\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu: blocker1, blocker2, blocker3, blocker4\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("seber tabula_rasa"));
    }

    @Test
    public void predejTabulaRasaCarokneznikovy() {
        assertEquals("Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:", hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Hwazaaaaa... Teleportuješ se\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("carymaryfuk"));

        assertEquals("Řekl jsi: \"4\"\n" +
                "Sfinga se na tebe udiveně podíva s obdivem na tváři. Takové ohromení je hodno odměny!\n" +
                "Sfinga před tebe položí tabulu rasu!\n" +
                "Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu:\n" +
                "Seznam věcí v místnosti: sfinga, tabula_rasa\n" +
                "Východy:", hra.zpracujPrikaz("řekni 4"));

        assertEquals("Jsi v místnosti z bílého mramoru\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu: tabula_rasa\n" +
                "Seznam věcí v místnosti: sfinga\n" +
                "Východy:", hra.zpracujPrikaz("seber tabula_rasa"));

        assertEquals("Eoeoeoeeeee... teleportuješ se zpět!\n" +
                "Ocitl si se na cimbuří veže nevýdané výšky\n" +
                "Máš na sobě: okovy\n" +
                "Seznam věcí v batohu: tabula_rasa\n" +
                "Seznam věcí v místnosti: čarokněžník\n" +
                "Východy:",  hra.zpracujPrikaz("abrakadabra"));

        assertEquals("Čarokněžník s jiskrou v oku nadšeně převezme tabulu rasu. Na oplátku tě vezme jako svého učedníka!\n" +
                "Vyhrál si! Dobrá práce!\n\n",  hra.zpracujPrikaz("mluv s čarokněžník"));

        assertTrue(hra.konecHry());
    }
}
