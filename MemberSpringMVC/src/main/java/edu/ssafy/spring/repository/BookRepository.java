package edu.ssafy.spring.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.ssafy.spring.dto.BookDto;

public interface BookRepository {

	void writeArticle(BookDto bookDto) throws SQLException; // create
	List<BookDto> listBook() throws SQLException; // book All-select
	int getTotalBookCount() throws SQLException; // total-book-count 
	BookDto getTitle(String isbn) throws SQLException; // id를 통한 책 이름 조회
	
	void modifyBook(BookDto bookDto) throws SQLException; // 책 수정
	void deleteBook(BookDto bookDto) throws SQLException; // 책 삭제
}