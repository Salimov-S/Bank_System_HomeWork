package BankServices;

import java.util.Comparator;

public class ByCodeAccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        return o1.getCode() - o2.getCode();
    }
}
