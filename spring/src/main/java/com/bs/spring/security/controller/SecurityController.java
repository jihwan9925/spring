package com.bs.spring.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {

	@RequestMapping("/loginPage")
	public String loginPage() {
		return "member/loginPage";
	}
	
	@RequestMapping("/error.do")
	public String loginFall(Model m) {
		m.addAttribute("msg", "로그인 실패");
		m.addAttribute("log", "/");
		return "common/msg";
	}
	
	@RequestMapping("/successLogin.do")
	public String loginSuccess() {
		log.info("로그인 성공");
		//인증성공 후 실행되는 메소드
		//인증한 사용자에 대한 정보를 확인
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("{}",o);
		return "redirect:/";
	}
}
