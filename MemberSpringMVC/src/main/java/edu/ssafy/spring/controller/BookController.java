package edu.ssafy.spring.controller;

import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.dto.FileDto;
import edu.ssafy.spring.service.BookService;
import edu.ssafy.spring.service.BookServiceImpl;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.PaggingUtil;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private ServletContext servletContext;
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@GetMapping
	public String index() {
		return "book/bookmanage";
	}
	
	@GetMapping("/insert")
	public String BookInsert() {
		return "book/bookinsert";
	}
	
	@PostMapping("/insert")
	public String InsertBook(@RequestParam("upfile") MultipartFile upfile, BookDto bookDto) throws IllegalStateException, IOException, SQLException {
		String path = servletContext.getContextPath();
		String realPath= "c:/upload";
		String saveFolder = realPath + File.separator;

		File folder = new File(saveFolder);

		if(!folder.exists()) {
			folder.mkdirs();
		}

		String originalFilename = upfile.getOriginalFilename();
		
		upfile.transferTo(new File(folder, originalFilename));
		
		bookDto.setImg(path + "/image/" + originalFilename);
		bookService.writeArticle(bookDto);
		return "redirect:/book/list";
	}
	

	@GetMapping("/list")
	public ModelAndView BookList(String pg, String spp, ModelAndView mav) {
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		currentPage = currentPage == 0 ? 1 : currentPage;
		int sizePerPage = spp == null ? PaggingUtil.sizePerPage : Integer.parseInt(spp);
		try {
			Map<String,Integer>map = new HashMap();
			map.put("currentPage", (currentPage-1)*sizePerPage);
			map.put("sizePerPage", sizePerPage);
			List<BookDto> list = bookService.listBook(map);
			PageNavigation pageNavigation = bookService.makePageNavigation(currentPage, sizePerPage);
			mav.addObject("navigation", pageNavigation);
			mav.addObject("booklist",list);
			mav.setViewName("book/booklist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	
	@GetMapping("/view")
	public String BookView(@RequestParam String isbn, Model model) throws SQLException {
		BookDto findBook = bookService.getTitle(isbn);
		System.out.println(findBook);
		model.addAttribute("book", findBook);
		return "book/viewbook";
	}
	
	@PostMapping("/update")
	public String BookUpdate(@ModelAttribute("book") BookDto bookDto) throws SQLException {
		bookService.modifyBook(bookDto);
		return "redirect:/book/list";
	}
	
	@PostMapping("/delete")
	public String BookDelete(BookDto bookDto) throws SQLException {
		bookService.deleteBook(bookDto);
		return "redirect:/book/list";
	}
	
	@PostMapping("/deletes")
	public String BookDeletes(String[] isbn) throws Exception {
		try {
			bookService.bookDeletes(isbn);
			return "redirect:/book/list";			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	
}
