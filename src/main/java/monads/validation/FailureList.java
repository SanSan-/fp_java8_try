package monads.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FailureList<L, A> extends Failure<List<L>, A> {

	private FailureList(List<L> left, A value) {
		super(left, value);
	}

	public static <L, A> FailureList<L, A> failureList(List<L> left, A value) {
		return new FailureList<>(left, value);
	}

	@Override
	public <B> Validation<List<L>, B> map(Function<? super A, ? extends B> mapper) {
		return failureList(left, mapper.apply(value));
	}

	@Override
	public <B> Validation<List<L>, B> flatMap(
		Function<? super A, Validation<?, ? extends B>> mapper) {
		Validation<?, ? extends B> result = mapper.apply(value);
		return (Validation<List<L>, B>)(result.isSuccess() ?
			failureList(left, result.value) :
			failureList(new ArrayList<L>(left) {{
				add(((Failure<L, B>)result).left);
			}}, result.value));
	}

	@Override
	public boolean isSuccess() {
		return left.isEmpty();
	}
}
