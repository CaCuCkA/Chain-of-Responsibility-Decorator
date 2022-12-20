import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ua.edu.ucu.apps.atm.*;


public class ATMTest {
    private static Handler handler50;

    @BeforeAll
    public static void setUp() {
        Handler handler5 = new Handler5();
        Handler handler20 = new Handler25();
        handler50 = new Handler50();

        handler50.setNextHandler(handler20);
        handler20.setNextHandler(handler5);
    }

    @Test
    public void testProcess() {
        ATM.process(100, handler50);
        System.out.println("---");
        ATM.process(80, handler50);
        System.out.println("---");
        ATM.process(60, handler50);
    }

    @Test
    void testUnsolvableProcess() {
        assertThrows(IllegalArgumentException.class, () -> ATM.process(1, handler50));
    }
}