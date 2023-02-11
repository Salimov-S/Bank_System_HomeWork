package BankServices;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Account {

    private int code;
    private String ownerName;
    private int date;
    private double balance;

    private List<Operation> operations = new ArrayList<>();
    private List<Deposit> deposits = new ArrayList<>();
    private List<Withdrawal> withdrawals = new ArrayList<>();


    public Account(int code, String ownerName, int date, double balance) {
        this.code = code;
        this.ownerName = ownerName;
        this.date = date;
        this.balance = balance;

        Deposit deposit = new Deposit(this.date, balance);
        operations.add(deposit);
        deposits.add(deposit);
    }

    public int getCode() {
        return code;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getMovements() {
        Collections.sort(operations, new ByDateOperationComparatorReversed());
        return operations;
    }


    public List<Deposit> getDeposits() {
        Collections.sort(deposits, new ByDateDescDepositComparator());
        return deposits;
    }


    public List<Withdrawal> getWithdrawals() {
		Collections.sort(withdrawals, new ByDateDescWithdrawalComparator());
        return withdrawals;
    }

    public void deposit(int date, double amount) {
        if (date > this.date) this.date = date;
        balance += amount;
        Deposit deposit = new Deposit(this.date, amount);
        operations.add(deposit);
        deposits.add(deposit);
    }

    public void withdraw(int date, double amount) throws InvalidValue {
        if (amount > balance) throw new InvalidValue();
        if (date > this.date) this.date = date;
        balance -= amount;
        Withdrawal withdrawal = new Withdrawal(this.date, amount);
        operations.add(withdrawal);
        withdrawals.add(withdrawal);
    }

    public String toString() {
        return code + "," + ownerName + "," + date + "," + balance;
    }

}
