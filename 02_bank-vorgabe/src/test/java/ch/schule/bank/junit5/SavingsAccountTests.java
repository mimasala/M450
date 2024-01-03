package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;



/**
 * Tests f�r die Klasse SavingsAccount.
 *
 * @author Roger H. J&ouml;rg
 * @version 1.0
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author XXX
 * @version 1.0
 */
public class SavingsAccountTests
{
	@Test
	public void testWithdraw() {
		SavingsAccount account = new SavingsAccount("SA-1000");

		account.deposit(20220101, 1000);

		boolean withdrawSuccess = account.withdraw(20220102, 500);
		assertTrue(withdrawSuccess, "Withdrawal should be successful");
		assertEquals(500, account.getBalance(), "Balance should be reduced by the withdrawn amount");

		withdrawSuccess = account.withdraw(20220103, 1000);
		assertFalse(withdrawSuccess, "Withdrawal should fail due to insufficient funds");
		assertEquals(500, account.getBalance(), "Balance should remain unchanged after failed withdrawal");

		withdrawSuccess = account.withdraw(20220104, -100);
		assertFalse(withdrawSuccess, "Withdrawal should fail due to negative withdrawal amount");
		assertEquals(500, account.getBalance(), "Balance should remain unchanged after failed withdrawal");
	}
}

