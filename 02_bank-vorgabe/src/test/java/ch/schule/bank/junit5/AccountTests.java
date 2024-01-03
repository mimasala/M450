package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.bank.junit5.util.TestableAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author xxxx
 * @version 1.0
 */
public class AccountTests {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new TestableAccount("123ABC");
    }
    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        account = new TestableAccount("123ABC");
        assertEquals("123ABC", account.getId());
        assertEquals(0, account.getBalance());
        assertNull(account.getBooking());
    }

    @Test
    public void testDeposit() {
        assertTrue(account.deposit(20230101, 1000));
        assertEquals(1000, account.getBalance());

        assertFalse(account.deposit(20230102, -500));
        assertEquals(1000, account.getBalance());

        assertFalse(account.deposit(20221231, 500));
        assertEquals(1000, account.getBalance());
    }




    @Test
    public void testWithdraw() {
        assertTrue(account.withdraw(20230102, 500));
        assertEquals(-500, account.getBalance());

        assertFalse(account.withdraw(20230103, -500));
        assertEquals(-500, account.getBalance());

        assertTrue(account.withdraw(20230104, 1500));
        assertEquals(-2000, account.getBalance());

        assertFalse(account.withdraw(20221231, 100));
        assertEquals(-2000, account.getBalance());
    }

    @Test
    public void testCanTransact() {
        assertTrue(account.canTransact(20230101));
        account.deposit(20230101, 1000);
        assertTrue(account.canTransact(20230102));
        assertFalse(account.canTransact(20221231));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        account.deposit(20230101, 1000);
        account.withdraw(20230102, 500);
        account.print();
        String expectedOutPut = """
                Kontoauszug '123ABC'
                Datum          Betrag      Saldo
                22.09.58164       0.01       0.01
                23.09.58164      -0.01       0.01
                """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutPut , outContent.toString().trim() + System.getProperty("line.separator"));
    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        account.deposit(20230101, 1000);
        account.withdraw(20230115, 500);
        account.deposit(20230201, 2000);
        account.print(2023, 1);

        String expectedOutput = """
                Kontoauszug '123ABC' Monat: 1.2023
                Datum          Betrag      Saldo
                """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutput, outContent.toString().trim() + System.getProperty("line.separator"));

        outContent.reset();

        account.print(2023, 2);

        String expectedOutputFebruary = """
                Kontoauszug '123ABC' Monat: 2.2023
                Datum          Betrag      Saldo
                """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutputFebruary, outContent.toString().trim() + System.getProperty("line.separator"));


    }

}
