package monads.transaction;

public class Commit<T> extends StartedTransaction<T> {

    private Commit(int count, T value) {
        super();
        this.count = count;
        this.store = value;
    }

    public static <T> Commit<T> doCommit(int count, T value) {
        return new Commit<>(count, value);
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}
