import functional.Functional;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FunctionalTest {

	@Test
	public void primeTest() {
		List<Integer> primes = Functional.prime.apply(100);
		assertNotNull(primes);
		assertEquals(25, primes.size());
		assertEquals(Arrays
				.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
						83, 89, 97), primes);
	}

	@Test
	public void factorialTest() {
		assertEquals(java.util.Optional.of(6L), java.util.Optional.of(Functional.fact(3L)));
	}

	@Test
	public void fizzBuzzTest() {
		Functional.fizzyBuzzy.accept(100);
		List<String> fzBz = Functional.fizzBuzzList.apply(100);
		fzBz.forEach(fb -> Functional.print.accept(fb));
		assertEquals(99, fzBz.size());
	}
}
