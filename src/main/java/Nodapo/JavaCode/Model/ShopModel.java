package Nodapo.JavaCode.Model;

import java.util.List;

import Nodapo.JavaCode.Service.ShopService;

public class ShopModel {

	private String name;
	private List<BookModel> books;
	private int sales;
		
	ShopModel(){};
	
	public ShopModel(String name, List<BookModel> books, int sales){
		
		this.name = name;
		this.books = ShopService.removeDuplicate(books);
		this.sales = sales;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<BookModel> getBooks(){
		this.books = ShopService.removeDuplicate(this.books);
		return books;
	}
	
	public void setBooks(List<BookModel> books) {
		this.books = books;
	}
	
	public int getSales() {
		return this.sales;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "ShopModel [name=" + name + ", books=" + books + ", sales=" + sales +  "]";
	}
	
}

