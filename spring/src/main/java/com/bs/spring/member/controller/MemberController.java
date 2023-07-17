package com.bs.spring.member.controller;

import java.lang.module.ModuleDescriptor.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member") //클래스에 선언하면 중복되는 요소를 지울 수 있다.
@SessionAttributes({"loginMember"})//loginMember라는 이름을 가지는 객체를 세션으로 저장해주는 기능
@Slf4j //lombok에서 지원하는 메소드, log로 사용가능
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember(@ModelAttribute("member") Member m) {
		return "member/enrollMember";
	}
	
	@PostMapping("/insertMember.do")
	public String insertMember(@Validated Member member, BindingResult isResult, Model m) {
		
		if(isResult.hasErrors()) {
			//에러나면 다시 입력창으로 이동
			return "member/enrollMember";	
		}
		
		//패스워드를 암호화해서 처리하기
		String oriPassword = member.getPassword();
		String encodePassword = passwordEncoder.encode(oriPassword);
		member.setPassword(encodePassword);
		log.debug(encodePassword);
		
		int result = service.insertMember(member);
		m.addAttribute("msg", result>0?"회원가입 성공":"회원가입 실패");
		m.addAttribute("loc", result>0?"/":"/member/enrollMember.do");
		
		
//		return "redirect:/common/msg"; //redirect를 사용하지 않는 이유: 
		return "common/msg";
	}
	
	@RequestMapping("/login.do")
	public String login(Member member, Model m) {
		//암호화를 진행하면서 기존방식으로 버번을 구분할 수 없기 때문에(login) 이를 해결하기 위해서
		//BCryptPasswordEncoder의 메소드를 이용한다
		
		Member result = service.login(member);
		if(result!=null&&passwordEncoder.matches(member.getPassword(),result.getPassword())) {
			//로그인성공
			//session.setAttribute("loginMember", result); //굳이 셰션을 추가해줘야하는 번거로움이 있음
			//model을 이용한 session처리하기
			m.addAttribute("loginMember", result); //클래스에서 생성한 loginMember를 세션으로 바꾸는 작업을 어노테이션으로 한다.
		}else {
			m.addAttribute("msg", "로그인 실패");
			m.addAttribute("loc", "/");
			return "common/msg";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(SessionStatus status) {
		//@SessionAttributes로 등록된 내용 삭제하기 -> SessionStatus객체를 이용해서 삭제
//		if(session!=null) session.invalidate();
		if(1==1) throw new IllegalArgumentException("잘못된 접근입니다."); //오류를 보기위한 임시코드
		
		if(!status.isComplete()) status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	//쿼리스트링으로 멤버 숫자를 가져와 다시 로그인한 후 그정보를 가져오는 방법
//	public String myPage(String userId, Model m) {
//		Member member = Member.builder().userId(userId).build();
//		m.addAttribute("member", service.login(member));
//		return "/member/mypage";
//	}
	//세션에 값이 있고, 로그인을 해야지 접근가능하기 때문에 간단하게 해결가능
	public String myPage() {
		return "/member/mypage";
	}
	
	@PostMapping("/update.do")
	public String update(Member member, Model m) {
		int result = service.update(member);
		if(result>0) {
			//성공
			m.addAttribute("loginMember", service.login(member));
			m.addAttribute("msg", "수정 성공");
			m.addAttribute("loc", "/member/mypage.do");
		}else {
			//실패
			m.addAttribute("msg", "수정 실패");
			m.addAttribute("loc", "/");
		}
		return "common/msg";
	}
	
}
