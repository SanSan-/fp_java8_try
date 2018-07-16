import dao.Account;
import dao.BankConnection;
import java.math.BigDecimal;
import java.util.function.Function;
import monads.handling.Try;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static services.BankService.credit;
import static services.BankService.debit;
import static services.BankService.open;

public class BankServiceTest {

	@Test
	public void accountingPositive() {
		Function<BankConnection, Try<Account>> positive = (connect) ->
				open("Alice", "123", new BigDecimal(100.00)).apply(connect)
						.map(acc -> credit(acc, new BigDecimal(200.00)).apply(connect))
						.map(acc -> credit(acc, new BigDecimal(300.00)).apply(connect))
						.flatMap(acc -> debit(acc, new BigDecimal(400.00)).apply(connect));
		Try<Account> account = positive.apply(new BankConnection());
		assertFalse(account.isFailure());
	}

	@Test
	public void accountingNegative() {
		Function<BankConnection, Try<Account>> negative = (connect) ->
				open("Bill", "435", new BigDecimal(50.00)).apply(connect)
						.map(acc -> credit(acc, new BigDecimal(100.00)).apply(connect))
						.map(acc -> credit(acc, new BigDecimal(50.00)).apply(connect))
						.flatMap(acc -> debit(acc, new BigDecimal(400.00)).apply(connect));
		Try<Account> account = negative.apply(new BankConnection());
		assertTrue(account.isFailure());
	}
}
