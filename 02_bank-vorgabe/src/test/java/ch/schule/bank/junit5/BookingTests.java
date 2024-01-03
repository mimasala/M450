package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests
{
	/**
	 * Tests f�r die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization() {
		int testDate = 20220101;
		long testAmount = 100000;

		Booking booking = new Booking(testDate, testAmount);

		assertEquals(testDate, booking.getDate(), "Booking date should match the initialization value");
		assertEquals(testAmount, booking.getAmount(), "Booking amount should match the initialization value");
	}


	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Booking booking = new Booking(20220101, 100000);
		booking.print(500000);
		String expectedOutput = """
				12.12.58136       1.00       6.00""";
		assertEquals(expectedOutput, outContent.toString().trim());
		System.setOut(System.out);
	}

}
