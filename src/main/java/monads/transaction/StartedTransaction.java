package monads.transaction;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import static monads.transaction.Commit.doCommit;
import static monads.transaction.DedicatedTransaction.doInDedicatedTransaction;
import static monads.transaction.Rollback.doRollback;

public class StartedTransaction<T> extends Transaction<T> {

	protected StartedTransaction() {
		this(Propaganation.OPTIMISTIC, DEFAULT_TIMEOUT);
	}

	protected StartedTransaction(Propaganation prop, Long timeout) {
		super(prop, timeout);
		this.count++;
	}

	public static Transaction start() {
		return start(Propaganation.OPTIMISTIC, DEFAULT_TIMEOUT);
	}

	public static Transaction start(Long timeout) {
		return start(Propaganation.OPTIMISTIC, timeout);
	}

	public static Transaction start(Propaganation prop, Long timeout) {
		return new StartedTransaction(prop, timeout);
	}

	public <E> Transaction<E> commit() {
		int promise = count() - 1;
		return (!isStarted() || promise < 0) ? doRollback()
				: (promise > 0 ? ( Transaction<E> ) dedicatedTx(this.result)
						: doCommit(promise, ( E ) result));
	}

	public <E> Transaction<E> rollback() {
		return doRollback();
	}

	public Transaction<List<T>> dedicatedTx(T value) {
		return doInDedicatedTransaction(Collections.singletonList(value));
	}

	@Override
	public <E> Transaction<E> map(Callable<? super T> callable) {
		T value;
		try {
			value = ( T ) callable.call();
			this.result = value;
		} catch (Exception e) {
			return rollback();
		}
		return ( Transaction<E> ) this;
	}

	@Override
	public <E> Transaction<E> flatMap(Function<Callable<? super T>, Transaction<E>> mapper) {
		Transaction<? extends E> result;
		result = mapper.apply(() -> this.result);
		return ( Transaction<E> ) result;
	}

	@Override
	public boolean isStarted() {
		return true;
	}

	@Override
	public boolean isSuccess() {
		return false;
	}
}
