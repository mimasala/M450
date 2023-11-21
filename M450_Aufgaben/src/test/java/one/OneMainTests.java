package one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OneMainTests {
    @Test
    void test_calculate_price(){

        ch.tbz.one.Main main = new ch.tbz.one.Main();
        boolean test_ok = true;

        double price = main.calculatePrice(10000, 0, 0, 0, 0);
        if (price != 10000) test_ok = false;

        price = main.calculatePrice(10000, 0, 0, 0, 10);
        if (price != 9000) test_ok = false;

        price = main.calculatePrice(10000, 500, 500, 0, 0);
        if (price != 11000) test_ok = false;

        Assertions.assertTrue(test_ok);
    }
}
