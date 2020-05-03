package monads.either;

import java.util.function.Function;

public class Success <L, R> extends Either<L, R> {

    private Success(R value) {
        super(null, value);
    }

    public static <A> Either success(A value) {
        return new Success<>(value);
    }

    @Override
    public <B> Either<L, B> map(Function<? super R, ? extends B> mapper) {
        return success(mapper.apply(value));
    }

    @Override
    public <A> Either<A, R> leftMap(Function<? super L, ? extends A> mapper) {
        return success(value);
    }

    @Override
    public <B> Either<L, B> flatMap(Function<? super R, Either<?, ? extends B>> mapper) {
        return (Either<L, B>) mapper.apply(value);
    }

    @Override
    public <A> Either<A, R> leftFlatMap(Function<? super L, Either<? extends A, ?>> mapper) {
        return success(value);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
