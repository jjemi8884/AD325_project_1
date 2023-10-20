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
     */
     public StockLedger(){
       stockList = new ArrayList<LedgerEntry>();
       monies = 0.0;
     }//end constructor

    /**
     * Records a stock purchase in this ledger.
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesBought  The number of shares purchased.
     * @param pricePerShare The price per share.
     */
    public void buy(String stockSymbol, int sharesBought, double pricePerShare) {
        boolean contains = false;
        //did not use contains or getEntry because we did need to.
        //this is O(N) operation, where as I am only going through the list once to find and
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
     *
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
     *
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
     *
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

    public String toString(){
        return printLedger();
    }

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
     * stock was sold below the pruchase cost.
     * @return - String - total gains of the stock
     */
    public String printGains(){
        String answer = "";
        answer = "Stock symbol total gains\n";
        for(LedgerEntry le : stockList){
            answer = answer + le.getStockSymbol() + "  |  " + le.getGains() + " dollars\n";
        }//end for
        return answer;
    }//end printGains
}//end class
