import java.util.concurrent.ExecutionException;
import monads.Promise;
import org.junit.Test;
import services.PromiseUtil;

import static junit.framework.Assert.assertEquals;

public class PromiseUtilTest {

	@Test
	public void composeLongComp() throws ExecutionException, InterruptedException {
		Promise<Integer> p = PromiseUtil.composeLongComp.apply("Hello");
		assertEquals(10, (int) p.get());
	}
}
