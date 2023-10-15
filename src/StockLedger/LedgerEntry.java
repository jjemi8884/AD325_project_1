package Deque.StockLedger;

import Deque.Deque.EmptyQueueException;
import Deque.Deque.LinkedDeque;

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
        StockPurchase sp = new StockPurchase(getStockSymbol(), price, numOfStock);
        this.ledgerDisk = new LinkedDeque<StockPurchase>();
        this.ledgerDisk.addToBack(sp);
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






}
