import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/* 
  Name: Muhammad Sufyan Ghani
  Student Number: 501093666
*/

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {

  // private ArrayList<Product> products = new ArrayList<Product>();
  
  // Converted the product ArrayList to a TreeMap  
  private TreeMap<String, Product> products = new TreeMap<String, Product>();
  // Map that keeps track of the product statistics
  private TreeMap<String, Integer> statistics = new TreeMap<String, Integer>();
  
  // These are maps used for the rating system
  private TreeMap<String, Integer> ratings = new TreeMap<String, Integer>();
  private TreeMap<String, Integer> totalRatings = new TreeMap<String, Integer>();
  private TreeMap<String, Double> productRatings = new TreeMap<String, Double>();

  private ArrayList<Customer> customers = new ArrayList<Customer>();

  private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
  private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

  // Created an extra arraylist storing books to help with A1 bonus
  private ArrayList<Book> books = new ArrayList<Book>();

  // These variables are used to generate order numbers, customer id's, and product id's
  private int orderNumber = 500;
  private int customerId = 900;
  private int productId = 700;

  // Random number generator
  Random random = new Random();

  public ECommerceSystem() {
    
    // Calling readProducts method to retrieve all the products from the txt file 
    readProducts();
    customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
    customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
    customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
    customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    
    // Calling this methods to initialize the stats and rating systems
    statsInit();
    ratingsInit();
    totalRatingsInit();
    productRatings();
  }

  private void readProducts() {
    // Temporary product ArrayList
    ArrayList<Product> prod = new ArrayList<Product>();

    try {
      Scanner in = new Scanner(new File("products.txt"));

      in.useDelimiter(":");

      while (in.hasNext()) {

        String line = in.nextLine().trim();

        // Creating a Book product
        if (line.equals("BOOKS")) {

          String category = line.toUpperCase();

          String name = in.nextLine();

          double price = Double.valueOf(in.nextLine());

          String stock = in.nextLine().trim();

          int paperBackStock = Integer.valueOf(stock.substring(0, 1));

          int hardCoverStock = Integer.valueOf(stock.substring(stock.length() - 1));

          String data = in.nextLine();
          String[] info = data.split(":", 0);

          String title = info[0];
          String author = info[1];
          int year = Integer.valueOf(info[2]);

          Product newProduct = new Book(generateProductId(), name, price, paperBackStock, hardCoverStock, title, author,
              year);

          prod.add(newProduct);

        }
        // Creating a general product 
        else {
          String category = line.toUpperCase();

          String name = in.nextLine();

          double price = Double.valueOf(in.nextLine());

          int stock = Integer.valueOf(in.nextLine());

          in.nextLine();

          Product newProduct = new Product(generateProductId(), name, price, stock, Product.Category.valueOf(category));

          prod.add(newProduct);
        }
      }
    }
    // Catching exception in case file in not found
    catch (IOException e) {
      System.out.println("\n\nFile not found");
      System.exit(1);
    }
    // Adding the products read from the file to a map 
    for (Product p : prod) {
      products.put(p.getId(), p);
    }
  }

  // Method to generate order number
  private String generateOrderNumber() {
    return "" + orderNumber++;
  }

  // Method to generate customer ID
  private String generateCustomerId() {
    return "" + customerId++;
  }

  // Method to generate product ID
  private String generateProductId() {
    return "" + productId++;
  }

  // Prints all the products
  public void printAllProducts() {
    for (String p : products.keySet())
      products.get(p).print();
  }

  // Print all products that are books. See getCategory() method in class Product
  public void printAllBooks() {
    for (String p : products.keySet()) {
      if (products.get(p).getCategory() == Product.Category.BOOKS) {
        products.get(p).print();
      }
    }
  }

  // Print all current orders
  public void printAllOrders() {
    for (ProductOrder o : orders) {
      o.print();
    }
  }

  // Print all shipped orders
  public void printAllShippedOrders() {
    for (ProductOrder s : shippedOrders) {
      s.print();
    }
  }

  // Print all customers
  public void printCustomers() {
    for (int i = 0; i < customers.size(); i++) {
      customers.get(i).print();
    }
  }

  // Helper method for the statistics system
  public void statsInit() {
    for (String s : products.keySet()) {
      statistics.put(s, 0);
    }
  }

  // Method to print all the products statistics
  public void printStats() {

    // Converting the statistics map to a LinkedList to be able to sort by value
    List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(statistics.entrySet());

    // Sorting the list based on the values in descending order
    Collections.sort(list, new Comparator<Entry<String, Integer>>()
    {
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2)
        {
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    
    // Printing the statistics from the list
    for (Entry<String, Integer> s : list) {
      System.out.printf("\nName: %-20s ID: %-5s Number of Orders: %s", products.get(s.getKey()).getName(),
          products.get(s.getKey()).getId(), s.getValue());
    }
  }

  // Helper method for ratings system
  public void ratingsInit(){
    // This map keeps track the ratings value of the product
    for (String s : products.keySet()) {
      ratings.put(s, 0);
    }
  }

  // Second helper method for ratings system
  public void totalRatingsInit(){
    // This map keeps track the total ratings given to the product
    for (String s : products.keySet()) {
      totalRatings.put(s, 0);
    }
  }
  
  // Method to initilize the product rating map
  public void productRatings(){
    // This map keeps track the average rating for the product
    for (String s : products.keySet()) {
      productRatings.put(s, 0.0);
    }
  }

  // Method to add the product rating
  public String rateProduct(String productId, int rating){

    // Checking if the product exists
    Product product = null;
    if (!products.containsKey(productId)) {
      throw new UnknownProductException("Product " + productId + " Not Found");
    }
    product = products.get(productId);

    // Check if the product rating is valid
    if (rating < 1 || rating > 5){
      throw new InvalidRatingException("Product " + productId + " Was Rated Incorrectly");
    }
    // Adding the rating to the product
    ratings.put(productId, ratings.get(productId) + rating);
    totalRatings.put(productId, totalRatings.get(productId) + 1);
    productRatings.put(productId, (double) ratings.get(productId)/totalRatings.get(productId));

    return "Product " + productId + " was Sucessfully Rated a " + rating;
  }
  
  // Printing the product rating to the customer
  public void printRatings(){
    
    // Convert the product rating map to a LinkedList to sort by value
    List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(productRatings.entrySet());

    // Sorting the list based on values in descending order
    Collections.sort(list, new Comparator<Entry<String, Double>>()
    {
        public int compare(Entry<String, Double> o1,
                Entry<String, Double> o2)
        {
            return o2.getValue().compareTo(o1.getValue());
        }
    });

    // Printing the ratings from the list
    for (Entry<String, Double> s : list) {
      System.out.printf("\nName: %-20s ID: %-5s Average Rating: %s", products.get(s.getKey()).getName(),
          products.get(s.getKey()).getId(), s.getValue());
    }
  }

  /*
   * Given a customer id, print all the current orders and shipped orders for them (if any)
   */
  public void printOrderHistory(String customerId) throws UnknownCustomerException {
    
    // Checking if the customer exists
    boolean custExists = false;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        custExists = true;
        break;
      }
    }
    if (!custExists) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }

    // Print current orders of this customer
    System.out.println("Current Orders of Customer " + customerId);
    printAllOrders();
    // Print shipped orders of this customer
    System.out.println("\nShipped Orders of Customer " + customerId);
    printAllShippedOrders();

  }

  // Method to add an item to the user's cart
  public String addToCart(String productId, String customerId, String productOptions)
      throws UnknownCustomerException, UnknownProductException, ProductStockException {
    
    // Checking if the customer exists
    int index = customers.indexOf(new Customer(customerId));
    if (index == -1) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }
    Customer customer = customers.get(index);

    // Check if the product exists
    Product product = null;
    if (!products.containsKey(productId)) {
      throw new UnknownProductException("Product " + productId + " Not Found");
    }
    product = products.get(productId);

    // Check if the product option is valid
    if (!product.validOptions(productOptions)) {
      throw new UnknownProductException("Product " + productId + " Option Was Invalid");
    }

    // Adding the item to the cart
    customer.getCart().addCartItem(new CartItem(product, productOptions));

    return "Product " + productId + " was Sucessfully Added to Cart";

  }

  // Method to remove an item from the cart
  public String remFromCart(String productId, String customerId)
      throws UnknownCustomerException, UnknownProductException {

    // Check if the product exists
    Product product = null;
    if (!products.containsKey(productId)) {
      throw new UnknownProductException("Product " + productId + " Not Found");
    }
    product = products.get(productId);

    // Check if the customer exists
    int index = customers.indexOf(new Customer(customerId));
    if (index == -1) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }
    Customer customer = customers.get(index);

    // Removing the item from the cart    
    customer.getCart().removeCartItem(productId);

    return "Product " + productId + " was Sucessfully Removed from Cart";

  }

  // Printing the items in the customer's cart
  public void printCart(String customerId) throws UnknownCustomerException {

    //  Checking if the customer exists
    int index = customers.indexOf(new Customer(customerId));
    if (index == -1) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }
    Customer customer = customers.get(index);
    
    // Printing the customer cart items
    customer.getCart().printCartItems();

  }

  // Method to order all items in the customer cart
  public String orderItems(String customerId) throws UnknownCustomerException {

    // Checking if the customer exists
    int index = customers.indexOf(new Customer(customerId));
    if (index == -1) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }
    Customer customer = customers.get(index);
    
    // Getting the items in the cart
    ArrayList<CartItem> cartItems = customer.getCart().getCartItems();

    // Ordering the items in the cart
    for (CartItem i: cartItems) {
      orderProduct(i.getProduct().getId(), customerId, i.getProductOption());
    }
    // Removing the items from the cart
    customer.getCart().removeAllItems();

    return "Customer " + customerId + "'s Items were Succesfully Ordered";
  }

  public void cartOptions(String choice){

    String message = "";

    if (choice.equals("1")){
      message = "Format [Paperback Hardcover EBook]: ";
    }
    else if (choice.equals("2")){
      message = "Format [Black or Brown, Size 6-10 (i.e 6 Black, 10 Brown...)]: ";
    }
    else if (choice.equals("3")){
      message = "No Options Available [Leave Option Blank]: ";
    }
    else{
      throw new InvalidChoiceException(choice + " Was an Invalid Option");
    }
    System.out.println(message);
  }


  public String orderProduct(String productId, String customerId, String productOptions)
      throws UnknownCustomerException, UnknownProductException, ProductStockException {
  
    // Checking if the customer exists
    boolean custExists = false;
    Customer customer = null;
    for (Customer c : customers) {
      if (c.getId().equals(customerId)) {
        custExists = true;
        customer = c;
        break;
      }
    }
    if (!custExists) {
      throw new UnknownCustomerException("Customer " + customerId + " Not Found");
    }

    // Check to see if product exists in the map
    Product product = null;
    if (!products.containsKey(productId)) {
      throw new UnknownProductException("Product " + productId + " Not Found");
    }
    product = products.get(productId);

    // Check if the options are valid for this product
    if (!product.validOptions(productOptions)) {
      throw new UnknownProductException("Product " + productId +
          " Option Was Invalid or Wrong Command Was Used");
    }

    // Check if the product has stock available
    if (product.getStockCount(productOptions) <= 0) {
      throw new ProductStockException("Product " + productId + " Is Out of Stock");
    }

    // Create a ProductOrder
    // Reduce stock count of product by 1 
    // Add to orders list and return order number string
    String ordNum = generateOrderNumber();
    product.reduceStockCount(productOptions);
    orders.add(new ProductOrder(ordNum, product, customer, productOptions));

    // Adding the order to statistics
    if (statistics.containsKey(productId)) {
      statistics.put(productId, statistics.get(productId) + 1);
    }

    return "Order " + ordNum;
  }

  /*
   * Create a new Customer object and add it to the list of customers
   */
  public void createCustomer(String name, String address) throws UnknownCustomerException, InvalidAddressException {
    // Check name parameter to make sure it is not null or ""
    if (name == null || name == "") {
      throw new UnknownCustomerException("Invalid Customer Name");
    }
    if (address == null || address == "") {
      throw new InvalidAddressException("Invalid Customer Address");
    }
    // Create a Customer object and add to array list
    else {
      customers.add(new Customer(generateCustomerId(), name, address));
    }
  }

  public ProductOrder shipOrder(String orderNumber) throws UnknownProductException {
    // Check if order number exists first
    // Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    // return a reference to the order
    boolean ordExists = false;
    int index = 0;
    ProductOrder temp = null;
    for (ProductOrder o : orders) {
      if (o.getOrderNumber().equals(orderNumber)) {
        ordExists = true;
        temp = orders.get(index);
        break;
      }
      index++;
    }
    if (!ordExists) {
      throw new UnknownProductException("Order " + orderNumber + " Not Found");
    } else {
      shippedOrders.add(temp);
      orders.remove(index);
    }

    return temp;
  }

  /*
   * Cancel a specific order based on order number
   */
  public void cancelOrder(String orderNumber) throws InvalidOrderException {

    // Check if order number exists first. If it doesn't, set errMsg to a message
    // and return false
    boolean ordExists = false;
    int index = 0;
    for (ProductOrder o : orders) {
      if (o.getOrderNumber().equals(orderNumber)) {
        ordExists = true;
        break;
      }
      index++;
    }
    if (!ordExists) {
      throw new InvalidOrderException("Order " + orderNumber + " Not Found");
    } else {
      orders.remove(index);
    }
  }

  // Printing products by increasing price
  public void printByPrice() {
    ArrayList<Product> tempProducts = new ArrayList<Product>();

    for (String p : products.keySet()) {
      tempProducts.add(products.get(p));
    }

    Collections.sort(tempProducts, Comparator.comparing(Product::getPrice));

    for (Product p : tempProducts) {
      p.print();
    }
  }

  // Printing products alphabetically by product name
  public void printByName() {
    ArrayList<Product> tempProducts = new ArrayList<Product>();

    for (String p : products.keySet()) {
      tempProducts.add(products.get(p));
    }

    Collections.sort(tempProducts, Comparator.comparing(Product::getName));

    for (Product p : tempProducts) {
      p.print();
    }
  }

  // Sort products alphabetically by product name
  public void sortCustomersByName() {
    Collections.sort(customers);
  }

  // This is a helper method for BooksByAuthor function
  // It adds all products that are books to an array list and sorts them by year
  public void sortBooks() {
    
    for (Product p : products.values()) {
      if (p.getCategory() == Product.Category.BOOKS) {
        books.add((Book) p);
      }
    }
    Collections.sort(books, Comparator.comparing(Book::getYear));
    
  }

  // This function takes in an author from the user and prints out the books from them
  public void BooksByAuthor(String author) throws UnknownAuthorException {

    books.clear();
    // Calling the helper method
    sortBooks();
    // Checking to see if the author exists
    boolean authorExists = false;
    for (Book b : books) {
      if (b.getAuthor().equals(author)) {
        authorExists = true;
        break;
      }
    }
    // Returning an error message if the author does not exist
    if (!authorExists) {
      throw new UnknownAuthorException("The Author, " + author + ", Was Not Found");
    }
    // Printing the author's books if they exist
    else {
      for (Book b : books) {
        if (b.getAuthor().equals(author)) {
          b.print();
        }
      }
    }
  }
}

// Classes made for all the possile exceptions
class UnknownCustomerException extends RuntimeException {
  public UnknownCustomerException(String errorMessage) {
    super(errorMessage);
  }
}

class UnknownProductException extends RuntimeException {
  public UnknownProductException(String errorMessage) {
    super(errorMessage);
  }
}

class InvalidProductException extends RuntimeException {
  public InvalidProductException(String errorMessage) {
    super(errorMessage);
  }
}

class ProductStockException extends RuntimeException {
  public ProductStockException(String errorMessage) {
    super(errorMessage);
  }
}

class InvalidCustomerException extends RuntimeException {
  public InvalidCustomerException(String errorMessage) {
    super(errorMessage);
  }
}

class InvalidAddressException extends RuntimeException {
  public InvalidAddressException(String errorMessage) {
    super(errorMessage);
  }
}

class InvalidOrderException extends RuntimeException {
  public InvalidOrderException(String errorMessage) {
    super(errorMessage);
  }
}

class UnknownAuthorException extends RuntimeException {
  public UnknownAuthorException(String errorMessage) {
    super(errorMessage);
  }
}
class InvalidRatingException extends RuntimeException {
  public InvalidRatingException(String errorMessage) {
    super(errorMessage);
  }
}
class InvalidChoiceException extends RuntimeException {
  public InvalidChoiceException(String errorMessage) {
    super(errorMessage);
  }
}