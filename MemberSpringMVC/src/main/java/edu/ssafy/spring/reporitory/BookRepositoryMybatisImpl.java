package edu.ssafy.spring.reporitory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import edu.ssafy.spring.dto.BookDto;

@Repository("BookRepositoryMybatis")
public class BookRepositoryMybatisImpl implements BookRepository{

	SqlSession session;
	
	public BookRepositoryMybatisImpl(SqlSession session) {
		super();
		this.session = session;
	}

	String ns = "edu.ssafy.spring.reporitory.BookRepository.";
	@Override
	public void writeArticle(BookDto bookDto) throws SQLException {
		session.insert(ns + "writeArticle", bookDto);
	}

	@Override
	public List<BookDto> listBook() throws SQLException {
		List<BookDto> list = session.selectList(ns + "listBook");
		return list;
	}

	@Override
	public List<BookDto> listBook(Map<String, Integer> map) throws SQLException {
		List<BookDto> list = session.selectList(ns + "listBookPage", map);
		return list;
	}

	@Override
	public int getTotalBookCount() throws SQLException {
		int cnt = session.selectOne(ns + "getTotalBookCount");
		return cnt;
	}

	@Override
	public BookDto getTitle(String isbn) throws SQLException {
		BookDto bookDto = session.selectOne(ns + "getTitle", isbn);
		return bookDto;
	}

	@Override
	public void modifyBook(BookDto bookDto) throws SQLException {
		session.update(ns + "modifyBook", bookDto);
	}

	@Override
	public void deleteBook(BookDto bookDto) throws SQLException {
		session.delete(ns + "deleteBook", bookDto);
		
	}

	@Override
	public BookDto viewBook(BookDto bookDto) throws SQLException {
		BookDto book = session.selectOne(ns + "viewBook", bookDto);
		return book;
	}
	
	

}
