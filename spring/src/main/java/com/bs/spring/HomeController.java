package com.bs.spring;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FunctionalTest;
import com.bs.spring.include.TargetComponent;



@Controller
public class HomeController {

	//springbean으로 등록된 객체는 필드로 가져와 사용할 수 있다
	//servlet-context에서 생성된 bean객체가 중복되는 경우 생성된bean객체의 이름을 구분자로 사용가능하다(a->dog||bbo)
	/*
	 * @Autowired private Animal a;
	 */
	
	@Autowired
	//중복된 타입이 있는 경우
	//@Qualifier어노테이션을 이용해서 특정 bean을 선택할 수 있다.
	@Qualifier("dog")
	private Animal a;
	
	@Autowired
	@Qualifier("bbo")
	private Animal b;
	
	//springBean으로 등록되지 않은 객체를 Autowired하면??
	//클래스에만 생성하더라도 bean으로 등록하지 않았기 때문에 에러(NoSuchBeanDefinitionException)가 발생
	//@Autowired(required = false)를 사용하면 당장 bean에 등록하지않아도 에러가 뜨지 않는다.(있으면 등록,없으면 무시[null]) 
	@Autowired(required = false)
//	@Qualifier("bbo")
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	
	//java로 등록한 bean가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	private List<Animal> animals;
	
	@Autowired
	private TargetComponent tc;
	
	//@어노테이션으로 bean등록
	@Autowired
	private FunctionalTest ft;
	
	//base-package 외부에 있는 @Component -> 오류
	@Autowired
	private Test test;
	
	
	@RequestMapping("/test")
	public String home() {
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		
		System.out.println(tc);
		System.out.println("functionalTest출력");
		System.out.println(ft);
		System.out.println(ft.getA());
		
		//spring에서 파일을 불러올 수 있는 Resource객체를 제공
		Resource resource = new ClassPathResource("mydata.properties");
		try {
			//프로젝트 내에서 파일 불러오기
			Properties prop = PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			
			//직접 경로를 입력하여 파일 불러오기
			resource = new FileSystemResource("C:\\Users\\GDJ\\git\\spring\\spring\\src\\main\\resources\\text.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8"))
			.forEach(System.out::println);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		return "index";
	}
}
