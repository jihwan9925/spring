package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //spring에서 Controller역할을 하는 클래스에 적용된다
//@Bean객체 중에서도 controller역할을 맡게 된다. (=springbean으로 등록된다)
public class MainController {
	//@Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(메핑메소드)로 선언한다
	//요청 URL주소 연결되는 메소드
	
	//@RequestMapping어노테이션을 메핑메소드 선언부에 선언을 한다.
	
	//controller에 선언된 메소드는 일반적으로 String값을 반환하도록 설정한다. (String형태의 리턴값으로 어떤화면을 출력할지 정하기 때문이다.)
	//view선택해서 출력시킬 때...
	
	@RequestMapping("/") //=@RequestMapping()괄호안에 있는 주소가 실행되면 main()메소드가 실행되야한다고 명시
	public String main(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		//메소드가 반환하는 값은 viewResolver Bean이 처리한다. servlet-context.xml에 있는 InternalResourceViewResolver설정과 같다
		//등록된 InternalResourceResolver Bean은 
		//반환된 문자열의 객체의 설정된 prefix, sufix를 붙여서 내부에서 화면출력파일을 찾는다. 
		//  (prefix,sufix는 파일앞뒤로 붙여서 경로를 완성시키는 역할만 한다. 이름의 큰 의미는 없다.)
		// [/WEB-INF/views/리턴값.jsp]
		// ex : RequestDispather("/WEB-INF/views/리턴값.jsp").forward();
		
		//===========================================================================
		//쿠키추가하기
		Cookie c = new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		
		return "index";
	}
	
}
