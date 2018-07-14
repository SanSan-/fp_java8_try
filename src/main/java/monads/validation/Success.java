package monads.validation;

import java.util.function.Function;

public class Success<L, A> extends Validation<L, A> {

	private Success(A value) {
		super(value);
	}

	public static <L, A> Success<L, A> success(A value) {
		return new Success<>(value);
	}

	@Override
	public <B> Validation<L, B> map(Function<? super A, ? extends B> mapper) {
		return success(mapper.apply(value));
	}

	@Override
	public <B> Validation<L, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
		return (Validation<L, B>) mapper.apply(value);
	}

	@Override
	public boolean isSuccess() {
		return true;
	}
}
