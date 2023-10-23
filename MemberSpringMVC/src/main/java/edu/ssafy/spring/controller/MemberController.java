package edu.ssafy.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.ssafy.spring.dto.MemberDto;
import edu.ssafy.spring.service.MemberService;

@Controller
@RequestMapping("/mem")
public class MemberController {

	private MemberService service;

	public MemberController(MemberService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/insert")
	public String MemberInsertForm() {
		return "member/regform";
	}

	@PostMapping("/insert")
	public String MemberInsert(@ModelAttribute() MemberDto dto) {
		try {
			int res = service.memberInsert(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@GetMapping("/idcheck/{id}")
	@ResponseBody
	public Map<String, String> checkId(@PathVariable String id) throws Exception {
		System.out.println(id);
		Map<String, String> result = new HashMap<>();
		try {
			int idCheck = service.idCheck(id);
			if (idCheck == 0) {
				result.put("result", "생성 가능");
			} else {
				result.put("result", "생성 불가");
			}
		} catch (Exception e) {
			result.put("result", "error");
			e.printStackTrace();
		}
		return result;
	}

	@PostMapping("/delmembers")
	public String listMem(@RequestParam("id") String[] ids) {
		for (String id : ids) {
			try {
				System.out.println(id);
				MemberDto findMember = service.findById(id);
				System.out.println(findMember.getId());
				if (findMember != null) {
					service.memberDelete(findMember);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return "redirect:/mem/list";
	}

//	@GetMapping("/list")
//	public ModelAndView MemberList(ModelAndView mav) throws Exception {
//		List<MemberDto> list = service.memberList();
//		mav.addObject("list", list);
//		mav.setViewName("member/listmember");
//		return mav;
//	}

	@GetMapping("/list")
	public String MemberList(Model model) throws Exception {
		List<MemberDto> list = service.memberList();
		model.addAttribute("list", list);
		return "member/listmember";
	}

	@GetMapping("/view")
	public ModelAndView MemberView(MemberDto dto, ModelAndView mav) {
		try {
			MemberDto member = service.memberView(dto);
			mav.addObject("mem", member);
			mav.setViewName("member/viewmember");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mav;
	}

	@GetMapping("/login")
	public String login() {
		return "member/loginform";
	}

	@PostMapping("/login")
	public String login(String id, String pw, HttpSession session) throws Exception {
		MemberDto login = service.memberLogin(id, pw);
		if (login != null) {
			session.setAttribute("userinfo", login);
			return "redirect:/";
		}
		return "member/loginform";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userinfo");
		return "redirect:/";
	}
//	
//	@PostMapping("/update")
//	public ModelAndView MemberUpdate(@ModelAttribute MemberDto dto, ModelAndView mav) throws Exception {
//		try {
//			int res = service.memberUpdate(dto);
//			mav.setViewName("redirect:/mem/list");			
//		} catch (Exception e) {
//			e.printStackTrace();
//			mav.setViewName("error/404");
//		}
//		return mav;
//	}

	@PostMapping("/update")
	public String MemberUpdate(Model model, @ModelAttribute MemberDto dto) throws Exception {
		try {
			int res = service.memberUpdate(dto);
			return "redirect:/mem/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/404";
		}
	}

	@PostMapping("/delete")
	public ModelAndView MemberDelete(@ModelAttribute MemberDto dto, ModelAndView mav) throws Exception {
		try {
			int res = service.memberDelete(dto);
			mav.setViewName("redirect:/mem/list");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("error/404");
		}
		return mav;
	}

}
