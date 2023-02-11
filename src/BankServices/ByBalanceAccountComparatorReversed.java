package BankServices;

import java.util.Comparator;

public class ByBalanceAccountComparatorReversed implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        return (int) (o2.getBalance() - o1.getBalance());
    }
}
