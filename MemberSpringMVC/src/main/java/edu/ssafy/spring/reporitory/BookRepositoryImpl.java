package edu.ssafy.spring.reporitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.util.PageNavigation;

@Repository
public class BookRepositoryImpl implements BookRepository {

	private DataSource source; // db 연결
	
	@Autowired
	public BookRepositoryImpl(DataSource source) {
		this.source = source;
	}

	@Override
	public void writeArticle(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into book(isbn, author, title, price, img) ");
		sb.append(" values(?,?,?,?,?) ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getIsbn());
		stmt.setString(2, bookDto.getAuthor());
		stmt.setString(3, bookDto.getTitle());
		stmt.setString(4, bookDto.getPrice());
		stmt.setString(5, bookDto.getPrice());
		stmt.executeUpdate();
		conn.close();
	}

	@Override
	public List<BookDto> listBook() throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select isbn, author, title, price, img ");
		sb.append(" from book ");
		
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		List<BookDto> list = new ArrayList<>();
		BookDto bookDto = null;
		while(rs.next()) {
			bookDto = new BookDto();
			bookDto.setIsbn(rs.getString("isbn"));
			bookDto.setAuthor(rs.getString("author"));
			bookDto.setTitle(rs.getString("title"));
			bookDto.setPrice(rs.getString("price"));
			bookDto.setImg(rs.getString("img"));
			list.add(bookDto);
		}
		conn.close();
		return list;
	}

	@Override
	public int getTotalBookCount() throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(isbn) from book ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}

	@Override
	public BookDto getTitle(String isbn) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select isbn, author, title, price, img");
		sb.append(" from book ");
		sb.append(" where isbn = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, isbn);
		ResultSet rs = stmt.executeQuery();
		BookDto mem = null;
		if(rs.next()) {
			mem = new BookDto();
			mem.setIsbn(rs.getString("isbn"));
			mem.setAuthor(rs.getString("author"));
			mem.setTitle(rs.getString("title"));
			mem.setPrice(rs.getString("price"));
			mem.setImg(rs.getString("img"));
		}
		conn.close();
		return mem;
	}

	@Override
	public void modifyBook(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" update book set author = ?, title = ?, price = ?, img = ? ");
		sb.append(" where isbn = ?  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getAuthor());
		stmt.setString(2, bookDto.getTitle());
		stmt.setString(3, bookDto.getPrice());
		stmt.setString(4, bookDto.getImg());
		stmt.setString(5, bookDto.getIsbn());
		stmt.executeUpdate();
		conn.close();
	}

	@Override
	public void deleteBook(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from book ");
		sb.append(" where isbn = ?  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getIsbn());
		stmt.executeUpdate();
		conn.close();
	}

	@Override
	public List<BookDto> listBook(Map<String, Integer> map) throws SQLException {
		int currentPage = (int) map.get("currentPage");
		int sizePerPage = (int) map.get("sizePerPage");
		
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select isbn, author, title, price, img ");
		sb.append(" from book ");
		sb.append(" limit ?, ?" );
		
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setInt(1, currentPage);
		stmt.setInt(2, sizePerPage);
		ResultSet rs = stmt.executeQuery();
		
		List<BookDto> list = new ArrayList<>();
		BookDto bookDto = null;
		while(rs.next()) {
			bookDto = new BookDto();
			bookDto.setIsbn(rs.getString("isbn"));
			bookDto.setAuthor(rs.getString("author"));
			bookDto.setTitle(rs.getString("title"));
			bookDto.setPrice(rs.getString("price"));
			bookDto.setImg(rs.getString("img"));
			list.add(bookDto);
		}
		conn.close();
		return list;
	}
	
	@Override
	public BookDto viewBook(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from book ");
		sb.append(" where isbn = ? ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getIsbn());
		ResultSet rs = stmt.executeQuery();
		BookDto book = null;
		
		if(rs.next()) {
			book = new BookDto();
			book.setIsbn(rs.getString("isbn"));
			book.setAuthor(rs.getString("author"));
			book.setTitle(rs.getString("title"));
			book.setPrice(rs.getString("price"));
			book.setImg(rs.getString("img"));
		}
		conn.close();
		return book;
	}
}
