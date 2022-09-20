package monads;

import monads.either.Either;
import org.junit.jupiter.api.Test;

import static monads.either.Failure.fail;
import static monads.either.Success.success;
import static org.junit.jupiter.api.Assertions.*;

public class EitherMonadTest {

    private static final String UNEXPECTED_EITHER_ROUTE = "Unexpected Either route!";

    public static <T> T throwTestError(T something) {
        try {
            throw new Exception(UNEXPECTED_EITHER_ROUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return something;
    }

    public static <T> Either<T, T> throwFlatTestError(T something) {
        try {
            throw new Exception(UNEXPECTED_EITHER_ROUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success(something);
    }

    @Test
    public void test_success() {
        Integer expected = 4232;
        final Either<Object, String> successEither = success(expected).map(actual -> {
            assertEquals(expected, actual);
            return actual.toString();
        }).leftMap(EitherMonadTest::throwTestError);
        assertTrue(successEither.isSuccess());
    }

    @Test
    public void test_success_flat_right() {
        final Either<Object, String> rightConversion = success(753).leftFlatMap(EitherMonadTest::throwFlatTestError)
                .flatMap(actual -> success(actual.toString() + "right"))
                .map(result -> {
                    assertEquals("753right", result);
                    return result;
                })
                .leftMap(EitherMonadTest::throwTestError);
        assertTrue(rightConversion.isSuccess());
    }

    @Test
    public void test_success_flat_left() {
        final Either<Object, Object> leftConversion = success(146).leftFlatMap(EitherMonadTest::throwFlatTestError)
                .flatMap(actual -> fail(actual.toString() + "left", null))
                .map(EitherMonadTest::throwTestError)
                .leftMap(left -> {
                    assertEquals("146left", left);
                    return left;
                });
        assertFalse(leftConversion.isSuccess());
    }

    @Test
    public void test_fail() {
        Integer expected = 1534;
        final Either<String, Object> failEither = fail(expected, null).map(EitherMonadTest::throwTestError)
                .leftMap(actual -> {
                    assertEquals(expected, actual);
                    return actual.toString();
                });
        assertFalse(failEither.isSuccess());
    }

    @Test
    public void test_fail_flat_right() {
        final Either<Object, Object> rightConversion = fail(283, null).flatMap(EitherMonadTest::throwFlatTestError)
                .leftFlatMap(actual -> success(actual.toString() + "right"))
                .map(result -> {
                    assertEquals("283right", result);
                    return result;
                })
                .leftMap(EitherMonadTest::throwTestError);
        assertTrue(rightConversion.isSuccess());
    }

    @Test
    public void test_fail_flat_left() {
        final Either<Object, Object> leftConversion = fail(360, null).flatMap(EitherMonadTest::throwFlatTestError)
                .leftFlatMap(actual -> fail(actual.toString() + "left", null))
                .map(EitherMonadTest::throwTestError)
                .leftMap(left -> {
                    assertEquals("360left", left);
                    return left;
                });
        assertFalse(leftConversion.isSuccess());
    }
}
