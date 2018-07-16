package dao;

public class Account {

	private final String owner;
	private final String number;
	private final Balance balance;

	public Account(String owner, String number, Balance balance) {
		this.owner = owner;
		this.number = number;
		this.balance = balance;
	}

	public String getOwner() {
		return owner;
	}

	public String getNumber() {
		return number;
	}

	public Balance getBalance() {
		return balance;
	}
}
