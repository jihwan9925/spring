package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //spring에서 Controller역할을 하는 클래스에 적용된다
//@Bean객체 중에서도 controller역할을 맡게 된다. (=springbean으로 등록된다)
public class MainController {
	
	//Log를 출력하기 위한 Logger가져오기
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//=================================================================================
	
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
		
		//log4j를 이용해서 log출력하기
		//sif4j에서 제공하는 Logger인터페이스를 구현한 클래스를 이용한다.
		//사용법 : LoggerRactory클래스에 static메소드인 getLogger(logger가 가져오는 클래스지정)
		
		//log를 출력할 때는 logger가 제공하는 메소드를 이용
		//	debug() : 개발시 사용하는 로그를 출력시 사용
		//	info() : 프로그램을 실행하는 정보를 출력할 때 사용
		//	warn() : 잘못된 사용을 했을 때 출력할때 사용(ex: 제네릭타입설정 안했을 때 경고용)
		//	error() : Exception 실행이 불가능한 기능에 대한 로그를 출력할 때 사용
		// * 메소드의 매개변수는 기본적으로 String만 가능, 객체나 다른 데이터를 출력하려면 ("{}",출력변수)
		
		//level(log4j에 있는 레벨)
		//debug -> info -> warn -> error -> fatal (오른쪽으로 갈수록 위험)
		// * 메소드하나를 선택하면 선택한 메소드부터 fatal까지 출력된다.
		
		//Log를 출력하기 위한 Logger가져오기 : 클래스 상단에서 생성
		
//		기본 패턴설정 : %-5(=5칸차지+왼쪽정렬)p: %c - %m%n
//		이벤트명, 카테고리명, 로그전달메세지 개행
//
//		* %c : 카테고리명(logger이름)을 표시
//		* 카테고리명이 a.b.c일때, %c{2}는 b.c를 출력
//		* %C : 클래스명을 표시함.	
//		* 풀 클래스 명이 com.kh.logger일때, %C{2}는 kh.logger를 출력
//		* %d : 로그 시간을 출력한다. java.text.SimpleDateFormat에서 적절한 출력 포맷을 지정할 수 있다. 
//		* %d{HH:mm:ss, SSS}
//		* %d{yyyy MMM dd HH:mm:ss, SSS}
//		* %d{ABSOLUTE} 
//		* %d{DATE} 
//		* %d{ISO8601}
//		* %F : 파일명을 출력. 로그시 수행한 메소드, 라인번호가 함께 출력된다.
//		* %l : 로깅이 발생한 caller의 위치정보. 자바파일명:라인번호(링크제공) 
//		* %L : 라인 번호만 출력한다(링크없음)
//		* %m : 로그로 전달된 메시지를 출력한다.
//		* %M : 로그를 수행한 메소드명을 출력한다. 
//		* %n : 플랫폼 종속적인 개행문자가 출력. rn 또는 n
//		* %p : 로그 이벤트명등의 priority 가 출력(debug, info, warn, error, fatal )
//		* %r : 로그 처리시간 (milliseconds)
//		* %t : 로그이벤트가 발생된 쓰레드의 이름을 출력
//		* %% : % 표시를 출력. escaping
//		* %r : 어플리케이션 시작 이후 부터 로깅이 발생한 시점의 시간(milliseconds)
//		* %X : 로깅이 발생한 thread와 관련된 MDC(mapped diagnostic context)를 출력합니다. %X{key} 형태.
		
		//로그출력하기
		logger.debug("debug내용출력하기");
		logger.info("info내용출력하기");
		logger.warn("warn내용출력하기");
		logger.error("error내용출력하기");
		
		
		return "index";
	}
	
}
