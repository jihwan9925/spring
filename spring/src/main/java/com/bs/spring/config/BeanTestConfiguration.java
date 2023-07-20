package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

//클래스 방식으로 bean등록해서 사용하기
// pojo 클래스를 configuration으로 사용할 수 있다 -> @Configuration어노테이션이용
@EnableWebMvc
@Configuration //일반 클래스를 spring에서 등록할 수 있는 어노테이션
@ComponentScan(//어노테이션이 부여된 Class들을 자동으로 Scan하여 Bean으로 등록하는 어노테이션
		basePackages = "com.bs.spring", //bean객체를 등록하기위한 최상위 디렉토리 설정
		includeFilters = {@ComponentScan.Filter(
				type = FilterType.REGEX,
				pattern = {"com.bs.spring.include.*"})}, 
		//ㄴ어노테이션표시가 없더라도 해당이되면 bean으로 등록함(추가기능),REGEX=정규표현식 
		excludeFilters = {} //어노테이션에 해당 하더라도 bean등록할 수 없음(제외기능)
		)
//@Import() //다른configuration을 가져와 처리하는 것
public class BeanTestConfiguration {
	//springbeanconfiguration.xml(=servlet-context.xml)과 동일한 기능 제공
	//spring에서 사용할 bean을 자바코드로 등록할 수 있다.(@Bean을 이용하여 수동으로 등록가능, 메소드 선언을 동해 등록)
	@Bean
	@Order(1) //bean의 우선순위를 설정가능
	public Animal ani() {
		return Animal.builder()
				.name("킥킥")
				.age(5).height(80).build();
	}
	
	@Bean
	//등록된 bean에 특정id값 부여하기 
	//@Qualifier(value="sol")를 이용하여 id값을 설정할 수 있다.
	//@Qualifier("sal") 이유 : 매개변수로 받는 Department가 두개 있기 때문에
	// 가지고 올 id값을 명확하게 하기 위해서 매개변수에도 @Qualifier 선언한다.
	@Qualifier(value="sol")
	public Employee getEmployee(@Qualifier("sal") Department d) {
		return Employee.builder()
				.name("최솔")
				.age(27)
				.address("경기도 안양시")
				.salary(200)
				.dept(d).build();
	}

	@Bean public Department sal() {
		return Department.builder()
				.deptCode(2L)
				.deptTitle("영업부")
				.deptLocation("서울").build();
	}
	
	//driver.properties 설정을 bean에 등록하여 사용할 수도 있다.
	@Bean BasicDataSource getDataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
