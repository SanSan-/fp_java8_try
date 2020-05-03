package monads.either;

import java.util.function.Function;

public class Failure<L, R> extends Either<L, R> {

    private Failure(L left, R value) {
        super(left, value);
    }

    public static <A, B> Either fail(A left, B value) {
        return new Failure<>(left, value);
    }

    @Override
    public <B> Either<L, B> map(Function<? super R, ? extends B> mapper) {
        return fail(left, null);
    }

    @Override
    public <A> Either<A, R> leftMap(Function<? super L, ? extends A> mapper) {
        return fail(mapper.apply(left), null);
    }

    @Override
    public <B> Either<L, B> flatMap(Function<? super R, Either<?, ? extends B>> mapper) {
        return fail(left, null);
    }

    @Override
    public <A> Either<A, R> leftFlatMap(Function<? super L, Either<? extends A, ?>> mapper) {
        return (Either<A, R>) mapper.apply(left);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
