package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Functional {

	public static Consumer<String> print = System.out::println;

	private static Map<Integer, String> fizzbuzz = new TreeMap<>();

	static {
		fizzbuzz.put(3, "Fizz");
		fizzbuzz.put(5, "Buzz");
	}

	private static TripleFunction<List<Integer>, Integer, Integer, List<Integer>> primes = (acc, k, n) -> (k > n) ? acc :
		curry(Functional.primes).apply(
			acc.stream().anyMatch(i -> k % i == 0) ? acc : Stream.concat(acc.stream(), Stream.of(k))
				.collect(Collectors.toList())).apply(k + 1).apply(n);

	public static IntFunction<List<Integer>> prime = n -> curry(primes).apply(new ArrayList<>()).apply(2).apply(n);

	private static IntPredicate isPrime = n -> Stream.iterate(2, i -> i + 1).limit((long)Math.sqrt(n)).noneMatch(
		i -> n % i == 0);

	public static IntFunction<List<Integer>> primez = n -> Stream
		.concat(Stream.of(2), IntStream.range(2, n).filter(Functional.isPrime).boxed())
		.collect(Collectors.toList());

	private static UnaryOperator<Long> factorial = x -> x == 0 ? 1 : x * Functional.factorial.apply(x - 1);

	private static IntFunction<String> check = i -> Optional.ofNullable(
		fizzbuzz.keySet().stream().filter(n -> i % n == 0).map(fizzbuzz::get)
			.collect(Collectors.joining(""))).filter(s -> !s.isEmpty()).orElse(String.valueOf(i));

	public static IntConsumer fizzyBuzzy = n -> IntStream.range(1, n).forEach(i -> print.accept(check.apply(i)));

	public static IntFunction<List<String>> fizzBuzzList = n -> IntStream.range(1, n).mapToObj(i -> check.apply(i))
		.collect(Collectors.toList());

	public static Long fact(Long x) {
		return factorial.apply(x);
	}

	public static <P1, P2, P3, R> Function<P1, Function<P2, Function<P3, R>>> curry(TripleFunction<P1, P2, P3, R> f) {
		return p1 -> p2 -> p3 -> f.apply(p1, p2, p3);
	}

	public interface TripleFunction<E, T, U, R> {

		R apply(E e, T t, U u);
	}
}
