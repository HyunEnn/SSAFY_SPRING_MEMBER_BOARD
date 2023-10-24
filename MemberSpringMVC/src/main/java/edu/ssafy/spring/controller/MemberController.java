package edu.ssafy.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.service.MemberService;
import edu.ssafy.spring.util.PageNavigation;
import edu.ssafy.spring.util.PaggingUtil;

@Controller
@RequestMapping("/mem")
public class MemberController {
	
	private MemberService service;
	@Autowired
	public MemberController(MemberService service) {
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
	
	@GetMapping("/insert")
	public String MemberInsertForm() {
		return "member/regform";
	};
	
	@PostMapping("/insert")
	public ModelAndView MemberInsert(MemberDto dto, ModelAndView mav) {
		try {
			int res = service.memberInsert(dto);
			mav.setViewName("redirect:/mem/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	@GetMapping("/list")
	public ModelAndView MemberList(String pg, String spp, ModelAndView mav) {
		int currentPage = pg == null ? 1 : Integer.parseInt(pg);
		currentPage = currentPage == 0 ? 1 : currentPage;
		int sizePerPage = spp == null ? PaggingUtil.sizePerPage : Integer.parseInt(spp);
		try {
			Map<String,Integer>map = new HashMap();
			map.put("currentPage", (currentPage-1)*sizePerPage);
			map.put("sizePerPage", sizePerPage);
			List<MemberDto> list = service.memberList(map);
			PageNavigation pageNavigation = service.makePageNavigation(currentPage, sizePerPage);
			mav.addObject("navigation", pageNavigation);
			mav.addObject("list",list);
			mav.setViewName("member/listmember");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	
	@GetMapping("/view")
	public ModelAndView MemberView(MemberDto dto, ModelAndView mav) {
		try {
			MemberDto member = service.memberView(dto);
			mav.addObject("mem", member);
			mav.setViewName("member/viewmember");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		
		return mav;
	};
	
	@PostMapping("/update")
	public ModelAndView MemberUpdate(@ModelAttribute() MemberDto dto, ModelAndView mav,HttpServletRequest req) {
		try {
			int res = service.memberUpdate(dto);
			mav.setViewName("redirect:/mem/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	
	@PostMapping("/delete")
	public ModelAndView MemberDelete(@ModelAttribute() MemberDto dto, ModelAndView mav) {
		try {
			int res = service.memberDelete(dto);
			mav.setViewName("redirect:/mem/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	
	@PostMapping("/deletes")
	public ModelAndView MemberDeletes(String[] id , ModelAndView mav)  {
		try {
			service.memberDeletes(id);
			mav.setViewName("redirect:/mem/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/error");
		}
		return mav;
	};
	
	
	@GetMapping(value = { "/login" })
	public ModelAndView loginForm(ModelAndView mv) {
		try {
			mv.setViewName("member/loginform");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("error/error");
		}

		return mv;
	}
	@RequestMapping("/login")
	public ModelAndView login(MemberDto dto, ModelAndView mv, HttpServletRequest req) throws Exception {
		try {
			boolean login = service.login(dto);
			if (login) {
				req.getSession().setAttribute("loginid", dto.getId());
				mv.setViewName("index");
			} else {
				mv.setViewName("index");
			}
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("error/error");
		}
		return mv;
	}
	@GetMapping(value = { "/logout" })
	public ModelAndView logout(ModelAndView mv, HttpServletRequest req) {
		try {
			req.getSession().invalidate();
			mv.setViewName("index");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// mv.addObject(attributeValue)
			e.printStackTrace();
			mv.setViewName("error/error");
		}

		return mv;
	}
}
