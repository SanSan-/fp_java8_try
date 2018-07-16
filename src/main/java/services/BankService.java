package services;

import dao.Account;
import dao.Balance;
import dao.BankConnection;
import exceptions.InsufficientBalanceException;
import java.math.BigDecimal;
import java.util.function.Function;
import monads.handling.Failure;
import monads.handling.Success;
import monads.handling.Try;

public class BankService {

	public static Function<BankConnection, Try<Account>> open(String owner, String number,
			BigDecimal balance) {
		return bankConnection -> {
			if (balance.compareTo(BigDecimal.ZERO) < 0) {
				return new Failure<>(new InsufficientBalanceException());
			}
			return new Success<>(new Account(owner, number, new Balance(balance)));
		};
	}

	public static Function<BankConnection, Account> credit(Account account, BigDecimal value) {
		return bankConnection -> new Account(account.getOwner(), account.getNumber(),
				new Balance(account.getBalance().getAmount().add(value)));
	}

	public static Function<BankConnection, Try<Account>> debit(Account account, BigDecimal value) {
		return bankConnection -> {
			if (account.getBalance().getAmount().compareTo(value) < 0) {
				return new Failure<>(new InsufficientBalanceException());
			}
			return new Success<>(new Account(account.getOwner(), account.getNumber(),
					new Balance(account.getBalance().getAmount().subtract(value))));
		};
	}

}
