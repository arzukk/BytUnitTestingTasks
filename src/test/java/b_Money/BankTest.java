package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}


@Test
public void testOpenAccount() {
		//trying to see if the exception works here
	try {
		String accountID = "Arzu";
		SweBank.openAccount(accountID);
		Assert.assertEquals("Arzu" ,  SweBank.getAccount("Arzu").getAccountName());
	} catch (AccountExistsException e) {
		fail("AccountExistsException should not be thrown");
	}
}


	@Test
	public void testDeposit() throws AccountDoesNotExistException, AccountExistsException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
		assertEquals(500, SweBank.getBalance("Ulrika").intValue());
		//deposit
	 }

	@Test
	public void testWithdraw() throws AccountDoesNotExistException, AccountExistsException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
		SweBank.withdraw("Ulrika", new Money(400, SEK));
		assertEquals(100, SweBank.getBalance("Ulrika").intValue());
		//testing deposit and withdraw together

}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException, AccountExistsException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
		assertEquals(500, SweBank.getBalance("Ulrika").intValue());
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException, AccountExistsException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
		assertEquals(500, SweBank.getBalance("Ulrika").intValue());
		SweBank.transfer("Ulrika", SweBank, "Bob", new Money(100, SEK));
		//after the deposit checking if the money reached bob and is taken from ulrikas acc
		assertEquals(400, SweBank.getBalance("Ulrika").intValue());
		assertEquals(100, SweBank.getBalance("Bob").intValue());
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException, AccountExistsException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.addTimedPayment("Ulrika", "1", new Integer(0), new Integer(0), new Money(100, SEK), SweBank, "Bob");
		SweBank.tick();
		SweBank.tick();
		Assert.assertEquals(new Integer(800), SweBank.getBalance("Ulrika"));
		//timed payment made 2 times here means 200 sek is withdrawn and its proven in the final baalnce

	}
}
