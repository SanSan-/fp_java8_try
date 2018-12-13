package utils;

import dao.Account;

import java.util.function.Consumer;
import java.util.function.Function;

import static utils.AssertUtils.compare;
import static utils.AssertUtils.notCompare;

public class TestUtils {

    public static void compareAccount(Account expected, Account actual) {
        Consumer<Function<Account, Object>> compare = compare(expected, actual);
        compare.accept(Account::getBalance);
        compare.accept(Account::getNumber);
        compare.accept(Account::getOwner);
    }

    public static void notCompareAccount(Account expected, Account actual) {
        Consumer<Function<Account, Object>> notCompare = notCompare(expected, actual);
        notCompare.accept(Account::getBalance);
        notCompare.accept(Account::getNumber);
        notCompare.accept(Account::getOwner);
    }
}
