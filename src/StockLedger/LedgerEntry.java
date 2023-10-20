package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;
import Deque.Deque.LinkedDeque;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LedgerEntry  {
    private LinkedDeque<StockPurchase> ledgerDisk;
    private String stockSymbol;
    private double gains;


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
     *  This will also return the stock gain/loss data that can be captured in the
     *  stock ledger and update the gains/loss variables for each stock.
     * @return double - the loss or gain of the stock purchase.
     */
    public double sellStock(int numOfStock, double price) throws EmptyQueueException {
        double total = 0.0;
        for(int i = 0; i < numOfStock; i++) {
            if (!ledgerDisk.isEmpty()) {
                StockPurchase sp = ledgerDisk.removeFront();
                total += price - sp.getStockPrice();
            }//end if
        }// end for
        this.addToGains(total);
        return total;

    }// end sellStock

    /**
     * Will clear the entire ledgerDisk
     */
    public void clear(){
        this.ledgerDisk = null;
    }//end clear

    /**
     * will set theStockSymbol
     * should only be called during the constructor cause
     * changing the stock symbol
     * could mess up other parts of the program.
     * @param symbol-
     */
    private void setStockSymbol(String symbol){
        this.stockSymbol = symbol;
    }

    /**
     * method for returning the stock symbol to outside calls.
     *
     * @return
     */
    public String getStockSymbol(){
        return this.stockSymbol;
    }

    /**
     * return a stancard string that will show stock prics and number of shares.
     * @override - toStirng in parent object
     * @return -string in format of  [stock price] ([number of stocks]), ...
     */
    public String toString(){
        String answer;
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
        String s = "shares";
        for(Map.Entry<Double, Integer> itr2 : stockMap.entrySet()){
            if(itr2.getValue() == 1){
                s = " share";
            }
            answer = answer + itr2.getKey() + " (" + itr2.getValue() + s + "), ";
        }//end foreach
        if(answer == ""){
            return answer;
        }else {
            return answer.substring(0, answer.length() - 2); //return substing to remove the ", " from the end.
        }
    }//end toString

    /**
     * will tell you if the ledger entry is empty and only has the symbol
     *
     */
    public boolean isEmpty(){
        return this.ledgerDisk.isEmpty();
    }

    /**
     * will return the to total gains of the symbol
     * @return double the gains (may be negative) of the ledger entry
     */
    public double getGains(){
        return this.gains;
    }// end getCost

    /**
     * will add to the gains (could subtract too) of the ledger
     * @param cost - double, what you want to add to the ledger gains.
     */
    private void addToGains(double cost){
        this.gains = this.gains + cost;
    }// end addToGains

}// end class
