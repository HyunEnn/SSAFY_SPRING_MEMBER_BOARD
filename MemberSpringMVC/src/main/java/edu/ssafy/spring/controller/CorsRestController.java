package edu.ssafy.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.spring.dto.MemberDto;

@CrossOrigin("*")
//@Controller
@RestController
@RequestMapping("/cors")
public class CorsRestController {
	
	@GetMapping("test01")
	public void test01(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		System.out.println(id+","+name);
		
		res.setContentType("text/plain; charset=utf-8");
		//res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		//res.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = res.getWriter(); 
		out.write(id + "님 반갑습니다!!! . ("+name+")");
		out.close();
		
	}

	@GetMapping("test02")
	public @ResponseBody MemberDto test02(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		System.out.println(id+","+name);
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setName(name);
		dto.setAge("21");
		dto.setPw("22");
		return dto;
	}
	
	@GetMapping("/test03")
	public @ResponseBody ResponseEntity<String> test03(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		//res.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(id+","+name);
		return new ResponseEntity<String>(id + "님 반갑습니다.("+name+")",HttpStatus.OK);
	}
	
	@GetMapping("/test04")
	public ResponseEntity<String> test04(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/plain; charset=utf-8");
		System.out.println(id+","+name);
		return new ResponseEntity<String>(id + "님 반갑습니다.("+name+")",HttpStatus.OK);
	}
	
	@GetMapping("/test05")
	public ResponseEntity<MemberDto> test05(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/plain; charset=utf-8");
		System.out.println(id+","+name);
		
		MemberDto dto = new MemberDto();
		
		dto.setId(id);
		dto.setName(name);
		dto.setAge("21");
		dto.setPw("22");
		return new ResponseEntity<MemberDto>(dto,HttpStatus.OK);
	}
	
	@PostMapping("/test06")
	public @ResponseBody ResponseEntity<MemberDto> test06(@RequestBody MemberDto dto, HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println(dto);
		dto.setAddr("999999");
		
		return new ResponseEntity<MemberDto>(dto,HttpStatus.OK);
	}
}
