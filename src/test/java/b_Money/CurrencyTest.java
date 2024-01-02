package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(0.15, SEK.getRate(), 0.001);
		assertEquals(0.20, DKK.getRate(), 0.001);
		assertEquals(1.5, EUR.getRate(), 0.001);
	}

	@Test
	public void testSetRate() {
		SEK.setRate(0.25);
		assertEquals(0.25, SEK.getRate(), 0.001);
	}

	@Test
	public void testGlobalValue() {

		assertEquals(15.0, SEK.universalValue(100), 0.001);
		assertEquals(20.0, DKK.universalValue(100), 0.001);
		assertEquals(150.0, EUR.universalValue(100), 0.001);
	}

	@Test
	public void testValueInThisCurrency() {

		assertEquals(100.0, SEK.valueInThisCurrency(100, SEK), 0.001);
		assertEquals(75.0, SEK.valueInThisCurrency(100, DKK), 0.001);
		assertEquals(10.0, SEK.valueInThisCurrency(100, EUR), 0.001);
	}
}
