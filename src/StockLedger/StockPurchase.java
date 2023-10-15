package Deque.StockLedger;

/**
 * the stock purchase class for use with stock legders.
  */
public class StockPurchase {
    private String stockSymbol;
    private double stockPrice;
    private int numOfShares;

    /**
     * The constructor of the stock purchase for use in a linked structure.
     * @param symbol - in String format
     * @param price - in double type
     * @param numOfShares - in int type
     */
    public StockPurchase(String symbol, double price, int numOfShares) {
        this.setStockSymbol(symbol);
        this.setStockPrice(price);
        this.setNumShares(numOfShares);
    } //end StockPurchase constructor

    /**
     * set the number of shares in a purchase
     * @param numOfShares - is the number of shares bought
     */
    public void setNumShares(int numOfShares){
        this.numOfShares = numOfShares;
    }// end setNumShares

    /**
     * returns the number of shares that were bought
     * @return - get number shares as an integer
     */
    public int getNumShares(){
        return this.numOfShares;
    }// end getNumShares

    /**
     * sets the stock price
     * @param price - required to be in double format
     */
    public void setStockPrice(double price){
        this.stockPrice = price;
    }//end setStockPrice

    /**
     * returns the stock price of the purchase
     * @return - in a double variable
     */
    public double getStockPrice(){
        return stockPrice;
    }//end getStockPrice

    /**
     * sets the stock symbol of the purchase
     * @param symbol - required to be in string type
     */
    public void setStockSymbol(String symbol){
        this.stockSymbol = symbol;
    }// end setStockSymbol

    /**
     * returns the stock symbol
     * @return - as a string object
     */
    public String getStockSymbol(){
        return stockSymbol;
    }// end getStockSymbol

    /**
     * returns the total cost of this stock as a double by multiplying the cost of each
     * stock option * the number of options.
     * @return - num of shares * cost of shares
     */
    public double getTotalCost(){
        return numOfShares * stockPrice;
    }// end getTotalCost

}
