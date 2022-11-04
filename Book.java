/* 
  Name: Muhammad Sufyan Ghani
  Student Number: 501093666
*/

/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product
{
  private String author;
  private String title;
  private int year; //Added year variable for books
  
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  public Book(String id, String name, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS 
     super(id, name, price, Integer.MAX_VALUE, Category.BOOKS);
     this.paperbackStock = paperbackStock;
     this.hardcoverStock = hardcoverStock;
     this.title = title;
     this.author = author;
     this.year = year;
  }

  //These are two getter methods I created to help me for the bonus 
  public int getYear(){
    return year;
  }  
  public String getAuthor(){
    return author;
  }

  // Checking if the user entered a valid format
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
  	if (productOptions.equalsIgnoreCase("Paperback")){
      return true;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")){ 
      return true;
    }
    else if (productOptions.equalsIgnoreCase("Ebook")){
      return true;
    }
    else{
      return false;
    }
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
  	if (productOptions.equalsIgnoreCase("Paperback")){
      return paperbackStock;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")){
      return hardcoverStock;
    }
    else if (productOptions.equalsIgnoreCase("Ebook")){
      return super.getStockCount(productOptions);
    }
    return 0;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
    if (productOptions.equalsIgnoreCase("Paperback")){
      paperbackStock = stockCount;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")){
      hardcoverStock = stockCount;
    }
    else if (productOptions.equalsIgnoreCase("Ebook")){
      super.setStockCount(stockCount, productOptions);
    }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
     if (productOptions.equalsIgnoreCase("Paperback")){
      paperbackStock --;
     }
     else if (productOptions.equalsIgnoreCase("Hardcover")){
      hardcoverStock --;
     }
     else if (productOptions.equalsIgnoreCase("Ebook")){
      super.reduceStockCount(productOptions);
     }
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video

    System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f Book Title: %1s Author: %s Year: %s", super.getId(), super.getCategory(), super.getName(), super.getPrice(), title, author, year);
  }
  
}
