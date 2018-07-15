package monads.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static monads.validation.FailureList.failureList;

public class SuccessList<L, A> extends Success<List<L>, A> {

	private SuccessList(A value) {
		super(value);
	}

	public static <L, A> SuccessList<L, A> successList(A value) {
		return new SuccessList<>(value);
	}

	@Override
	public <B> Validation<List<L>, B> map(Function<? super A, ? extends B> mapper) {
		return new SuccessList<>(mapper.apply(value));
	}

	@Override
	public <B> Validation<List<L>, B> flatMap(
			Function<? super A, Validation<?, ? extends B>> mapper) {
		Validation<?, ? extends B> result = mapper.apply(value);
		return ( Validation<List<L>, B> ) (result.isSuccess() ?
				successList(result.value) :
				failureList(new ArrayList<L>() {{
					add((( Failure<L, B> ) result).left);
				}}, result.value));
	}

}
