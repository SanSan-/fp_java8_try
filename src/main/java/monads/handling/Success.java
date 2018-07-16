package monads.handling;

import java.util.function.Function;

public class Success<A> implements Try<A> {

	private final A value;

	public Success(A value) {
		this.value = value;
	}

	@Override
	public <B> Try<B> map(Function<A, B> f) {
		return new Success<>(f.apply(value));
	}

	@Override
	public <B> Try<B> flatMap(Function<A, Try<B>> f) {
		return f.apply(value);
	}

	@Override
	public boolean isFailure() {
		return false;
	}
}
