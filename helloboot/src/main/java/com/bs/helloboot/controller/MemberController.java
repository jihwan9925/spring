package com.bs.helloboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.helloboot.dto.Member;
import com.bs.helloboot.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	
	public MemberController(MemberService service) {
		this.service = service;
	}

//	MyWebMvcConfiguration에서 연결설정을 해서 여기서 선언하지 않아도 된다.
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/memberAll")
	public String selectMemberAll(Model m) {
		m.addAttribute("member", service.selectMemberAll());
		return "member/memberList";
	}
	
}
