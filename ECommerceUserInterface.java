import java.util.Scanner;

/* 
	Name: Muhammad Sufyan Ghani
	Student Number: 501093666
*/

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try{
				String action = scanner.nextLine();
			
				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
	
				else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
				{
					amazon.printAllProducts(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
				{
					amazon.printAllBooks(); 
				}
				else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
				{
					amazon.printCustomers();	
				}
				else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();	
				}
				else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
				{
					amazon.printAllShippedOrders();	
				}
				else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
				{
					String name = "";
					String address = "";
					
					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();
					
					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();
					
					amazon.createCustomer(name, address);
				}
				else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
				{	
					
					String orderNumber = "";
	
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine()){
						orderNumber = scanner.nextLine();
					}
					amazon.shipOrder(orderNumber).print();;
				
				}
				else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
				{
					String customerId = "";

					System.out.print("Customer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}

					amazon.printOrderHistory(customerId);
				}
				else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";
	
					System.out.print("Product Id: ");
						// Get product Id from scanner
						if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
					
					System.out.print("\nCustomer Id: ");
						// Get customer Id from scanner
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
					System.out.println(amazon.orderProduct(productId, customerId, ""));
														
				}
				else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
				
					String productId = "";
					String customerId = "";
					String options = "";
	
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
	
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
	
					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					// get book option 
					if (scanner.hasNextLine()){
						options = scanner.nextLine();
					}
					amazon.orderProduct(productId, customerId, options);
										
				}
				else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
				{
					String productId = "";
					String customerId = "";
					String options = "";
					
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
	
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
	
					System.out.print("\nColor: \"Black\" \"Brown\": ");
					// get shoe color and append to options
					if (scanner.hasNextLine()){
						options += scanner.nextLine();
					}
	
					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and store in options	
					if (scanner.hasNextLine()){
						options += scanner.nextLine();
					}
	
					amazon.orderProduct(productId, customerId, options);

				}
				else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					// get order number from scanner
					if (scanner.hasNextLine()){
						orderNumber = scanner.nextLine();
					}

					amazon.cancelOrder(orderNumber);
				}
				else if (action.equalsIgnoreCase("PRINTBYPRICE")) // printing products by price in ascending order
				{
					amazon.printByPrice();
				}
				else if (action.equalsIgnoreCase("PRINTBYNAME")) // printing products by name (alphabetic)
				{
					amazon.printByName();
				}
				else if (action.equalsIgnoreCase("SORTCUSTS")) // sorting customers by name (alphabetic)
				{
					amazon.sortCustomersByName();
				}
				else if (action.equalsIgnoreCase("BooksByAuthor")) // Listing all books by increasing year, given an author from user
				{
					String author = "";

					System.out.print("Author Name: ");
					// get author from user
					if (scanner.hasNextLine()){
						author = scanner.nextLine();
					}
					amazon.BooksByAuthor(author);
				
				}
				else if (action.equalsIgnoreCase("ADDTOCART")) // Adding a product to the customer's cart
				{
					String productId = "";
					String customerId = "";
					String options = "";
					String choice = "";
	
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
	
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
					
					System.out.print("\nWhat product are you ordering: \n1. Books \n2. Shoes \n3. Other \n =>");
					if (scanner.hasNextLine()){
						choice = scanner.nextLine();
					}
					amazon.cartOptions(choice);
					
					// getting product option 
					if (scanner.hasNextLine()){
						options = scanner.nextLine();
					}
	
					System.out.println(amazon.addToCart(productId, customerId, options));	
				}
				else if (action.equalsIgnoreCase("REMCARTITEM")) // Removing a product from the customer's cart
				{
					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					// Get product Id from scanner
					if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
					
					System.out.print("\nCustomer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
					System.out.println(amazon.remFromCart(productId, customerId));
				}
				else if (action.equalsIgnoreCase("PRINTCART")) // Printing all products in the cart
				{
					String customerId = "";
					
					System.out.print("\nCustomer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
					amazon.printCart(customerId);
				}
				else if (action.equalsIgnoreCase("ORDERITEMS")) // Ordering all products in the cart
				{
					String customerId = "";
					
					System.out.print("\nCustomer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						customerId = scanner.nextLine();
					}
					System.out.println(amazon.orderItems(customerId));
				}
				else if (action.equalsIgnoreCase("STATS")) // Printing stats based on the number of times a product was ordered 
				{
					amazon.printStats();
				}
				else if (action.equalsIgnoreCase("RATE")) // Letting the customer rate a product
				{
					String productId = "";
					int rating = 0;

					System.out.print("\nProduct Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						productId = scanner.nextLine();
					}
					System.out.print("\nRating (1-5): ");
					// Get customer Id from scanner
					if (scanner.hasNextLine()){
						rating = scanner.nextInt();
					}
					System.out.println(amazon.rateProduct(productId, rating));
					
				}
				else if (action.equalsIgnoreCase("PRINTRATINGS")) // Printing out the ratings for all the products
				{
					amazon.printRatings();
				}
				//System.out.print("\n>");
			}
			// Catching all the possible Runtime exceptions
			catch (UnknownCustomerException e) {
				System.out.println(e.getMessage());
			}
			catch (UnknownProductException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidProductException e) {
				System.out.println(e.getMessage());
			}
			catch (ProductStockException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidCustomerException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidAddressException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidOrderException e) {
				System.out.println(e.getMessage());
			}
			catch (UnknownAuthorException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidRatingException e) {
				System.out.println(e.getMessage());
			}
			catch (InvalidChoiceException e) {
				System.out.println(e.getMessage());
			}
			System.out.print("\n>");
		}
	}
}
