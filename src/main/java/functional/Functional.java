package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Functional {
    public static Consumer<String> print = System.out::println;

    private static TripleFunction<List<Integer>, Integer, Integer, List<Integer>> primes = (acc, k, n) -> (k > n) ?
            acc : curry(Functional.primes).apply(acc.stream().anyMatch(i -> k % i == 0) ? acc : Stream.concat(acc.stream(), Stream.of(k)).collect(Collectors.toList())).apply(k + 1).apply(n);

    public static IntFunction<List<Integer>> prime = n -> curry(primes).apply(new ArrayList<>()).apply(2).apply(n);

    private static UnaryOperator<Long> factorial = x -> x == 0 ? 1 : x * Functional.factorial.apply(x - 1);

    private static IntFunction<String> check = i -> {
        if (i % 15 == 0) return "FizzBuzz";
        if (i % 3 == 0) return "Fizz";
        if (i % 5 == 0) return "Buzz";
        return String.valueOf(i);
    };

    public static IntConsumer fizzyBuzzy = n -> IntStream.range(1, n).forEach(i -> print.accept(check.apply(i)));

    public static IntFunction<List<String>> fizzBuzzList = n -> IntStream.range(1, n).mapToObj(i -> check.apply(i)).collect(Collectors.toList());

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
