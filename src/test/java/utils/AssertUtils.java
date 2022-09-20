package utils;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class AssertUtils {

    public static <T, R> Consumer<Function<T, R>> compare(T expected, T actual) {
        return getter -> assertEquals(getter.apply(expected), getter.apply(actual));
    }

    public static <T, R> Consumer<Function<T, R>> notCompare(T expected, T actual) {
        return getter -> assertNotSame(getter.apply(expected), getter.apply(actual));
    }
}
