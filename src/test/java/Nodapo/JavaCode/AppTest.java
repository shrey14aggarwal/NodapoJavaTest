package Nodapo.JavaCode;

import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import Nodapo.JavaCode.DataStore.DataStore;
import Nodapo.JavaCode.Model.BookModel;
import Nodapo.JavaCode.Model.Genre;
import Nodapo.JavaCode.Service.CustomerService;
import Nodapo.JavaCode.Service.ShopService;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   ShopService shopService;
   CustomerService customerService;
   
 
    @BeforeEach
	protected
    void setUp() {   	
    	shopService = new ShopService();
    	customerService = new CustomerService();
   	
    }
    
  
    //Test to get books by Adventure Genre
    @Test
    public void testCustomerServiceGetBookByGenre() {
    	
    	//Correct book requested from shop; only Book3 has genre ADVENTURE
    	Optional<List<BookModel>> books = customerService.getBooksByGenre("Shop1"); 	
    	assertEquals("Book3", books.get().get(0).getTitel());
    	
    	//Book2 does not have Genre ADVENTURE
    	assertNotEquals("Book2",books.get().get(0).getTitel());
    	
    	//Invalid shop name given; no books returned
    	Optional<List<BookModel>> newBooks = customerService.getBooksByGenre("InvalidShop"); 	
    	assertFalse(newBooks.isPresent());	
    }
    
    //Test to check if 2 stores have the specified same book
    @Test
    public void testCustomerServiceCompareBooksByStore() {
    
    	//Book present in both stores
    	assertEquals(true,customerService.compareBooksByStore("Shop1", "Shop2", "Book4"));
    	
    	//Book present in store 1 but not in store2
    	assertFalse(customerService.compareBooksByStore("Shop1", "Shop2", "Book1"));
    	
    	//Book not present in store 1 but present in store 2
    	assertFalse(customerService.compareBooksByStore("Shop1", "Shop2", "Book2"));
    	
    	//Invalid book name given; Book not present with both store
    	assertFalse(customerService.compareBooksByStore("Shop1", "Shop2", "InvalidBook"));
    	
    	//Invalid shop name given
    	assertFalse(customerService.compareBooksByStore("InvalidShop1", "InvalidShop2", "Book2"));
    	
    }
    
    //Test to check the method purchase book
    @Test
    public void testCustomerServicePurchaseBook() {
    	
    	//Book present in store and correct price paid
    	Optional<BookModel> newBook = customerService.purchaseBook("Shop1", "Book4", 51);
    	assertEquals("Book4",newBook.get().getTitel());
    	
    	//Book present but less price paid
    	Optional<BookModel> newBook1 = customerService.purchaseBook("Shop1", "Book4", 49);
    	assertEquals(false,newBook1.isPresent());
    	
    	//book not present in the shop
    	Optional<BookModel> newBook2 = customerService.purchaseBook("Shop1", "Book2", 51);
    	assertEquals(false,newBook2.isPresent());
    	
    	//Incorrect shop name provided
    	Optional<BookModel> newBook3 =  customerService.purchaseBook("InvalidShop", "Book4", 51);
    	assertEquals(false, newBook3.isPresent());
    	
    }
    
    //Test to check the sell book method
    @Test
    public void testShopServiceSellBook() {
    	
    	//Book present in the shop and correct price paid
    	Optional<BookModel> newBook = shopService.sellBook("Shop1", "Book4", 51);
    	assertEquals("Book4",newBook.get().getTitel());
    	
    	//Book not present in the shop
    	Optional<BookModel> newBook1 = shopService.sellBook("Shop1", "Book5", 51);
    	assertEquals(false, newBook1.isPresent());
    	
    	//Book present in the shop and incorrect price paid
    	Optional<BookModel> newBook2 = shopService.sellBook("Shop1", "Book4", 49);
    	assertEquals(false, newBook2.isPresent());
    }
    
    //Test to check the method which adds books to the store
    @Test
    public void testShopServiceAddBooks() {
    	 DataStore dataStore = new DataStore();

    	//Getting books of shop4
    	Optional<List<BookModel>> bookList = dataStore.getShops().stream().filter(x->x.getName().equals("Shop4")).map(y->y.getBooks()).findFirst();
    
    	//Initially shop4 has only 1 book - Book1
    	assertEquals(1, bookList.get().size());
        
    	//Adding a book with valid isbn to shop4 (isbn taken from the problem statement provided)
    	shopService.addBooks("Shop4", new BookModel("Book6", 10, 10, Genre.Biography, 10, "978-3608963762"));
        assertEquals(2,shopService.shop.get().getBooks().size());
        
        //Adding a book with valid isbn to shop4 
        shopService.addBooks("Shop4", new BookModel("Book7", 20, 15, Genre.Fantasy, 100, "978-3608963762"));
        assertEquals(3,shopService.shop.get().getBooks().size());
        
        //Adding a book with invalid isbn to shop4 (isbn taken from the problem statement provided)
        shopService.addBooks("Shop4", new BookModel("Book7", 20, 15, Genre.Fantasy, 100, "978-3442267747"));
        assertNotEquals(4,shopService.shop.get().getBooks().size());
        
        //Adding a book to inavlid shop with valid isbn
        shopService.addBooks("InvalidShop", new BookModel("Book7", 20, 15, Genre.Fantasy, 100, "978-0716703440"));
        assertEquals(false,shopService.shop.isPresent());
        
        //These two test case test if only 1 instance of a book is present
        shopService.addBooks("Shop4", new BookModel("Book7", 20, 15, Genre.Fantasy, 100, "978-3608963762"));
        assertEquals(3,shopService.shop.get().getBooks().size());
        
        //Again adding the same book to the same shop
        shopService.addBooks("Shop4", new BookModel("Book7", 20, 15, Genre.Fantasy, 100, "978-3608963762"));
        assertEquals(3,shopService.shop.get().getBooks().size());
        
        //3 instances of Book4 are added to Shop4 each with quantity 100, check if total quantity of Book4 is 300
        assertEquals(Optional.of(300),shopService.shop.get().getBooks().stream().filter(x->x.getTitel().equals("Book7")).map(x->x.getQuantity()).findFirst());

    }
    
    //Test to check sales of a shop and quantity of books after purchasing a book
    @Test
    public void testShopServiceSellBookQuantityandSales() {
    	
    	//Requesting Book1 from Shop4; Price of book1 is 100
    	shopService.sellBook("Shop4", "Book1", 100);
    	assertEquals(shopService.bookModel.get().getPrice(),shopService.shopModel.get().getSales());
    	
    	//Requesting the same book from same shop; expecting sale to be 200
    	shopService.sellBook("Shop4", "Book1", 100);
    	assertEquals(shopService.bookModel.get().getPrice()*2,shopService.shopModel.get().getSales());
    	
    	//Test the quantity of books after purchasing books from store
    	shopService.sellBook("Shop4", "Book1", 100);
    	//Quantity 7 because already purchased 3 times
    	assertEquals(Optional.of(7),shopService.shopModel.get().getBooks().stream().map(x->x.getQuantity()).findFirst());
    }
    
    
}
