package monads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;

public class Promise<A> implements Future<A> {

    private final CompletableFuture<A> future;

    private Promise(CompletableFuture<A> future) {
        this.future = future;
    }

    public static <A> Promise<A> promise(Supplier<A> supplier) {
        return new Promise<>(CompletableFuture.supplyAsync(supplier));
    }

    public <B> Promise<B> then(Function<? super A, ? extends B> f) {
        return new Promise<>(future.thenApplyAsync(f));
    }

    public <B> Promise<B> thenCall(Function<? super A, Promise<B>> f) {
        return new Promise<>(future.thenComposeAsync(a -> f.apply(a).future));
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public A get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public A get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get();
    }
}
