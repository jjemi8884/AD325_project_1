package Deque.StockLedger;

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
        StockPurchase sp = new StockPurchase(getStockSymbol(), price, numOfStock);
        this.ledgerDisk.addToBack(sp);
    }

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
