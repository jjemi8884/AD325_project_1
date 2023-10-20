package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;

public class Main {
    public static void main(String[] args) throws EmptyQueueException {
        StockLedger ledger = new StockLedger();
        ledger.buy("AAPL", 20, 45.00);
        ledger.buy("AAPL", 50, 75.00);
        ledger.buy("MSFT",20, 95.00);
        System.out.println(ledger);
        ledger.sell("AAPL", 30, 65.00);
        System.out.println(ledger);
        ledger.sell("AAPL", 10, 65.00);
        System.out.println(ledger);
        ledger.buy("AAPL", 100, 20.00);
        ledger.buy("AAPL", 20, 24.00);
        ledger.buy("TSLA", 200, 36.00);
        System.out.println(ledger);
        ledger.sell("AAPL", 10, 65.00);
        System.out.println(ledger);
        ledger.sell("TSLA", 150, 30.00);
        System.out.println(ledger);
        ledger.buy("MSFT", 5, 60.00);
        ledger.buy("MSFT", 5, 70.00);
        System.out.println(ledger);
        ledger.sell("MSFT", 4, 30.00);
        System.out.println(ledger);
        ledger.sell("MSFT", 2, 30.00);
        System.out.println(ledger);
        //--- extra test that different things
        ledger.sell("TSLA", 49, 30.00); // remove TSLA to one share
        System.out.println(ledger); // make sure it prints out one share

    }
}
