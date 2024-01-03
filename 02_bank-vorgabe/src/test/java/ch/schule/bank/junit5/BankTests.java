package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Bank;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        String youthAccountId = bank.createPromoYouthSavingsAccount();
        String salaryAccountId = bank.createSalaryAccount(-5000);

        assertNotNull(savingsAccountId);
        assertNotNull(youthAccountId);
        assertNotNull(salaryAccountId);
        assertNotEquals(savingsAccountId, youthAccountId);
        assertNotEquals(youthAccountId, salaryAccountId);
    }


    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        Bank bank = new Bank();
        String accountId = bank.createSavingsAccount();
        assertTrue(bank.deposit(accountId, 20220101, 1000));
        assertEquals(1000, bank.getBalance(accountId));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        Bank bank = new Bank();
        String accountId = bank.createSavingsAccount();
        bank.deposit(accountId, 20220101, 500);
        assertTrue(bank.withdraw(accountId, 20220102, 200));
        assertEquals(300, bank.getBalance(accountId));
        assertFalse(bank.withdraw(accountId, 20220103, 500));
    }


    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Bank bank = new Bank();
        String accountId = bank.createSavingsAccount();
        bank.deposit(accountId, 20220101, 1000);
        bank.deposit(accountId, 20220201, 500);
        bank.print(accountId);
        String expectedOutput = """
                Kontoauszug 'S-1000'
                Datum          Betrag      Saldo
                12.12.58136       0.01       0.01
                22.03.58137       0.01       0.01"""
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutput, outContent.toString().trim());
        System.setOut(System.out);
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Bank bank = new Bank();
        String accountId = bank.createSavingsAccount();
        bank.deposit(accountId, 20220101, 1000);
        bank.deposit(accountId, 20220201, 500);
        bank.print(accountId, 2022, 1);
        String expectedOutput = """
                                   Kontoauszug 'S-1000' Monat: 1.2022
                                   Datum          Betrag      Saldo"""
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutput, outContent.toString().trim());
        System.setOut(System.out);
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        Bank bank = new Bank();
        bank.createSavingsAccount();
        bank.createPromoYouthSavingsAccount();
        bank.deposit("S-1000", 20220101, 1000);
        bank.deposit("Y-1001", 20220101, 500);
        assertEquals(-1505, bank.getBalance());
    }


    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Bank bank = new Bank();
        for (int i = 0; i < 10; i++) {
            String accountId = bank.createSavingsAccount();
            bank.deposit(accountId, 20220101, 1000 * (i + 1));
        }

        bank.printTop5();

        String expectedOutput = """
                S-1009: 10000
                S-1008: 9000
                S-1007: 8000
                S-1006: 7000
                S-1005: 6000"""
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        assertEquals(expectedOutput, outContent.toString().trim());

        System.setOut(System.out);
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testBottom5() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Bank bank = new Bank();
        for (int i = 0; i < 10; i++) {
            String accountId = bank.createSavingsAccount();
            bank.deposit(accountId, 20220101, 1000 * (i + 1)); // Different balances
        }
        bank.printBottom5();
        String expectedOutput = """
                S-1000: 1000
                S-1001: 2000
                S-1002: 3000
                S-1003: 4000
                S-1004: 5000"""
                .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        assertEquals(expectedOutput, outContent.toString().trim());
        System.setOut(System.out);
    }

}
