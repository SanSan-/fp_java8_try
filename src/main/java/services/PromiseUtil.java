package services;

import monads.Promise;

import java.util.function.Function;
import java.util.stream.IntStream;

public class PromiseUtil {

    private static void someLongComputation() {
        IntStream.range(1, 999999999).map(i -> i * i).filter(i -> i % 2 == 0).boxed().count();
    }

    public static int slowLength(String s) {
        someLongComputation();
        return s.length();
    }

    public static int slowDouble(int i) {
        someLongComputation();
        return i * 2;
    }

    public static Function<String, Promise<Integer>> composeLongComp = s -> Promise.promise(() -> slowLength(s))
        .thenCall(i -> Promise.promise(() -> slowDouble(i)));

}
