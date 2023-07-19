package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private MemberService service;
	
	//어노테이션없이 ajax사용하기
	@GetMapping("/basicTest.do") //postMapping()은 post로 보낸 값, 그럼 GetMappring()은???
	public void basic(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException {
		Board b = Board.builder().boardTitle("냉무").boardContent("냉무").build();
		ObjectMapper mapper = new ObjectMapper();
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b));
	}
	
	//리턴값에 반환할 객체를 선언
	//@ResponseBody ->json으로 반환할 수 있게 처리
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring좋다!").boardContent("하하하하하").build();
	}
	
	@GetMapping("/doubleCheck.do")
	@ResponseBody
	public Member doubleCheck(Member member) {
		//위 map 자료형에서는 @RequestParam이 되고, Member는 안되는 이유 : map은 자료형을 object로 선언해서 같은자료형이라 되고, 
		//	Member는 서로 다르기 때문에 오류를 일으킨다. 해결법은 그냥 넣음으로서 해결할 수 있다.
		return service.login(member);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	
	@GetMapping("/selectMemberAll")
	@ResponseBody
	public List<Member> selectMemberAll() {
		return service.selectMemberAll();
	}
	
	@PostMapping("/insertData.do")
	public Member insertData(@RequestBody Member m) {
		log.info("{}",m);
		return m;
	}
	
	
	
	
	
	
}
