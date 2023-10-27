package edu.ssafy.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.service.MemberService;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.PaggingUtil;

@CrossOrigin("*")
@Controller
@RequestMapping("/restmem")
public class RestMemberController {
	
	private MemberService service;
	@Autowired
	public RestMemberController(@Qualifier("MemberServiceMapperImpl") MemberService service) {
		this.service = service;
	}
	
	@GetMapping("/idcheck/{id}")
	public @ResponseBody Map<String, String> idCheck(@PathVariable("id") String id) throws Exception{
		MemberDto dto = new MemberDto();
		dto.setId(id);
		MemberDto mem = service.memberView(dto);
		Map<String, String> map = new HashMap();
		System.out.println(mem);
		map.put("result", mem==null?"사용하실 수 있는 아이디입니다":"사용하실 수 없는 아이디입니다");
		return map;
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> 
							MemberInsert(@RequestBody MemberDto dto) {
		
		Map<String,Object> map = new HashMap();
		try {
			int res = service.memberInsert(dto);
			map.put("resmsg", "입력성공");
			map.put("resdata", res);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "입력실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String,Object>> res = new ResponseEntity(map,HttpStatus.OK);
		return res;
	};
	
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> MemberList() {
		Map<String,Object> map = new HashMap();
		try {
			List<MemberDto> list = service.memberList();
			map.put("resmsg", "조회성공");
			map.put("resdata", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map,HttpStatus.OK);
		return res;
	};
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Map<String, Object>> MemberView(@PathVariable("id") String id) {
		MemberDto dto = new MemberDto();
		dto.setId(id);
		Map<String,Object> map = new HashMap();
		try {
			MemberDto member = service.memberView(dto);
			map.put("resdata", member);
			map.put("resmsg", "조회성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "조회실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map,HttpStatus.OK);
		return res;
	};
	
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> MemberUpdate(@RequestBody MemberDto dto) {
		Map<String,Object> map = new HashMap();
		try {
			int res = service.memberUpdate(dto);
			map.put("resdata", res);
			map.put("resmsg", "수정성공");
			
		} catch (Exception e) {
			map.put("resmsg", "수정실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map,HttpStatus.OK);
		return res;
	};
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> MemberDelete(@PathVariable("id")String id) {
		Map<String,Object> map = new HashMap();
		MemberDto dto = new MemberDto();
		dto.setId(id);
		try {
			int res = service.memberDelete(dto);
			map.put("resdata", res);
			map.put("resmsg", "삭제성공");
			
		} catch (Exception e) {
			map.put("resmsg", "수정실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map,HttpStatus.OK);
		return res;
	};
	
	@DeleteMapping("/deletes/{ids}")
	public ResponseEntity<Map<String, Object>>  MemberDeletes(@PathVariable("ids") String ids , ModelAndView mav)  {
		String[] id = ids.split(",");
		Map<String,Object> map = new HashMap();
		try {
			boolean res = service.memberDeletes(id);
			map.put("resdata", res);
			map.put("resmsg", "삭제성공");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resmsg", "삭제실패");
			map.put("resdata", e.getMessage());
		}
		ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map,HttpStatus.OK);
		return res;
	};
	
	
//	@GetMapping(value = { "/login" })
//	public ModelAndView loginForm(ModelAndView mv) {
//		try {
//			mv.setViewName("member/loginform");
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv.setViewName("error/error");
//		}
//
//		return mv;
//	}
//	@RequestMapping("/login")
//	public ModelAndView login(MemberDto dto, ModelAndView mv, HttpServletRequest req) throws Exception {
//		try {
//			boolean login = service.login(dto);
//			if (login) {
//				req.getSession().setAttribute("loginid", dto.getId());
//				mv.setViewName("index");
//			} else {
//				mv.setViewName("index");
//			}
//		} catch (Exception e) {
//			 //TODO Auto-generated catch block
//			e.printStackTrace();
//			mv.setViewName("error/error");
//		}
//		return mv;
//	}
//	@GetMapping(value = { "/logout" })
//	public ModelAndView logout(ModelAndView mv, HttpServletRequest req) {
//		try {
//			req.getSession().invalidate();
//			mv.setViewName("index");
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// mv.addObject(attributeValue)
//			e.printStackTrace();
//			mv.setViewName("error/error");
//		}
//
//		return mv;
//	}
}
