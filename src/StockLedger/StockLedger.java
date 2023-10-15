package Deque.StockLedger;

import java.util.ArrayList;

public class StockLedger implements StockLedgerInterface{

    ArrayList<LedgerEntry> stockList;

    /**
     * the constructor will only create the arraylist for items to be added.
     * no input or outputs.
     * using the standard ArrayList length for  initial array length.
     */
     public StockLedger(){
       stockList = new ArrayList<LedgerEntry>();
     }//end constructor

    /**
     * Records a stock purchase in this ledger.
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesBought  The number of shares purchased.
     * @param pricePerShare The price per share.
     */
    public void buy(String stockSymbol, int sharesBought, double pricePerShare) {
        boolean sentinal1 = false;
        for(LedgerEntry le : this.stockList){
            if(le.getStockSymbol().equals(stockSymbol)){
                le.addPurchase(pricePerShare, sharesBought);
                sentinal1 = true;
            }//end if

        }// end for loop

        //if this is a new entry
        if(sentinal1){
            LedgerEntry newEntry = new LedgerEntry(stockSymbol, pricePerShare, sharesBought);
            stockList.add(newEntry); // new purchase,
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
    public double sell(String stockSymbol, int sharesSold, double pricePerShare) {
        return 0;
    }

    /**
     * Returns a boolean on whether the passed in stock symbol is contained in the ledger.
     *
     * @param stockSymbol The stock's symbol.
     * @return Boolean of if the stock exists in the ledger.
     */
    public boolean contains(String stockSymbol) {
        return false;
    }

    /**
     * Returns a LedgerEntry object based on stock symbol.
     *
     * @param stockSymbol The stock's symbol.
     * @return LedgerEntry object of stock symbol.
     */
    public LedgerEntry getEntry(String stockSymbol) {
        return null;
    }
}
