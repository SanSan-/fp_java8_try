package monads.transaction;

public class Rollback<T> extends StartedTransaction<T> {

	public Rollback() {
		super();
		this.count = 0;
	}

	public static <T> Rollback<T> doRollback() {
		return new Rollback<>();
	}

	@Override
	public boolean isStarted() {
		return false;
	}

	@Override
	public boolean isSuccess() {
		return false;
	}

}
