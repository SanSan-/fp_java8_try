package monads.either;

import java.util.function.Function;

public abstract class Either<L, R> {

    protected final L left;
    protected final R value;

    protected Either(L left, R value) {
        this.left = left;
        this.value = value;
    }

    public abstract <B> Either<L, B> map(Function<? super R, ? extends B> mapper);

    public abstract <A> Either<A, R> leftMap(Function<? super L, ? extends A> mapper);

    public abstract <B> Either<L, B> flatMap(Function<? super R, Either<?, ? extends B>> mapper);

    public abstract <A> Either<A, R> leftFlatMap(Function<? super L, Either<? extends A, ?>> mapper);

    public abstract boolean isSuccess();
}
