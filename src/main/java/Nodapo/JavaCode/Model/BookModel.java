package Nodapo.JavaCode.Model;

public class BookModel {

	private String titel;
	private int price;
	private int pageNo;
	private Genre genre;
	private int quantity;
	private String isbn13;
	
	BookModel(){}
	
	public BookModel(String titel, int price, int pageNo, Genre genre, int quantity, String isbn13) {
		super();
		this.titel = titel;
		this.price = price;
		this.pageNo = pageNo;
		this.genre = genre;
		this.quantity = quantity;
		this.isbn13 = isbn13;
	}

	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getisbn13() {
		return isbn13;
	}
	
	public void setisbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	public boolean equals(Object obj) {
		BookModel book = (BookModel) obj;
		 if(this.titel.equals(book.getTitel()) && 
				this.price == book.getPrice() &&
				this.pageNo == book.getPageNo() &&
				this.genre.equals(book.getGenre()) &&
				this.quantity == book.getQuantity() &&
				this.isbn13.equals(book.getisbn13())){
			return true;
		}
		else
			return false;
	}

	 public int hashCode() {	        
	        return (this.titel.hashCode() + this.price + this.getPageNo() + this.genre.hashCode() + this.quantity + this.isbn13.hashCode());        
	    }

	@Override
	public String toString() {
		return "BookModel [titel=" + titel + ", price=" + price + ", pageNo=" + pageNo + ", genre=" + genre
				+ ", quantity=" + quantity + ", isbn13=" + isbn13 + "]";
	}
	
	 
	
}
