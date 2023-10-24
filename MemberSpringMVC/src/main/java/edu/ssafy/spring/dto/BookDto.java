package edu.ssafy.spring.dto;

public class BookDto {

	private String isbn;
	private String author;
	private String title;
	private String price;
	public BookDto(String isbn, String author, String title, String price) {
		super();
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.price = price;
	}
	
	public BookDto() {
		super();
	}

	@Override
	public String toString() {
		return "BookDto [isbn=" + isbn + ", author=" + author + ", title=" + title + ", price=" + price + "]";
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
