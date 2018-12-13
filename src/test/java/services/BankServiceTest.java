package services;

import dao.Account;
import dao.BankConnection;
import monads.handling.Try;
import monads.reader.TryReader;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static services.BankService.*;

public class BankServiceTest {

	@Test
	public void accountingPositive() {
		TryReader<BankConnection, Account> reader =
			open("Alice", "123", new BigDecimal(100.00))
				.mapReader(acc -> credit(acc, new BigDecimal(200.00)))
				.mapReader(acc -> credit(acc, new BigDecimal(300.00)))
				.flatMap(acc -> debit(acc, new BigDecimal(400.00)));
		Try<Account> account = reader.apply(new BankConnection());
		assertFalse(account.isFailure());
	}

	@Test
	public void accountingNegative() {
		TryReader<BankConnection, Account> reader =
			open("Bill", "435", new BigDecimal(50.00))
				.mapReader(acc -> credit(acc, new BigDecimal(100.00)))
				.mapReader(acc -> credit(acc, new BigDecimal(50.00)))
				.flatMap(acc -> debit(acc, new BigDecimal(400.00)));
		Try<Account> account = reader.apply(new BankConnection());
		assertTrue(account.isFailure());
	}
}
