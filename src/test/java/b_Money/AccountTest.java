package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 10, 10, new Money(100, SEK), SweBank, "Alice");
		Assert.assertEquals(true, testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		Assert.assertEquals(false, testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1",2,0, new Money(10000, SEK), SweBank, "Alice");

		for (int i = 0; i < 10; i++) {
			testAccount.tick();
		}
		Assert.assertEquals(Integer.valueOf(9930000), testAccount.getBalance());
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(5000000, SEK));
		Assert.assertEquals(Integer.valueOf(5000000), testAccount.getBalance());

	}
	@Test
	public void testGetBalance() {
		Assert.assertEquals(Integer.valueOf(10000000), testAccount.getBalance());
	}
}
