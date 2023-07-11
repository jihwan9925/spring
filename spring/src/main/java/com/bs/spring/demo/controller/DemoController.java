package com.bs.spring.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;

@Controller
public class DemoController {
	
	@RequestMapping("/demo/demo.do")
	public String main() {
		return "/demo/demo";
	}
	
	//메소드 선언하기
	//리턴값, 매개변수 알아보기
	//1. 반환형
	// 1) String : viewResolver에 의해서 view화면을 출력해준다. (기본적으로 가장많이 쓰임)
	// 2) void : HttpServletResponse객체로 직접 응답메세지를 작성할 때 사용 (doGet과 유사해질 수 있다.)
	// 3) ModelAndView : 화면에 전달할 데이터와 view내용을 저장하는 객체 (spring제공, )
	// 4) 클래스 타입 : json으로 데이터를 반환할 때, Restful하게 서버를 구성했을 때 많이사용한다. (*ResponseEntity<클래스타입>)
	
	//2. 매개변수로 선언할 수 있는 타입
	// 1) HttpServletRequest : 서블릿에서 사용하던 객체 , 서블릿처럼 사용 가능해진다.
	// 2) HttpServletResponse : 서블릿에서 사용하던 객체 , 서블릿처럼 사용 가능해진다.
	// 3) HttpSession : 서블릿에서 사용하던 객체 , session값을 가져와서 대입해준다.
	// 4) java.util.Locale : 서버의 로케일정보를 저장한객체 (ex : ko-kr)
	// 5) InputStream/Reader : 파일 읽어올 때 사용하는 stream
	// 6) OutputStream/Writer : 파일 보낼 때 사용하는 stream
	// 7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터랑 선언한 변수이름이 동일하면 자동으로 매핑해준다. =============================
	       //선언이름과 파라미터이름이 다를 경우 @RequsetParameter머노테이션을 이용해서 연결할 수 있다.
		   //@RequestParam은 매핑,기본값설정,필수여부설정
	// 8) 클래스변수 : Command라고 한다. parameter데이터를 필드에 넣어서 객체를 전달한다 (parameter이름과 필드명이 같은 데이터를 대입해준다.)
	// 9) java.util.Map : @RequestParam 어노테이션과 같이 사용한다. (parameter값을 map으로)
	// 10) @RequestHeader(name값)와 기본자료형을 작성하면 header정보를 받을 수 있다.
	// 11) @CookieValue(name값)와 기본자료형을 작성하면 Cookie에 저장된 값을 받을 수 있다.
	// 12) Model : request와 비슷하게 데이터를 key,value 형식으로 저장할 수 있는 객체
	// 13) ModelAndView : model과 view를 동시에 저장하는 객체
	
	//메소드 어노테이션
	// @ResponseBody -> Rest방식으로 클래스를 json으로 전송할 때 
	// @RequestBody -> Json방식으로 전송된 parameter를 클래스로 받을 때 사용
	// @GetMapping, @PostMapping, @DeleteMapping...
	
	//서블릿 방식으로 메핑메소드 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(req);
		System.out.println(res);
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devGender = req.getParameter("devGender");
		String devEmail = req.getParameter("devEmail");
		String[] devLang = req.getParameterValues("devLang");
		System.out.println(devName+devAge+devGender+devEmail);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder()
				.devName(devName)
				.devAge(devAge).devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang).build();
		req.setAttribute("demo", d);
		
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		
		/*
		 * res.setContentType("text/html;charset=utf-8"); PrintWriter out =
		 * res.getWriter(); out.print("<h2>"+devName+degAge+devGender+devEmail+"</h2>");
		 */
		
	}
	
	
	
}
