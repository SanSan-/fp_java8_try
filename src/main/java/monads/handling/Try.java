package monads.handling;

import java.util.function.Function;

public interface Try<A> {

	<B> Try<B> map(Function<A, B> f);

	<B> Try<B> flatMap(Function<A, Try<B>> f);

	boolean isFailure();
}
