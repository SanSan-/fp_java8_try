package monads.transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DedicatedTransaction<T> extends StartedTransaction<List<T>> {

	public DedicatedTransaction(Collection<? extends T> value) {
		super();
		this.count++;
		result = new ArrayList<>(value);
	}

	public static <T> DedicatedTransaction<T> doInDedicatedTransaction(
			Collection<? extends T> value) {
		return new DedicatedTransaction<>(value);
	}

	@Override
	public boolean isStarted() {
		return true;
	}

	@Override
	public boolean isSuccess() {
		return result.isEmpty();
	}

}
