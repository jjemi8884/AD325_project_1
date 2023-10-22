package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StockLedger implements StockLedgerInterface{

    ArrayList<LedgerEntry> stockList;
    Double monies;

    /**
     * the constructor will only create the arraylist for items to be added.
     * no input or outputs.
     * using the standard ArrayList length for  initial array length.
     * O(1) no loops in constructor
     */
     public StockLedger(){
       stockList = new ArrayList<>();
       monies = 0.0;
     }//end constructor

    /**
     * Records a stock purchase in this ledger.
     * O(n) this has a loop that looks for the stock symbol which means the
     * symbol could be at the end of the stocklist or not in the list, hence O(n) for worst case
     * @param stockSymbol   The stock's symbol.
     * @param sharesBought  The number of shares purchased.
     * @param pricePerShare The price per share.
     */
    public void buy(String stockSymbol, int sharesBought, double pricePerShare) {
        boolean contains = false;
        //did not use contains or getEntry because we did need to.
        //this is O(n) operation, where as I am only going through the list once to find and
        //add the new purchase
        for(LedgerEntry le : this.stockList){
            if(le.getStockSymbol().equals(stockSymbol)){
                le.addPurchase(pricePerShare, sharesBought);
                contains = true;
            }//end if
        }// end for loop

        //if this is a new entry
        if(!contains){ //means we never found the stock symbol and need to create a new entry
            LedgerEntry newEntry = new LedgerEntry(stockSymbol, pricePerShare, sharesBought);
            stockList.add(newEntry);// new purchase,
        }// end if
    }// end buy

    /**
     * Removes from this ledger any shares of a particular stock
     * that were sold and computes the capital gain or loss.
     *  O(n) since this calls the "contains" method it wil be O(n)
     * @param stockSymbol   The stock's symbol.
     * @param sharesSold    The number of shares sold.
     * @param pricePerShare The price per share.
     * @return The capital gain (loss).
     */
    public double sell(String stockSymbol, int sharesSold, double pricePerShare) throws EmptyQueueException {
        if(contains(stockSymbol)){
            LedgerEntry temp = this.getEntry(stockSymbol);
            monies += temp.sellStock(sharesSold, pricePerShare);

        }else {
            throw new IllegalArgumentException("You don't contain that stock");
        }
        return monies;
    }

    /**
     * Returns a boolean on whether the passed in stock symbol is contained in the ledger.
     * O(n) will iterate once through the list looking for the symbol
     * @param stockSymbol The stock's symbol.
     * @return Boolean of if the stock exists in the ledger.
     */
    public boolean contains(String stockSymbol) {
        for (LedgerEntry le : this.stockList) {
            if (le.getStockSymbol().equals(stockSymbol)) {
                return true;
            }//end if;
        }//end for loop
        return false;
    }//end contains

    /**
     * Returns a LedgerEntry object based on stock symbol.
     * O(n)
     * @param stockSymbol The stock's symbol.
     * @return LedgerEntry object of stock symbol.
     * @throws NoSuchElementException - for no LedgerEntry with that symbol
     */
    public LedgerEntry getEntry(String stockSymbol){
            for (LedgerEntry le : this.stockList) {
                if (le.getStockSymbol().equals(stockSymbol)) {
                    return le;
                }
                //end if
            }//end for loop
        throw new NoSuchElementException(stockSymbol + " is not in ledger");

    }//end getEntry

    /**
     * this is the override for the toStirng in the main object
     * O(n) since the call sp.toString is O(n) and is nested in the
     * foreach loop of the stockList. worst case is O(2n),
     * @return String - in a stock ledger format
     */
    public String toString(){
        return printLedger();
    }

    /**
     * return the correct ledger output in a string format to print.
     * O(n), It would be O(n^2) due to nested loops but only iterates through each stock purchase only once to map
     * them to the correct price
     * @return - String in correct format
     */
    public String printLedger(){
        String answer = "----Stock Ledger----\n";
        for(LedgerEntry sp : stockList){
            if (!sp.isEmpty()) { //don't print the ledger if there is no stock :)
                answer = answer + sp.getStockSymbol() + " " + sp.toString() + "\n";
            }
        }
        return answer;
    }

    /**
     * will return how much the stockLedger has made over the course of sells, could be negative is
     * stock was sold below the purchase cost.
     * O(2n) ---> O(n) again have nested loops that iterate through the ledger array and then each stock purchase.
     * O(n)for iterating through all stock purchases only once.
     * @return - String - total gains of the stock
     */
    public String printGains(){
        String answer = "Stock symbol total gains\n";
        for(LedgerEntry le : stockList){
            answer = answer + le.getStockSymbol() + "  |  " + le.getGains() + " dollars\n";
        }//end for
        return answer;
    }//end printGains

    /**
     * clear the ledger, and run for the hills
     * O(n) for worst case, but would be very unlikely since we are not iterating through the stock purchases,
     * only the ledgers
     */
    public void clear(){
        stockList.clear(); // used the arraylist clear to remove all entries and stock purhcases
        this.monies = 0.0;
    }

    /**
     * return the size of the ledger, or how many symbols we have worked with.
     * @return int size of the internal array that holds stock symobls
     */
    public int size(){
        return stockList.size();
    }
}//end class
