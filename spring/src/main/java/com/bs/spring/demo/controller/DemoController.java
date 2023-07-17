package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.MainController;
import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.model.dto.Memo;
import com.bs.spring.demo.service.DemoService;

@Controller
public class DemoController {
	
	//logger 생성하기
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private DemoService service;
	
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
	// 8) 클래스변수 : Command(=Dto)라고 한다. parameter데이터를 필드에 넣어서 객체를 전달한다 (parameter이름과 필드명이 같은 데이터를 대입해준다.)
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
		// logger.debug(req); -debug가 받는 자료형은 String이기 때문에 그냥은 안됨
		logger.debug("request : {}",req);
		logger.debug("response : {}",res);
		// System.out.println(req); 위로 변환함
		// System.out.println(res);
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devGender = req.getParameter("devGender");
		String devEmail = req.getParameter("devEmail");
		String[] devLang = req.getParameterValues("devLang");
		logger.debug(devName+devAge+devGender+devEmail);
		// System.out.println(devName+devAge+devGender+devEmail);
		for(String l : devLang) {
			logger.debug(l);
			// System.out.println(l);
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
	//1:1 매칭하여  데이터 받기
	//메핑메소드의 매개변수의 파라미터로 전송되는 name과 동일한 이름의 변수를 선언
	//매개변수의 타입은 사용할 타입으로 설정, 변경이 가능해야한다
	@RequestMapping("demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang,Model model) {
		System.out.println(devName+devAge+devGender+devEmail+Arrays.toString(devLang));
		//페이지에 생성한 데이터를 전송하려면 request,session,servletcontext를 사용했지만
		//spring에서는 model이라는 데이터전송 객체를 제공한다.
		//Model에 데이터 저장하려면 model.addAttribute("key",value);
		Demo d = Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devGender(devGender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		model.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	//파라미터 데이터를 받을 때 @RequestParameter어노테이션을 이용하여 옵션을 설정할 수 있다.
	@RequestMapping("demo/demo3.do")
	public String requestParamuse( 
			@RequestParam(value="devName")String name, 
			@RequestParam(value="devAge",defaultValue="10")int age, 
			@RequestParam(value="devGender")String gender,
			@RequestParam(value="devEmail",required = false)String devEmail, String[] devLang, Model model) {
		Demo d = Demo.builder()
				.devName(name)
				.devAge(age)
				.devGender(gender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		model.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	//DTO/VO 객체로  직접 parameter값 받기
	//매개변수로 전달된 parameter이름과 동일한 필드를 갖는 객체를 선언함
	//*주의할 점 : Date타입을 전달받을 때는 java.sql.Date로 변경하기
	@RequestMapping("/demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);
		m.addAttribute("demo", demo);
		return "demo/demoResult";
	}
	
	//Map으로 parameter데이터 받아오기
	//@RequestParam어노테이션 설정
	//배열은 문자열로 하나만 가져오고, 날짜는 문자열로 받아오기 때문에 형변환 하거나, 매개변수에 추가하면 된다.
	@RequestMapping("/demo/demo5.do")
	public String mapMapping(@RequestParam Map param,String[] devLang, Model m) {
		param.put("devLang", devLang);
		System.out.println(param);
		m.addAttribute("demo", param);
		return "demo/demoResult";
	}
	
	//추가 데이터 받아오기
	//Cookie,Header,Session
	// Cookie : @CookieValue(value="key") String data
	// Header : @RequestHeader(value="헤더이름") String header
	// Session : @SessionAttibute(value="세션key값") String id
	@RequestMapping("/demo/demo6.do")
	public String extraData(
			@CookieValue(value="testData",required = false,defaultValue = "rest-time")String data,
			@RequestHeader(value="User-agent") String userAgent,
			@SessionAttribute(value="sessionId") String sessionId,
			@RequestHeader(value="Referer") String referer
			
			) {
		System.out.println("쿠키 : "+data);
		System.out.println("헤더 : "+userAgent);
		System.out.println("세션 : "+sessionId);
		System.out.println("이전페이지 : "+referer);
		// 보내는 곳은 MainController , 여기서는 확인만 
		return "index";
	}
	
	//ModelAndView객체를 이용해서 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		//ModelAndView는 view설정과, Model설정을 같이 할 수 있는 객체
		// view : setViewName()메소드를 이용해서 저장
		// data : addObject("key",value)메소드를 이용해서 저장
		mv.addObject("demo",d);
		mv.setViewName("demo/demoResult");
		return mv;
	}
	
	//자료형에 대해서 반환하기 (Data만 응답할 때 사용, 주로 json응답 받을 떄 사용[jackson라이브러리를 이용해서 처리])
	//메소드선언부 @ResponseBody어노테이션 사용
	//Restful메소드를 구현했을 때 사용
	//============================================================
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn(){
		return "유병승 최주영 조장흠 최솔 조윤진";
	}
	
	//Request요청 메소드(Get,Post)를 필터링하기
	//@RequestMapping(value="url",method=RequestMethod.GET||RequestMethod.POST)
	//간편하게 사용할 수 있게 Mapping어노테이션을 지원
	//@GetMapping
	//@PostMapping
	//@DeleteMapping
	//@PutMapping
	
//	@RequestMapping(value="/demo/demo9.do",method=RequestMethod.POST)
	@PostMapping("/demo/demo9.do") //위 로직과 동일한 기능(어노테이션 자체가 post만 전송하고 받는기능)
	public String methodCheck(Demo d, Model m) {
		m.addAttribute("demo", d);
		return "demo/demoResult";
	}
	
	//mapping주소를 설정할 때 {}를 사용할 수 있다.
	//	/board/boardview?no=1
	//	/board/1 method=GET
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable(value="no") int no) {
		System.out.println(no);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDemo.do",method=RequestMethod.POST)
	public String insertDemo(Demo demo, Model m) {
		int result = service.insertDemo(demo);
		System.out.println(result);
		m.addAttribute("msg", result>0?"저장성공":"저장실패");
		m.addAttribute("loc", "/demo/demo.do");
		//sendRedirect로 변경하는 방법(이유 : 기본값(Dispathcher)로 전송하면 새로고침시 동일한 값을 게속 보낸다)
		//ㄴ prefix redirect:요청할 주소(매핑주소[=메소드위에 선언된 주소])
		//return "redirect:/";
		//또는 "redirect:/demo/demo";
		 
		return "common/msg"; //지금은 메세지처리때문에 메세지화면으로 이동함
		//return "demo/demo";
	}
	
	@RequestMapping(value="/demo/selectDemoAll.do",method=RequestMethod.POST)
	public String selectDemoAll(Model m) {
		List<Demo> result = service.selectDemoAll();
		m.addAttribute("demo",result);
		return "demo/demoList";
	}
	
}
