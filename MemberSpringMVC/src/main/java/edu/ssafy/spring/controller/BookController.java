package edu.ssafy.spring.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ssafy.spring.dto.BookDto;
import edu.ssafy.spring.dto.FileDto;
import edu.ssafy.spring.service.BookService;
import edu.ssafy.spring.service.BookServiceImpl;

@Controller
@RequestMapping("/book")
public class BookController {
	
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	private ServletContext servletContext;
	private BookServiceImpl bookService;
	
	public BookController(BookServiceImpl bookServiceImpl) {
		super();
		bookService = bookServiceImpl;
	}

	@GetMapping("/list")
	public String list(@RequestParam Map<String, Integer> map, Model model) throws SQLException {
		List<BookDto> list = bookService.listBook(map);
//		PageNavigation pageNavigation = bookService
		model.addAttribute("books", list);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "book/list";
	}
	
	@GetMapping("/write")
	public String writeBook(@RequestParam Map<String, String> map, Model model) {
		logger.debug("what is : ?" , map);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "book/write";
	}
	
	@PostMapping("/write")
	// get으로 돌려받을 필요 없고, write.jsp에서 지정해준 값들을 bookDto에서 그대로 받은 후, service단으로 전송
	public String write(BookDto bookDto, @RequestParam("upfile") MultipartFile[] files,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, SQLException {
		
//		FileUpload 관련 설정.
		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		if (!files[0].isEmpty()) {
			String realPath = servletContext.getRealPath("/upload");
//			String realPath = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = realPath + File.separator + today; // 어디로 파일이 저장될지 지정
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs(); // 폴더 생성
			List<FileDto> fileInfos = new ArrayList<FileDto>();
			for (MultipartFile mfile : files) {
				FileDto fileInfoDto = new FileDto(); 
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) { // 원래 이름
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setFid(today);
					fileInfoDto.setName(originalFileName);
					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			bookDto.setFileInfos(fileInfos);
		}
		bookService.writeArticle(bookDto);
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		return "redirect:/book/list";
	}
	
	
}
