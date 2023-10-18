package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;
import Deque.Deque.LinkedDeque;

public class Main {
    public static void main(String[] args) throws EmptyQueueException {
        LinkedDeque<Integer> ld = new LinkedDeque<Integer>();
        ld.addToBack(4);
        System.out.print(ld.removeFront());

    }
}
