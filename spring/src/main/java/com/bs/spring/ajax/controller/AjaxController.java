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
import com.bs.spring.common.exception.AuthenticationException;

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
	public List<Member> selectMemberAll(){
		//if(1==1) throw new AuthenticationException("권한에러발생!");
		return service.selectMemberAll();
	}
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) {
		log.info("{}",m);
		return m;
	}
	
	//REST API, RESTful
	//의미 : URL을 설정할 때 간편하게 서비스를 알아볼 수 있는 방식으로 구현하자
	//		웹에서 url주소를 설계할 때 주소만 보고도 알아볼수 있도록 메소드로 구분하는 방식
	//		URL주소를 설정을 할 때 행위에 대한 표현을 빼자 -> 행위는 method를 보고 결정하자
	//목적 : session,Cookie 관리를 하지않는다.(stateless) ajax라서 가능함, 
	//method
	//GET : Data를 조회하는 서비스
	//POST : Data를 저장하는 서비스
	//PUT : Data를 수정하는 서비스
	//DELETE : Data를 삭제하는 서비스
	//URL설정할 때는 명사로 작성한다.
	// ex: 회원을 관리하는 서비스
	// GET localhost:9090/spring/member -> 전체회원 조회
	// GET localhost:9090/spring/member/{id}1||admin -> 회원 1명 조회
	// POST localhost:9090/spring/member -> 회원 추가
	// PUT localhost:9090/spring/member -> 회원 수정	
	// DELETE localhost:9090/spring/member -> 회원 삭제	
	
	
	
}
