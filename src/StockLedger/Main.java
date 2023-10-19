package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;

public class Main {
    public static void main(String[] args) throws EmptyQueueException {
        StockLedger ledger = new StockLedger();
        ledger.buy("AARP", 20, 45.00);
        ledger.buy("AAPL", 50, 75.00);
        ledger.buy("MSFT",20, 95.00);
        System.out.println(ledger);
        //ledger.sell("AARP", 30, 65.00);
        System.out.println(ledger);
        //ledger.sell("AAPL", 10, 65.00);
        System.out.println(ledger);
        ledger.buy
    }
}
