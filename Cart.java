import java.util.ArrayList;

/* 
  Name: Muhammad Sufyan Ghani
  Student Number: 501093666
*/

/*
 *  class Cart models a virtual cart to store customer's items in 
 */

public class Cart {
    // Instance variables
    private ArrayList<CartItem> cartItems;

    // Constructor
    public Cart() {
        this.cartItems = new ArrayList<CartItem>();
    }

    /**
     * Method to retrieve all the items in the cart
     * 
     * @return arraylist that stores all the items in the cart
     */
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Method to add an item to the cart
     * 
     * @param item an item to add to the cart
     * @return void as this method only adds the item to the cart
     */
    public void addCartItem(CartItem item) {
        cartItems.add(item);
    }

    /**
     * Method to remove an item from the cart
     * 
     * @param productId the product to remove stored as a string
     * @return void as this method only removes an item from the cart
     */
    public void removeCartItem(String productId) {
        for (CartItem i : cartItems) {
            if (i.getProduct().getId().equals(productId)) {
                cartItems.remove(i);
            }
            break;
        }
    }

    /**
     * Method to remove all items in the cartt
     * 
     * @return void as this simply removes all items from the cart
     */
    public void removeAllItems() {
        cartItems.clear();
    }

    /**
     * Method to print all cart items
     * 
     * @return void as this method only prints the cart items
     */
    public void printCartItems() {
        for (CartItem i : cartItems) {
            System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f Option: %s", i.getProduct().getId(),
                    i.getProduct().getCategory(), i.getProduct().getName(), i.getProduct().getPrice(),
                    i.getProductOption());
        }
    }
}