/* 
  Name: Muhammad Sufyan Ghani
  Student Number: 501093666
*/

/*
 *  class CartItem models an item inside a customer's cart
 *  
 */
public class CartItem {

    //Instance variables
    private String option;
    private Product product;

    // CartItem Constructor
    public CartItem(Product product, String option) {
        this.option = option;
        this.product = product;
    }

    // Getter methods needed for program
    public Product getProduct() {
        return product;
    }

    public String getProductOption() {
        return option;
    }
}