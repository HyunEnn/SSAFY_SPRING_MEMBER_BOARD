package edu.ssafy.spring.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.service.BookService;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.PaggingUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/restbook")
public class RestBookController {
	
	private BookService service;

	public RestBookController(BookService service) {
		super();
		this.service = service;
	}

	@GetMapping("/insert")
	public String BookInsert() {
		return "book/bookinsert";
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Map<String, Object>> InsertBook(@RequestBody BookDto bookDto) throws IllegalStateException, IOException, SQLException {
		Map<String, Object> map = new HashMap<>();
		try {
			service.writeArticle(bookDto);
			map.put("resdata", bookDto);
			map.put("resmsg", "입력 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "입력 실패");
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> BookList() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<BookDto> list = service.listBook();
			map.put("resdata", list);
			map.put("resmsg", "조회 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회 실패");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	};
	
	@GetMapping("/view/{isbn}")
	public ResponseEntity<Map<String, Object>> BookView(@PathVariable String isbn) throws SQLException {
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		Map<String, Object> map = new HashMap<>();
		try {
			BookDto viewBook = service.viewBook(bookDto);
			map.put("resdata", viewBook);
			map.put("resmsg", "조회 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회 실패");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
//	
	@PutMapping("/update/{isbn}")
	public ResponseEntity<Map<String, Object>> BookUpdate(@PathVariable String isbn, @RequestBody BookDto bookDto) throws SQLException {
		BookDto findBook = service.getTitle(isbn);
		bookDto.setIsbn(findBook.getIsbn()); 
		Map<String, Object> map = new HashMap<>();
		try {
			service.modifyBook(bookDto);
			map.put("resdata", bookDto);
			map.put("resmsg", "업데이트 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "업데이트 실패");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<Map<String, Object>> BookDelete(@PathVariable String isbn) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		BookDto bookDto = new BookDto();
		bookDto.setIsbn(isbn);
		try {
			service.deleteBook(bookDto);
			map.put("resmsg", "삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "삭제 실패");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletes/{isbns}")
	public ResponseEntity<Map<String, Object>> BookDeletes(@PathVariable String isbns) throws Exception {
		Map<String, Object> map = new HashMap<>();
		String[] isbn = isbns.split(",");
		try {
			service.bookDeletes(isbn);
			map.put("resmsg", "삭제 성공");		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("resmsg", "삭제 실패");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
