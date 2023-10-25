package edu.ssafy.spring.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.reporitory.BookRepository;
import edu.ssafy.spring.util.PageNavigation;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public void writeArticle(BookDto bookDto) throws SQLException {
		// TODO Auto-generated method stub
		bookRepository.writeArticle(bookDto);
	}

	@Override
	public List<BookDto> listBook() throws SQLException {
		return bookRepository.listBook();
	}

	@Override
	public int getTotalBookCount() throws SQLException {
		return bookRepository.getTotalBookCount();
	}

	@Override
	public BookDto getTitle(String isbn) throws SQLException {
		return bookRepository.getTitle(isbn);
	}

	@Override
	public void modifyBook(BookDto bookDto) throws SQLException {
		bookRepository.modifyBook(bookDto);
	}

	@Override
	public void deleteBook(BookDto bookDto) throws SQLException {
		bookRepository.deleteBook(bookDto);
	}
	
	@Override
	public List<BookDto> listBook(Map<String, Integer> map) throws SQLException {
		// TODO Auto-generated method stub
		return bookRepository.listBook(map);
	}
	
	@Override
	public BookDto viewBook(BookDto bookDto) throws SQLException {
		BookDto viewBook = bookRepository.viewBook(bookDto);
		return viewBook;
	}
	
	@Override
	public boolean bookDeletes(String[] id) throws Exception {
		
		return false;
	}
	
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
