package BankServices;

import java.util.Comparator;

public class ByDateDescDepositComparator implements Comparator<Deposit> {

    @Override
    public int compare(Deposit o1, Deposit o2) {
        return o1.getDate() - o2.getDate();
    }
}
