package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests f�r die Klasse 'Bank'.
 *
 * @author xxxx
 * @version 1.0
 */
public class BankTests {
    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {
        Bank bank = new Bank();
        String savingsAccountId = bank.createSavingsAccount();

        assertNotNull(savingsAccountId, "Savings account ID should not be null");
        assertTrue(savingsAccountId.startsWith("S-"), "Savings account ID should start with 'S-'");

    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        fail("toDo");
    }
    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        fail("toDo");
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        fail("toDo");
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        fail("toDo");
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        fail("toDo");
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        fail("toDo");
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testBottom5() {
        fail("toDo");
    }

}
