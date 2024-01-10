package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		//testing if the get amount shows the actual value
		assertEquals(10000, SEK100.getAmount().intValue());
		assertEquals(0, EUR0.getAmount().intValue());
		assertEquals(-10000, SEKn100.getAmount().intValue());
	}

	@Test
	public void testGetCurrency() {
		//trying to find out the currency of the money
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		//showing the money in double format
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("-100.0 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		//currencies and their corresponding universal values checked
		assertEquals(1500.0, SEK100.universalValue(), 0.001);
		assertEquals(3000.0, EUR20.universalValue(), 0.001);
		assertEquals(0.0, SEK0.universalValue(), 0.001);
	}

	@Test
	public void testEqualsMoney() {
		//checking money in different currencies if they have the same global value
		assertTrue(SEK100.equals(EUR10));
		assertFalse(SEK100.equals(EUR20));
		assertFalse(SEK100.equals(SEKn100));
		assertTrue(SEK0.equals(EUR0));

	}

	@Test
	public void testAdd() {
		//adding amount of two money in different currency and checking the final currency type of the money
		Money result = SEK100.add(EUR10);
		assertEquals(20000, result.getAmount().intValue());
		assertEquals(SEK, result.getCurrency());
	}

	@Test
	public void testSub() {
		//subtracting amount of two money in different currency and checking the final currency type of the money
		Money result = SEK200.sub(EUR10);
		assertEquals(10000, result.getAmount().intValue());
		assertEquals(SEK, result.getCurrency());
	}

	@Test
	public void testIsZero() {
		//checking with money has the amount zero with iszero method
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
	}

	@Test
	public void testNegate() {
		//using negate values of the amount of money in a given currency
		Money result = SEK100.negate();
		assertEquals(-10000, result.getAmount().intValue());
		assertEquals(SEK, result.getCurrency());
	}

	@Test
	public void testCompareTo() {
		//comparing the amount of 2 money in different currency
		assertTrue(SEK100.compareTo(EUR10) == 0);
		assertTrue(SEK100.compareTo(SEK100) == 0);
		assertTrue(EUR20.compareTo(SEK100) > 0);
	}

}
