package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;
import Deque.Deque.LinkedDeque;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LedgerEntry  {
    private LinkedDeque<StockPurchase> ledgerDisk;
    private String stockSymbol;
    private int totalShares;

    /**
     * construction with only the symbol supplied and no shares bought
     * @param symbol - String object
     */
    public LedgerEntry(String symbol){
        this.setStockSymbol(symbol);
        ledgerDisk = new LinkedDeque<>();
    }

    /**
     * construction that will also create the stock purchase object and add it to the linkedDeque data type.
     *
     * @param symbol - string object
     * @param price - double type
     * @param numOfStock - integer type
     */
    public LedgerEntry(String symbol, Double price, int numOfStock){
        setStockSymbol(symbol);
        this.ledgerDisk = new LinkedDeque<StockPurchase>();
        this.addPurchase(price, numOfStock);
    }


    /**
     * takes the number of inputs and adds them to the linkedDeque data type
     * does not need the stirng since the string is already of the ledger type
     * @param price - in double type
     * @param numOfStock - in int type
     * //@return - the price of the purchase, just in case we want to track that or something
     */
    public void addPurchase(double price, int numOfStock){
        for(int i = 0; i < numOfStock; i++) {
            StockPurchase sp = new StockPurchase(getStockSymbol(), price, 1);
            this.ledgerDisk.addToBack(sp);
        }
    }

    /**
     *  Remove the inputs from the stock and sell, sell, sell
     *  Since this is a deque, I will be taking from the front
     */
    public double sellStock(int numOfStock) throws EmptyQueueException {
        double stockTotalBuyPrice = 0.00;
        for(int i = 0; i < numOfStock; i++) {
            if (!ledgerDisk.isEmpty()) {
                StockPurchase sp = ledgerDisk.removeFront();
                stockTotalBuyPrice += sp.getStockPrice();
            }//end if
        }// end for
        return stockTotalBuyPrice;
    }// end sellStock

    public void clear(){
        this.ledgerDisk = null;
    }

    public void setStockSymbol(String symbol){
        this.stockSymbol = symbol;
    }

    public String getStockSymbol(){
        return this.stockSymbol;
    }

    public String toString(){
        String answer = "";
        Iterator<StockPurchase> itr = ledgerDisk.getIterator();
        TreeMap<Double, Integer> stockMap= new TreeMap<>();
        while(itr.hasNext()){

            StockPurchase stock = itr.next();
            double price = stock.getStockPrice();
            if (stockMap.containsKey(price)){
                //use a dict to count how many of each stock at what price we have
                int count = stockMap.get(price);
                count++;
                stockMap.put(price, count);
            }else{
                // in case this is the first instance of the price
                stockMap.put(price, 1);
            }//end if

            //print the results
        }//end while
        answer = "";
        for(Map.Entry<Double, Integer> itr2 : stockMap.entrySet()){
            answer = answer + itr2.getKey() + " (" + itr2.getValue() + "), ";
        }//end foreach

        return answer.substring(0, answer.length()-2);
    }//end toString





}// end class
