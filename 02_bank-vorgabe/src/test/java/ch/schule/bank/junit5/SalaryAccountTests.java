package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author XXX
 * @version 1.1
 */
public class SalaryAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test() {
		long creditLimit = -5000; // Example credit limit
		SalaryAccount account = new SalaryAccount("SA-1000", creditLimit);
		account.deposit(20220101, 1000); // Initial deposit

		// Test successful withdrawal within credit limit
		boolean withdrawSuccess = account.withdraw(20220102, 1500);
		assertTrue(withdrawSuccess, "Withdrawal should be successful");
		assertEquals(-500, account.getBalance(), "Balance should reflect the withdrawal");

		// Test unsuccessful withdrawal that exceeds credit limit
		withdrawSuccess = account.withdraw(20220103, 5000);
		assertFalse(withdrawSuccess, "Withdrawal should fail as it exceeds credit limit");
		assertEquals(-500, account.getBalance(), "Balance should remain unchanged after failed withdrawal");
	}
}
