import functional.Functional;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FunctionalTest {

	private final static List<Integer> PRIMES_100 = Arrays
		.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);

	@Test
	public void primesTest() {
		checkPrimes100(Functional.prime.apply(100));
		checkPrimes100(Functional.primez.apply(100));
	}

	private void checkPrimes100(List<Integer> primes) {
		primes.forEach(i -> Functional.print.accept(String.valueOf(i)));
		assertNotNull(primes);
		assertEquals(25, primes.size());
		assertEquals(PRIMES_100, primes);
	}

	@Test
	public void factorialTest() {
		Long factorial = Functional.fact(3L);
		Functional.print.accept(String.valueOf(factorial));
		assertEquals(6L, (long)factorial);
	}

	@Test
	public void fizzBuzzTest() {
		Functional.fizzyBuzzy.accept(100);
		List<String> fzBz = Functional.fizzBuzzList.apply(100);
		fzBz.forEach(Functional.print);
		assertEquals(99, fzBz.size());
	}
}
