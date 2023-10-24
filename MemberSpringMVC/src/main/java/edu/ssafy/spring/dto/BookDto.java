package edu.ssafy.spring.dto;

import java.util.List;


public class BookDto {

	private String isbn;
	private String author;
	private String title;
	private String price;
	private List<FileDto> fileInfos;
	
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

	public List<FileDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

}
