package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author XXXX
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void testDepositWithBonus() {
		PromoYouthSavingsAccount account = new PromoYouthSavingsAccount("PY-1000");
		long depositAmount = 1000;
		long expectedBonus = depositAmount / 100;

		boolean depositSuccess = account.deposit(20220101, depositAmount);
		long expectedBalance = depositAmount + expectedBonus;

		assertTrue(depositSuccess, "Deposit should be successful");
		assertEquals(expectedBalance, account.getBalance(), "Balance should include 1% bonus");
	}
}
