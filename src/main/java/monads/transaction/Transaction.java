package monads.transaction;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public abstract class Transaction<T> {

	public final static long DEFAULT_TIMEOUT = 60_000L;

	protected long timeout;
	protected Propaganation propaganation;
	protected T result;
	protected int count;

	protected Transaction(Propaganation prop, Long timeout) {
		this.propaganation = prop;
		this.timeout = timeout;
		this.count = 0;
	}

	public abstract <E> Transaction<E> commit();

	public abstract <E> Transaction<E> rollback();

	public abstract Transaction<List<T>> dedicatedTx(T value);

	public abstract <E> Transaction<E> map(Callable<? super T> callable);

	public abstract <E> Transaction<E> flatMap(Function<Callable<? super T>, Transaction<E>> mapper);

	public abstract boolean isStarted();

	public abstract boolean isSuccess();

	protected int count() {
		return count;
	}

	public T getResult() {
		return (isSuccess() && !isStarted()) ? result : null;
	}
}
