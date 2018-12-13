package utils;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class AssertUtils {

    public static <T, R> Consumer<Function<T, R>> compare(T expected, T actual) {
        return getter -> assertEquals("Сравниваемые значения не совпадают", getter.apply(expected), getter.apply(actual));
    }

    public static <T, R> Consumer<Function<T, R>> notCompare(T expected, T actual) {
        return getter -> assertNotSame("Сравниваемые значения совпадают", getter.apply(expected), getter.apply(actual));
    }
}
