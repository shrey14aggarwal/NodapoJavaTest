package Nodapo.JavaCode.Model;

import java.util.List;

public class CustomerModel {

	private String name;
	private List<BookModel> books;
	private int money;
	
	public CustomerModel() {}
	
	public CustomerModel(String name, List<BookModel> books, int money) {
		super();
		this.name = name;
		this.books = books;
		this.money = money;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BookModel> getBooks() {
		return books;
	}
	public void setBooks(List<BookModel> books) {
		this.books = books;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "CustomerModel [name=" + name + ", books=" + books + ", money=" + money + "]";
	}
	
	
}
