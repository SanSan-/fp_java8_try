package services;

import monads.Promise;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromiseUtilTest {

    @Test
    public void composeLongComp() throws ExecutionException, InterruptedException {
        Promise<Integer> p = PromiseUtil.composeLongComp.apply("Hello");
        assertEquals(10, (int) p.get());
    }
}
