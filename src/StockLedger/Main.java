package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;

public class Main {
    public static void main(String[] args) throws EmptyQueueException {
        StockLedger ledger = new StockLedger();
        ledger.buy("AARP", 20, 45.00);
        ledger.buy("ESP", 50, 35.00);
        ledger.buy("hp", 100, 25.00);
        ledger.buy("AARP", 30, 45.00);
        ledger.buy("AARP", 40, 35.00);
        System.out.println(ledger);

    }
}
