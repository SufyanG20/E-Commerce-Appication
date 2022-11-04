/* 
	Name: Muhammad Sufyan Ghani
	Student Number: 501093666
*/

/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer implements Comparable<Customer>
{
	private String id;  
	private String name;
	private String shippingAddress;
	private Cart cart;
	// Default constructor
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
		this.cart = new Cart(); // Added a cart object to each customer 
	}
	
	// Constructor 
	public Customer(String id, String name, String address)
	{	
		// Initializing variables
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.cart = new Cart();
	}
	
	// Various setter and getter methods needed for Customer
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
	
	// Printing the customers information 
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	// Equals method comapres two customers ID
	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

	// CompareTo method required to implement the Comparable interface
	public int compareTo(Customer o) {
		
		return this.getName().compareTo(o.getName());
	}

	// New method added to return the customers cart
    public Cart getCart(){
		return cart;
    }
}
