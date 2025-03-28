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
        assertEquals("Vítejte!\n" +
                "Toto je příběh o Červené Karkulce, babičce a vlkovi.\n" +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\n" +
                "Jsi v mistnosti/prostoru domeček, ve kterém bydlí Karkulka.\nvýchody: les", hra.vratUvitani());
        // 1. krok hry JDI les
        assertEquals( "Jsi v mistnosti/prostoru les s jahodami, malinami a pramenem vody.\n" +
                "východy: domeček hluboký_les" ,hra.zpracujPrikaz("jdi les"));
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
