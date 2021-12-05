package Nodapo.JavaCode.Service;

import java.util.List;
import java.util.Optional;

import Nodapo.JavaCode.DataStore.DataStore;
import Nodapo.JavaCode.Model.BookModel;
import Nodapo.JavaCode.Model.Genre;
import Nodapo.JavaCode.Model.ShopModel;

public class CustomerService {
	
	DataStore dataStore;
	
	public CustomerService(){
		dataStore = new DataStore();
	}

	ShopService shopService = new ShopService();
	
	
	//Purchase a particular book from the store
	public Optional<BookModel> purchaseBook(String shopName, String bookName, int price) {	
		Optional<BookModel> optBook = shopService.sellBook(shopName, bookName, price);
		return optBook;
		
	}
	
	/**
	 * Returns all the books by Adventure Genre
	 * @param shop Shop Object
	 */
	public Optional<List<BookModel>> getBooksByGenre(String shopName) {
		
		//Getting all the shops from the data store
		List<ShopModel> shops = dataStore.getShops();
		List<BookModel> adventureBooks = null;
		
		//filtering the instance of shop which matches the shopName
		Optional<ShopModel> shop = shops.stream().filter(x->x.getName().equals(shopName)).findFirst();
		
		//If shop is present return the books with Genre adventure
		if(shop.isPresent()) {
			adventureBooks = shopService.getBookByGenre(shop.get(), Genre.Adventure);
			return Optional.of(adventureBooks);
		}
		
		//If shop is not present then an empty response is returned
		return Optional.empty();
	}
	
	/**
	 * 
	 * @param shop1 name
	 * @param shop2 name
	 * @param book
	 * @return true if both shops have same book, false otherwise
	 */
	public boolean compareBooksByStore(String shopName1, String shopName2, String book) {
		Optional<BookModel> book1 ;
		Optional<BookModel> book2 ;
		
		//Getting all the shops from the data store
		List<ShopModel> shops = dataStore.getShops();
 
		//Filtering the instances of shop whose name is same as the name supplied provided by the user
		Optional<ShopModel> shop1 = shops.stream().filter(x->x.getName().equals(shopName1)).findFirst();
		Optional<ShopModel> shop2 = shops.stream().filter(x->x.getName().equals(shopName2)).findFirst();

		//if shop1 is present get the book instance from shop1 whose name matches the name supplied by the user
		if(shop1.isPresent()) {
			book1 = shop1.get().getBooks().stream().filter(x->x.getTitel().equals(book)).findFirst();
		}
		else{
			book1 = Optional.empty();
			return false;
		}
		
		//if shop2 is present get the book instance from shop2 whose name matches the name supplied by the user
		if(shop2.isPresent()) {
			book2= shop2.get().getBooks().stream().filter(x->x.getTitel().equals(book)).findFirst();
						
		}
		else {
			book2 = Optional.empty();
			return false;
		}
		
		//Check if both books from shop1 and shop2 are present and both the instances are equal
		if(book1.isPresent() && book2.isPresent() && book1.get().equals(book2.get())){
			return true;
		}
		else
			return false;
	}
}
