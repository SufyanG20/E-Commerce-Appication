/* 
  Name: Muhammad Sufyan Ghani
  Student Number: 501093666
*/

/*
    This is the Shoe class that inherits from the Product class
*/
public class Shoes extends Product {

    /*
     * These are variables for stocks of all combinations
     * There are 2 colour posibilities and 5 sizes for each colour
     */
    private int stockBlack6;
    private int stockBlack7;
    private int stockBlack8;
    private int stockBlack9;
    private int stockBlack10;

    private int stockBrown6;
    private int stockBrown7;
    private int stockBrown8;
    private int stockBrown9;
    private int stockBrown10;

    // Shoes constructor
    public Shoes(String id, String name, double price, int stockBlack6, int stockBlack7, int stockBlack8,
            int stockBlack9, int stockBlack10, int stockBrown6, int stockBrown7, int stockBrown8, int stockBrown9,
            int stockBrown10) {

        // Used the constructor from super class Product and initialized all the other variables
        super(id, name, price, 0, Category.SHOES);
        this.stockBlack6 = stockBlack6;
        this.stockBlack7 = stockBlack7;
        this.stockBlack8 = stockBlack8;
        this.stockBlack9 = stockBlack9;
        this.stockBlack10 = stockBlack10;
        this.stockBrown6 = stockBrown6;
        this.stockBrown7 = stockBrown7;
        this.stockBrown8 = stockBrown8;
        this.stockBrown9 = stockBrown9;
        this.stockBrown10 = stockBrown10;
    }

    /**
     * Checks to see if the user has entered a valid option for the product
     * 
     * @param productOptions the user's product option stored as a String
     * @return true if the option is valid, false otherwise
     */
    public boolean validOptions(String productOptions) {

        // Used switch cases to check for all the possible options
        switch (productOptions) {
            case "Black6":
            case "Black7":
            case "Black8":
            case "Black9":
            case "Black10":
            case "Brown6":
            case "Brown7":
            case "Brown8":
            case "Brown9":
            case "Brown10":
                return true;
            default:
                return false;
        }
    }

    /**
     * Helper method to get the stock count for the product the user selected
     * 
     * @param productOptions the user's product option stored as a String
     * @return an integer representing the stock count available for the product
     */
    public int getStockCount(String productOptions) {

        // Switch cases for all the possible options
        switch (productOptions) {
            case "Black6":
                return stockBlack6;
            case "Black7":
                return stockBlack7;
            case "Black8":
                return stockBlack8;
            case "Black9":
                return stockBlack9;
            case "Black10":
                return stockBlack10;
            case "Brown6":
                return stockBrown6;
            case "Brown7":
                return stockBrown7;
            case "Brown8":
                return stockBrown8;
            case "Brown9":
                return stockBrown9;
            case "Brown10":
                return stockBrown10;
            default:
                return 0;
        }
    }

    /**
     * Helper method to set the stock count for the product
     * 
     * @param stockCount     the stock available for the product stored as an
     *                       integer
     * @param productOptions the user's product option stored as a String
     * @return void as this method only sets the stock count
     */
    public void setStockCount(int stockCount, String productOptions) {

        // Switch cases set the stock count for all the products
        switch (productOptions) {
            case "Black6":
                stockBlack6 = stockCount;
            case "Black7":
                stockBlack7 = stockCount;
            case "Black8":
                stockBlack8 = stockCount;
            case "Black9":
                stockBlack9 = stockCount;
            case "Black10":
                stockBlack10 = stockCount;
            case "Brown6":
                stockBrown6 = stockCount;
            case "Brown7":
                stockBrown7 = stockCount;
            case "Brown8":
                stockBrown8 = stockCount;
            case "Brown9":
                stockBrown9 = stockCount;
            case "Brown10":
                stockBrown10 = stockCount;

        }
    }

    /**
     * Method used to return the stock count for the product user ordered
     * 
     * @param productOptions the user's product option stored as a String
     * @return void as this method only reduces the stock count for the product
     *         ordered
     */
    public void reduceStockCount(String productOptions) {

        // Switch cases reduces for all possible products
        switch (productOptions) {
            case "Black6":
                stockBlack6--;
            case "Black7":
                stockBlack7--;
            case "Black8":
                stockBlack8--;
            case "Black9":
                stockBlack9--;
            case "Black10":
                stockBlack10--;
            case "Brown6":
                stockBrown6--;
            case "Brown7":
                stockBrown7--;
            case "Brown8":
                stockBrown8--;
            case "Brown9":
                stockBrown9--;
            case "Brown10":
                stockBrown10--;

        }
    }

    /**
     * Method to print the information for the product
     * 
     * @return void as this method only prints information about the products
     */
    public void print() {

        System.out.printf(
                "\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f Color: \"Black\" \"Brown\" Size: \"6\" \"7\" \"8\" \"9\" \"10\" ",
                super.getId(), super.getCategory(),
                super.getName(), super.getPrice());
    }
}
