package BankServices;




import java.util.*;

public class Bank {
	private String name;

	private List<Account> accounts = new ArrayList<>();
	private List<Account> deletedAccounts = new ArrayList<>();

	public Bank(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public int createAccount(String name, int date, double initial) {
		Account account = new Account(accounts.size() + 1, name, date, initial);
		accounts.add(account);
		return accounts.size();
	}
	
	public Account deleteAccount(int code, int date) throws InvalidCode, InvalidValue {
		Account account = getAccount(code);
		try {
			account.withdraw(date, account.getBalance());
		} catch (InvalidValue e) {
			e.printStackTrace();
		}
		return account;
	}
	
	public Account getAccount(int code) throws InvalidCode {
		if (code < 1 || code > accounts.size()) new InvalidCode();
		return accounts.get(code - 1);
	}

	public void deposit(int code, int date, double value) throws InvalidCode {
		Account account = getAccount(code);
		account.deposit(date, value);
	}

	public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
		Account account = getAccount(code);
		account.withdraw(date, value);
	}
	
	public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
		withdraw(fromCode, date, value);
		deposit(toCode, date, value);
	}
	
	public double getTotalDeposit() {
		double sum = 0;
		for (int i = 0; i < accounts.size(); i++) {
			Account account = accounts.get(i);
			sum += account.getBalance();
		}
		return sum;
	}
	
	public List<Account> getAccounts() {
		Collections.sort(accounts, new ByCodeAccountComparator());
		return accounts;
	}
	

	public List<Account> getZeroAccounts() {
		return deletedAccounts;
	}

	public List<Account> getAccountsByBalance(double low, double high) {

		List<Account> accountsByBalance = new ArrayList<>();
		for (int i = 0; i < accounts.size(); i++) {
			Account account = accounts.get(i);
			if (account.getBalance() >= low && account.getBalance() <= high) {
				accountsByBalance.add(account);
			}
		}
		Collections.sort(accountsByBalance, new ByBalanceAccountComparatorReversed());
		return accountsByBalance;
	}
	
	public long getNumberHigher(double min) {
		long count = 0;
		for (int i = 0; i < accounts.size(); i++) {
			Account account = accounts.get(i);
			if (account.getBalance() >= min) {
				count++;
			}
		}
		return count;
	}

	public class ByCodeAccountComparator implements Comparator<Account> {
		@Override
		public int compare(Account o1, Account o2) {
			return o1.getCode() - o2.getCode();
		}
	}

	public class ByBalanceAccountComparatorReversed implements Comparator<Account> {
		@Override
		public int compare(Account o1, Account o2) {
			return (int) (o2.getBalance() - o1.getBalance());
		}
	}


}
