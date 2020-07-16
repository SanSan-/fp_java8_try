package services;

import monads.transaction.Transaction;

import java.util.concurrent.Callable;

import static monads.transaction.StartedTransaction.start;

public class TransactionManager {

    public static <T> T runInTx(Callable<T> callable, long timeout) {
        Transaction<T> tx = start(timeout).map(callable).commit();
        return tx.getStore();
    }

    public static <T> T runInDedicatedTx(Callable<T> callable, long timeout) {
        Transaction<T> tx = start(timeout).flatMap((object) -> start().map(callable)).commit();
        return tx.getStore();
    }

}
