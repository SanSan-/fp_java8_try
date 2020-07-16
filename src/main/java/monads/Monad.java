package monads;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Monad<T> {

    private final static Monad<?> EMPTY = new Monad<>(null);
    private final T value;

    private Monad(T value) {
        this.value = value;
    }

    public static <T> Monad<T> of(T value) {
        return new Monad<>(value);
    }

    public static <T> Monad<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static <T> Monad<T> empty() {
        Monad<T> t = (Monad<T>) EMPTY;
        return t;
    }

    public <U> Monad<U> map(Function<? super T, ? extends U> f) {
        return value == null ? empty() : new Monad<>(f.apply(value));
    }

    public <U> Monad<U> flatMap(Function<? super T, Monad<U>> f) {
        return value == null ? empty() : f.apply(value);
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

}
