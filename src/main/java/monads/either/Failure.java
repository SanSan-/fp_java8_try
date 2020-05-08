package monads.either;

import java.util.function.Function;

public class Failure<A, R> extends Either<A, R> {

    private Failure(A left, R value) {
        super(left, value);
    }

    public static <L, R> Failure<L, R> fail(L left, R value) {
        return new Failure<>(left, value);
    }

    @Override
    public <B> Either<B, R> leftMap(Function<? super A, ? extends B> mapper) {
        return fail(mapper.apply(left), value);
    }

    @Override
    public <B> Either<A, B> map(Function<? super R, ? extends B> mapper) {
        return fail(left, null);
    }

    @Override
    public <B> Either<B, R> leftFlatMap(Function<? super A, Either<? extends B, ?>> mapper) {
        return (Either<B, R>) mapper.apply(left);
    }

    @Override
    public <B> Either<A, B> flatMap(Function<? super R, Either<?, ? extends B>> mapper) {
        return fail(left, null);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
