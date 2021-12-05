package Nodapo.JavaCode.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Nodapo.JavaCode.Model.BookModel;
import Nodapo.JavaCode.Model.Genre;
import Nodapo.JavaCode.Model.ShopModel;

public class ShopService {

	/**
	 * 
	 * @param shopName
	 * @param bookName
	 * @param price
	 * @return Instance of book (if present), null otherwise
	 */
	public  Optional<BookModel> sellBook(ShopModel shop, BookModel book, int price) {
		for(BookModel books : shop.getBooks()) {
			if(books.getTitel().equals(book.getTitel()) && books.getPrice() < price && books.getQuantity()>0) {
				
				shop.getBooks().set(shop.getBooks().indexOf(book.getTitel()),
						new BookModel(book.getTitel(), book.getPrice(), book.getPageNo(), book.getGenre(), book.getQuantity()-1));
				int currentSales = shop.getSales();
				shop.setSales(currentSales + price);
				return Optional.of(book);
			}
		
		}
		return Optional.empty();
	}
	
	/**
	 * 
	 * @param genre
	 * @return Instance of a book filtered by Genre
	 */
	public List<BookModel> getBookByGenre(ShopModel shop, Genre genre){
		System.out.println(shop +" "+ genre);
		List<BookModel> booksByGenre = shop.getBooks()
				.stream()
				.filter(x->x.getGenre()
				.equals(genre))
				.collect(Collectors.toList());
		
		return booksByGenre;
	}
	
}
