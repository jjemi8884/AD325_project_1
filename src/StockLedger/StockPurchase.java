package Deque.StockLedger;

/**
 * the stock purchase class for use with stock legders.
  */
public class StockPurchase {
    private String stockSymbol;
    private double stockPrice;
    private int numOfShares; // for this program this should be 1 stock per purchase

    /**
     * The constructor of the stock purchase for use in a linked structure.
     * O(1) no loops in constructor
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
     * O(1) again no loops in the constructor
     * @param numOfShares - is the number of shares bought
     */
    public void setNumShares(int numOfShares){
        this.numOfShares = numOfShares;
    }// end setNumShares

    /**
     * returns the number of shares that were bought
     * O(1) no loops
     * @return - get number shares as an integer
     */
    public int getNumShares(){
        return this.numOfShares;
    }// end getNumShares

    /**
     * sets the stock price
     * O(1) no loops
     * @param price - required to be in double format
     */
    public void setStockPrice(double price){
        this.stockPrice = price;
    }//end setStockPrice

    /**
     * returns the stock price of the purchase
     * O(1) again, no loops
     * @return - in a double variable
     */
    public double getStockPrice(){
        return stockPrice;
    }//end getStockPrice

    /**
     * sets the stock symbol of the purchase
     * O(1) no loops just straight commands
     * @param symbol - required to be in string type
     */
    public void setStockSymbol(String symbol){
        this.stockSymbol = symbol;
    }// end setStockSymbol

    /**
     * returns the stock symbol
     * O(1) no loops
     * @return - as a string object
     */
    public String getStockSymbol(){
        return stockSymbol;
    }// end getStockSymbol

    /**
     * returns the total cost of this stock as a double by multiplying the cost of each
     * stock option * the number of options.
     * O(1) no loops
     * @return - num of shares * cost of shares
     */
    public double getTotalCost(){
        return numOfShares * stockPrice;
    }// end getTotalCost

}
