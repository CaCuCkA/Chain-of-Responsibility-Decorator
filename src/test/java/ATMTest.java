import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.atm.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ATMTest {
    private static Handler handler72;

    @BeforeAll
    public static void setUp() {
        Handler handler6 = new Handler6();
        Handler handler36 = new Handler36();
        handler72 = new Handler72();

        handler72.setNextHandler(handler36);
        handler36.setNextHandler(handler6);
    }

    @Test
    public void testProcess() {
        ATM.process(288, handler72);
        System.out.println("---");
        ATM.process(144, handler72);
        System.out.println("---");
        ATM.process(72, handler72);
    }

    @Test void testUnsolvableProcess() {
        assertThrows(IllegalArgumentException.class, () -> ATM.process(1, handler72));
    }
}
