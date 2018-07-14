package monads.validation;

import java.util.function.Function;

public class Failure<L, A> extends Validation<L, A> {

	protected final L left;

	private Failure(A value, L left) {
		super(value);
		this.left = left;
	}

	public static <L, A> Failure<L, A> failure(L left, A value) {
		return new Failure<>(value, left);
	}

	@Override
	public <B> Validation<L, B> map(Function<? super A, ? extends B> mapper) {
		return failure(left, mapper.apply(value));
	}

	@Override
	public <B> Validation<L, B> flatMap(Function<? super A, Validation<?, ? extends B>> mapper) {
		Validation<?, ? extends B> result = mapper.apply(value);
		return result.isSuccess() ? failure(left, result.value) :
				failure(((Failure<L, B>) result).left, result.value);
	}

	@Override
	public boolean isSuccess() {
		return false;
	}
}
