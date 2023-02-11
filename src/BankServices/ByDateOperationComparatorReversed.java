package BankServices;

import java.util.Comparator;

public class ByDateOperationComparatorReversed implements Comparator<Operation> {

    @Override
    public int compare(Operation o1, Operation o2) {
        return o2.getDate() - o1.getDate();
    }
}
