package edu.ssafy.spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.dto.MemberDto;

@Repository
public class BookRepositoryImpl implements BookRepository {

	private DataSource source; // db 연결
	
	public BookRepositoryImpl(DataSource source) {
		super();
		this.source = source;
	}

	@Override
	public void writeArticle(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into book(isbn, author, title, price) ");
		sb.append(" values(?,?,?,?) ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getIsbn());
		stmt.setString(2, bookDto.getAuthor());
		stmt.setString(3, bookDto.getTitle());
		stmt.setString(4, bookDto.getPrice());
		stmt.executeUpdate();
		conn.close();
	}

	@Override
	public List<BookDto> listBook() throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" select isbn, author, title, price ");
		sb.append(" from members ");
		
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		ResultSet rs = stmt.executeQuery();
		List<BookDto> list = new ArrayList();
		while(rs.next()) {
			list.add(new BookDto(rs.getString("isbn"), rs.getString("author"), rs.getString("title"), rs.getString("price")));
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
		sb.append(" select isbn, author, title, price");
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
		}
		conn.close();
		return mem;
	}

	@Override
	public void modifyBook(BookDto bookDto) throws SQLException {
		Connection conn = source.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" update book set isbn = ?, author = ?, title = ?, price = ? ");
		sb.append(" where isbn = ?  ");
		PreparedStatement stmt = conn.prepareStatement(sb.toString());
		stmt.setString(1, bookDto.getIsbn());
		stmt.setString(2, bookDto.getAuthor());
		stmt.setString(3, bookDto.getTitle());
		stmt.setString(4, bookDto.getPrice());
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

}
