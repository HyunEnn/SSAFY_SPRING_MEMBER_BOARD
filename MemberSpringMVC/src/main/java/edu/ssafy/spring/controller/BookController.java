package edu.ssafy.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.board.controller.BoardController;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@GetMapping("/list")
	public String list() {
		return "book/list";
	}
	
	@GetMapping("/write")
	public String writeBook() {
		return "book/write";
	}
	
	
}
