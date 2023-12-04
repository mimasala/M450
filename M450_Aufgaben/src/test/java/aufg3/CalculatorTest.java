package aufg3;
import ch.tbz.aufg3.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(10, calculator.add(6, 4), "Addition falsch");
    }

    @Test
    void testSubtract() {
        assertEquals(2, calculator.subtract(6, 4), "Subtraktion falsch");
    }

    @Test
    void testMultiply() {
        assertEquals(24, calculator.multiply(6, 4), "Multiplikation falsch");
    }

    @Test
    void testDivide() {
        assertEquals(1.5, calculator.divide(6, 4), "Division falsch");
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0), "division durch null soll exception werfen");
    }
}