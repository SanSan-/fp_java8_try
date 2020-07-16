package services;

import dao.Account;
import dao.Balance;
import dao.BankConnection;
import exceptions.InsufficientBalanceException;
import monads.handling.Failure;
import monads.handling.Success;
import monads.reader.Reader;
import monads.reader.TryReader;

import java.math.BigDecimal;

public class BankService {

    public static TryReader<BankConnection, Account> open(String owner, String number, BigDecimal balance) {
        return new TryReader<>(bankConnection -> {
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                return new Failure<>(new InsufficientBalanceException());
            }
            return new Success<>(new Account(owner, number, new Balance(balance)));
        });
    }

    public static Reader<BankConnection, Account> credit(Account account, BigDecimal value) {
        return new Reader<>(bankConnection -> new Account(account.getOwner(), account.getNumber(),
                                                          new Balance(account.getBalance().getAmount().add(value))));
    }

    public static TryReader<BankConnection, Account> debit(Account account, BigDecimal value) {
        return new TryReader<>(bankConnection -> {
            if (account.getBalance().getAmount().compareTo(value) < 0) {
                return new Failure<>(new InsufficientBalanceException());
            }
            return new Success<>(new Account(account.getOwner(), account.getNumber(),
                                             new Balance(account.getBalance().getAmount().subtract(value))));
        });
    }

}
