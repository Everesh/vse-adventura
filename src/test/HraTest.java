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
                "TODO\n" +
                       "Jsi ve své cele\n" +
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

        // 2. krok hry JDI hluboky_les
        assertEquals( "Jsi v mistnosti/prostoru temný les, ve kterém lze potkat vlka.\n" +
                "východy: chaloupka jeskyně les",hra.zpracujPrikaz("jdi hluboký_les"));
        // 3. krok hry JDI jeskyne
        assertEquals( "Jsi v mistnosti/prostoru les s jahodami, malinami a pramenem vody.\n" +
                "východy: domeček hluboký_les" ,hra.zpracujPrikaz("jdi jeskyne"));
        // Overeni epilogu
        assertEquals( "Jsi v mistnosti/prostoru les s jahodami, malinami a pramenem vody.\n" +
                "východy: domeček hluboký_les" ,hra.vratEpilog());
    }
}
