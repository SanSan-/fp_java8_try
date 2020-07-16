package monads.either;

import java.util.function.Function;

public class Success<L, A> extends Either<L, A> {

    private Success(A value) {
        super(null, value);
    }

    public static <L, R> Success<L, R> success(R value) {
        return new Success<>(value);
    }

    @Override
    public <B> Either<B, A> leftMap(Function<? super L, ? extends B> mapper) {
        return success(value);
    }

    @Override
    public <B> Either<L, B> map(Function<? super A, ? extends B> mapper) {
        return success(mapper.apply(value));
    }

    @Override
    public <B> Either<B, A> leftFlatMap(Function<? super L, Either<? extends B, ?>> mapper) {
        return success(value);
    }

    @Override
    public <B> Either<L, B> flatMap(Function<? super A, Either<?, ? extends B>> mapper) {
        return (Either<L, B>) mapper.apply(value);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
