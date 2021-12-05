package Nodapo.JavaCode.DataStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Nodapo.JavaCode.Model.BookModel;
import Nodapo.JavaCode.Model.CustomerModel;
import Nodapo.JavaCode.Model.Genre;
import Nodapo.JavaCode.Model.ShopModel;

public class DataStore {

 //Initialize all objects  
	   BookModel book1;
	   BookModel book2;
	   BookModel book3;
	   BookModel book4;
	   
	   List<BookModel> list1;
	   List<BookModel> list2;
	   List<BookModel> list3;
	   List<BookModel> list4;
	   
	   ShopModel shop1;
	   ShopModel shop2;
	   ShopModel shop3;
	   ShopModel shop4;
	   
	   CustomerModel customer1;
	   
	   public DataStore(){
		    book1 = new BookModel("Book1", 100, 250, Genre.Comic, 10, "978-0716703440");
			book2 = new BookModel("Book2", 50, 100, Genre.Fantasy,10, "978-0716703440");
			book3 = new BookModel("Book3", 4, 200, Genre.Adventure,25, "978-0716703440");
			book4 = new BookModel("Book4", 50, 10, Genre.Fantasy,10, "978-0716703440");
		    
			list1 = new ArrayList<>();
			list1.add(book1);
			list1.add(book3);
			list1.add(book4);
			
			list2 = new ArrayList<>();
			list2.add(book2);
			list2.add(book3);
			list2.add(book4);
			list2.add(book4);
		
			
			list3 = new ArrayList<>();
			list3.add(book1);
			list3.add(book4);
			
			list4 = new ArrayList<>();
			list4.add(book1);
			
			
			//Initializing Shop objects - Name, Books, Sales
			shop1 = new ShopModel("Shop1", list1, 0);
			shop2 = new ShopModel("Shop2", list2, 0);
			shop3 = new ShopModel("Shop3", list3, 0);
			shop4 = new ShopModel("Shop4", list4, 0);
			
			//Initializing Customers
			customer1 = new CustomerModel("Cust1", list2, 200);
	   }
	   
	   public List<BookModel> getBooks(){
		   return Arrays.asList(book1, book2, book3, book4);
	   }
	   
	   public List<ShopModel> getShops(){
		   return Arrays.asList(shop1, shop2, shop3, shop4);
	   }
	   
	   public List<CustomerModel> getCustomers(){
		   return Arrays.asList(customer1);
	   }
	   
	   
}